package com.viacaovv.trabalhoengenharia.controller;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.layout.property.TextAlignment;
import com.viacaovv.trabalhoengenharia.entity.*;

import com.viacaovv.trabalhoengenharia.service.ClienteService;
import com.viacaovv.trabalhoengenharia.service.PassagemService;
import com.viacaovv.trabalhoengenharia.service.ViagemService;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import org.springframework.web.bind.annotation.GetMapping;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;


import java.util.List;
import java.util.Random;



@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    private ClienteService clienteService;
    private ViagemService viagemService;
    private PassagemService passagemService;

    @Autowired
    public UsuarioController(ClienteService clienteService, ViagemService viagemService, PassagemService passagemService) {
        this.clienteService = clienteService;
        this.viagemService = viagemService;
        this.passagemService = passagemService;
    }

    @GetMapping("/login")
    public String login(Model model) {
        Usuario usuario = new Usuario();

        model.addAttribute("usuario", usuario);

        return "view/usuario/login-usuario";
    }

    @GetMapping("/verificarLogin")
    public String verificarLogin(@ModelAttribute("usuario") Usuario usuario, Model model, HttpSession session) {
        if (Administrador.getInstance().getEmail().equals(usuario.getEmail())
                && Administrador.getInstance().getSenha().equals(usuario.getSenha())) {
            return paginaInicialAdm();
        }

        Cliente cliente = clienteService.findClienteByEmailAndSenha(usuario.getEmail(), usuario.getSenha());
        if (cliente == null) {
            return "view/usuario/erro-login";
        }

        Viagem viagem = new Viagem();
        model.addAttribute("viagem", viagem);

        session.setAttribute("clienteId", cliente.getId());
        return "view/usuario/cliente/home-cliente";

    }

    @GetMapping("/voltarBuscarViagem")
    public String voltarBuscarViagem(Model model, HttpSession session){
        Viagem viagem = new Viagem();
        model.addAttribute("viagem", viagem);
        return "view/usuario/cliente/home-cliente";
    }

    @GetMapping("/paginaInicialAdm")
    public String paginaInicialAdm() {
        return "view/usuario/administrador/confirmacao-adm";
    }

    @GetMapping("/formularioParaCadastro")
    public String formularioParaCadastro(Model model) {
        Cliente cliente = new Cliente();

        model.addAttribute("cliente", cliente);

        return "view/usuario/cliente/formulario-cliente";
    }

    @PostMapping("/cadastrar")
    public String cadastrarCliente(@ModelAttribute("cliente") Cliente cliente, Model model) {
        clienteService.save(cliente);

        Viagem viagem = new Viagem();
        model.addAttribute("viagem", viagem);

        return "view/usuario/cliente/home-cliente";
    }

    @GetMapping("/verificarViagem")
    public String verificarViagem(@ModelAttribute("viagem") Viagem viagem, Model model) {
        List<Viagem> listaViagens = viagemService.findViagemByOrigemAndDestinoAndData(viagem.getOrigem(), viagem.getDestino(), viagem.getDataViagem());
        if (listaViagens.isEmpty()) {
            return "view/usuario/cliente/erro-viagens";
        }

        model.addAttribute("listaViagens", listaViagens);
        return "view/usuario/cliente/viagens-disponiveis";
    }

    @GetMapping("/areaPagamento")
    public String areaPagamento(@ModelAttribute("passagem") Passagem passagem, Model model, HttpSession session,
                                @RequestParam("idViagem") int idViagem, @RequestParam("nPoltrona") int nPoltrona) {

        session.setAttribute("viagemId", idViagem);

        int clienteId = (int) session.getAttribute("clienteId");
        model.addAttribute("idCliente", clienteId);


        Passagem verificarPassagem = passagemService.findPassagemByIdViagemAndNPoltrona(idViagem, nPoltrona);
        if (verificarPassagem != null) { // Poltrona já ocupada
            model.addAttribute("mensagemErro", "Poltrona Ocupada!");
            List<Viagem> listaViagens = viagemService.findViagemByOrigemAndDestinoAndData(verificarPassagem.getViagem().getOrigem(), verificarPassagem.getViagem().getDestino(), verificarPassagem.getViagem().getDataViagem());
            model.addAttribute("listaViagens", listaViagens); // Recarregar lista de viagens
            return "view/usuario/cliente/viagens-disponiveis";
        }

        passagem.setnPoltrona(nPoltrona);
        model.addAttribute("passagem", passagem);
        session.setAttribute("nPoltrona", nPoltrona);
        String redirectUrl = String.format("/usuarios/finalizarCompra?idViagem=%d&nPoltrona=%d&clienteId=%d",
                idViagem, nPoltrona, clienteId);

        return "redirect:" + redirectUrl;
    }

    @GetMapping("/finalizarCompra")
    public String finalizarCompra(HttpSession session, Model model) {
        int clienteId = (int) session.getAttribute("clienteId");
        int viagemId = (int) session.getAttribute("viagemId");
        int nPoltrona = (int) session.getAttribute("nPoltrona");

        Random random = new Random();
        double valor = 50 + (250 - 50) * random.nextDouble();
        String preco = String.format("%.2f", valor);

        session.setAttribute("preco", preco);
        model.addAttribute("preco", preco);

        Passagem passagem = new Passagem(clienteId, viagemId, nPoltrona);
        passagem.setPreco(preco);
        passagemService.save(passagem);

        Cliente cliente = clienteService.findById(clienteId);
        Viagem viagem = viagemService.findById(viagemId);

        passagem.setCliente(cliente);
        passagem.setViagem(viagem);

        session.setAttribute("passagem", passagem);
        model.addAttribute("passagem", passagem);

        return "view/usuario/cliente/confirmacao-compra";

    }
    @GetMapping("/compraConcluida")
    public String compraConcluida() {
        return "view/usuario/cliente/CompraConcluida";
    }

    @GetMapping("/pdfPassagem")
    public ResponseEntity<InputStreamResource> pdfPassagem(HttpSession session) {
        int clienteId = (int) session.getAttribute("clienteId");
        int viagemId = (int) session.getAttribute("viagemId");
        int nPoltrona = (int) session.getAttribute("nPoltrona");
        String preco = (String) session.getAttribute("preco");
        Passagem passagem = (Passagem) session.getAttribute("passagem");

        ByteArrayInputStream bis = generatePdf(passagem.getCliente().getNome(), passagem.getViagem().getOrigem(), passagem.getViagem().getDestino(), passagem.getnPoltrona(), passagem.getPreco());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=passagem.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

    private ByteArrayInputStream generatePdf(String nomeCliente, String origem, String destino, int poltrona, String preco) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter writer = new PdfWriter(out);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
            PdfFont fontNormal = PdfFontFactory.createFont(StandardFonts.HELVETICA);

            float margin = 20;
            document.setMargins(margin, margin, margin, margin);

            Paragraph header = new Paragraph("Passagem")
                    .setFont(font)
                    .setFontSize(18)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setBold()
                    .setMarginBottom(20);
            document.add(header);

            document.add(new Paragraph("Nome do Cliente:").setFont(font).setFontSize(12));
            document.add(new Paragraph(nomeCliente).setFont(fontNormal).setFontSize(12).setMarginBottom(10));

            document.add(new Paragraph("Origem:").setFont(font).setFontSize(12));
            document.add(new Paragraph(origem).setFont(fontNormal).setFontSize(12).setMarginBottom(10));

            document.add(new Paragraph("Destino:").setFont(font).setFontSize(12));
            document.add(new Paragraph(destino).setFont(fontNormal).setFontSize(12).setMarginBottom(10));

            document.add(new Paragraph("Poltrona:").setFont(font).setFontSize(12));
            document.add(new Paragraph(String.valueOf(poltrona)).setFont(fontNormal).setFontSize(12).setMarginBottom(10));

            document.add(new Paragraph("Preço:").setFont(font).setFontSize(12));
            document.add(new Paragraph("R$ " + preco).setFont(fontNormal).setFontSize(12).setMarginBottom(20));

            Paragraph footer = new Paragraph("Obrigado por viajar conosco!")
                    .setFont(font)
                    .setFontSize(12)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setMarginTop(30);
            document.add(footer);

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }

}
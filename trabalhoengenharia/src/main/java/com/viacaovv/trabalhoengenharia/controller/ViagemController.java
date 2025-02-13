package com.viacaovv.trabalhoengenharia.controller;

import com.viacaovv.trabalhoengenharia.entity.Motorista;
import com.viacaovv.trabalhoengenharia.entity.Onibus;
import com.viacaovv.trabalhoengenharia.entity.Viagem;
import com.viacaovv.trabalhoengenharia.service.MotoristaService;
import com.viacaovv.trabalhoengenharia.service.OnibusService;
import com.viacaovv.trabalhoengenharia.service.ViagemService;
import com.viacaovv.trabalhoengenharia.strategy.RelatorioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/viagens")
public class ViagemController {

    private ViagemService viagemService;
    private MotoristaService motoristaService;
    private OnibusService onibusService;

    @Autowired
    public ViagemController(ViagemService viagemService, MotoristaService motoristaService, OnibusService onibusService) {
        this.viagemService = viagemService;
        this.motoristaService = motoristaService;
        this.onibusService = onibusService;
    }

    @GetMapping("/lista")
    public String listaViagens(Model model) {
        List<Viagem> listaViagens = viagemService.findAll();

        model.addAttribute("viagens", listaViagens);

        // Este eh o caminho do arquivo da view
        return "view/viagem/lista-viagens";
    }

    @GetMapping("/formularioParaCadastro")
    public String formularioParaCadastro(Model model) {
        Viagem viagem = new Viagem();

        model.addAttribute("viagem", viagem);

        return "view/viagem/formulario-viagem";
    }

    @GetMapping("/formularioParaAtualizar")
    public String formularioParaAtualizar(@RequestParam("idViagem") int id, Model model) {
        Viagem viagem = viagemService.findById(id);

        model.addAttribute("viagem", viagem);

        return "view/viagem/formulario-viagem";
    }

    @PostMapping("/cadastrar")
    public String cadastrarViagem(@ModelAttribute("viagem") Viagem viagem) {

        int idMot = viagem.getIdMotorista();
        Motorista motorista = motoristaService.findById(idMot);

        int idOn = viagem.getIdOnibus();
        Onibus onibus = onibusService.findById(idOn);

        viagem.setMotorista(motorista);
        motorista.addViagem(viagem);

        viagem.setOnibus(onibus);
        onibus.addViagem(viagem);

        viagemService.save(viagem);
        return "redirect:/viagens/lista";

    }

    @GetMapping("/deletar")
    public String deletarViagem(@RequestParam("idViagem") int id) {
        viagemService.deleteById(id);

        return "redirect:/viagens/lista";
    }

    @GetMapping("/relatorioGeral")
    public String relatorioGeral(@RequestParam("idViagem") int id) {
        String chave = "geral";
        String idConvertido = Integer.toString(id);
        return "redirect:/download-pdf?chave=" + chave + "&valor=" + idConvertido;
    }

    @GetMapping("/relatorioPorOrigem")
    public String relatorioPorOrigem(@ModelAttribute("viagem") Viagem viagem) {
        String valor = viagem.getOrigem();
        String chave = "origem";
        return "redirect:/download-pdf?chave=" + chave + "&valor=" + valor;
    }

    @GetMapping("/relatorioPorData")
    public String relatorioPorData(@ModelAttribute("viagem") Viagem viagem) {
        String valor = viagem.getDataViagem();
        String chave = "data";
        return "redirect:/download-pdf?chave=" + chave + "&valor=" + valor;
    }
}

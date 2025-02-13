package com.viacaovv.trabalhoengenharia.controller;

import com.viacaovv.trabalhoengenharia.entity.Motorista;
import com.viacaovv.trabalhoengenharia.service.MotoristaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/motoristas")
public class MotoristaController {

    private MotoristaService motoristaService;

    @Autowired
    public MotoristaController(MotoristaService motoristaService) {
        this.motoristaService = motoristaService;
    }

    @GetMapping("/lista")
    public String listaMotoristas(Model model) {
        List<Motorista> listaDeMotoristas = motoristaService.findAll();

        model.addAttribute("motoristas", listaDeMotoristas);

        // Este eh o caminho do arquivo da view
        return "view/motorista/lista-motoristas";
    }

    @GetMapping("/formularioParaCadastro")
    public String formularioParaCadastro(Model model) {
        Motorista motorista = new Motorista();

        model.addAttribute("motorista", motorista);

        return "view/motorista/formulario-motorista";
    }

    @GetMapping("/formularioParaAtualizar")
    public String formularioParaAtualizar(@RequestParam("idMotorista") int id, Model model) {
        Motorista motorista = motoristaService.findById(id);

        model.addAttribute("motorista", motorista);

        return "view/motorista/formulario-motorista";
    }

    @PostMapping("/cadastrar")
    public String cadastrarMotorista(@Valid @ModelAttribute("motorista") Motorista motorista, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "view/motorista/formulario-motorista";
        } else {
            motoristaService.save(motorista);

            return "redirect:/motoristas/lista";
        }
    }

    @GetMapping("/deletar")
    public String deletarMotorista(@RequestParam("idMotorista") int id) {
        motoristaService.deleteById(id);

        return "redirect:/motoristas/lista";
    }

}

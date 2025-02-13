package com.viacaovv.trabalhoengenharia.controller;

import com.viacaovv.trabalhoengenharia.entity.Onibus;
import com.viacaovv.trabalhoengenharia.service.OnibusService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("onibus")
public class OnibusController {

    private OnibusService onibusService;

    @Autowired
    public OnibusController(OnibusService onibusService) {
        this.onibusService = onibusService;
    }

    @GetMapping("/lista")
    public String listaOnibus(Model model) {
        List<Onibus> listaDeOnibus = onibusService.findAll();

        model.addAttribute("onibus", listaDeOnibus);

        // Este eh o caminho do arquivo da view
        return "view/onibus/lista-onibus";
    }

    @GetMapping("/formularioParaCadastro")
    public String formualarioParacadastro(Model model) {
        Onibus onibus = new Onibus();

        model.addAttribute("onibus", onibus);

        return "view/onibus/formulario-onibus";
    }

    @GetMapping("formularioParaAtualizar")
    public String formularioParaAtualizar(@RequestParam("idOnibus") int id, Model model) {
        Onibus onibus = onibusService.findById(id);

        model.addAttribute("onibus", onibus);

        return "view/onibus/formulario-onibus";
    }

    @PostMapping("/cadastrar")
    public String cadastrarOnibus(@ModelAttribute("onibus") Onibus onibus) {
        onibusService.save(onibus);

        return "redirect:/onibus/lista";
    }

    @GetMapping("/deletar")
    public String deletarOnibus(@RequestParam("idOnibus") int id) {
        onibusService.deleteById(id);

        return "redirect:/onibus/lista";
    }

}

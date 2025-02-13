package com.viacaovv.trabalhoengenharia.strategy;



import com.viacaovv.trabalhoengenharia.entity.Viagem;
import com.viacaovv.trabalhoengenharia.service.ViagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Map;

@RestController
public class RelatorioController {

    private Map<String, RelatorioService> relatorioServices;

    @Autowired
    public RelatorioController(
            @Qualifier("relatorioGeral") RelatorioService relatorioGeral,
            @Qualifier("relatorioPorOrigem") RelatorioService relatorioPorOrigem,
            @Qualifier("relatorioPorData") RelatorioService relatorioPorData) {
        this.relatorioServices = Map.of(
                "geral", relatorioGeral,
                "origem", relatorioPorOrigem,
                "data", relatorioPorData
        );
    }

    @GetMapping("/download-pdf")
    public ResponseEntity<InputStreamResource> downloadPdf(@RequestParam("chave") String chave, @RequestParam("valor") String valor) {
        System.out.println("ESTA É A CHAVE:" + chave);
        RelatorioService relatorioService = relatorioServices.get(chave);
        if(relatorioService == null){
            return ResponseEntity.badRequest().build();
        }
        ByteArrayInputStream bis = relatorioService.gerarRelatorio(valor);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=relatorio.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
}
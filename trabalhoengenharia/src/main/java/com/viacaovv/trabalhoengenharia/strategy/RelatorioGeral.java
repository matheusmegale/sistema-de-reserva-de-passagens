package com.viacaovv.trabalhoengenharia.strategy;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.viacaovv.trabalhoengenharia.entity.Viagem;
import com.viacaovv.trabalhoengenharia.service.ViagemService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

@Service
@Qualifier("relatorioGeral")
public class RelatorioGeral implements RelatorioService{
    private ViagemService viagemService;

    public RelatorioGeral(ViagemService viagemService) {
        this.viagemService = viagemService;
    }

    @Override
    public ByteArrayInputStream gerarRelatorio(String idSemConverter) {
        int id = Integer.parseInt(idSemConverter);
        Viagem viagem = viagemService.findById(id);
        Document documento = new Document();
        ByteArrayOutputStream arquivoSaida = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(documento, arquivoSaida);
            documento.open();
            documento.add(new Paragraph("Relatório Geral : "));
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(viagem.relatorioGeral()));
            documento.close();
        } catch (DocumentException e) {
            e.printStackTrace(); // DocumentException é uma exceção verificada que pode ser lançada por iText
        }

        return new ByteArrayInputStream(arquivoSaida.toByteArray());
    }
}

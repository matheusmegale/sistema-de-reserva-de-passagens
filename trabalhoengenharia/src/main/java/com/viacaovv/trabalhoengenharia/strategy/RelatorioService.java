package com.viacaovv.trabalhoengenharia.strategy;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.viacaovv.trabalhoengenharia.entity.Viagem;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public interface RelatorioService {

    public ByteArrayInputStream gerarRelatorio(String parametro);
}
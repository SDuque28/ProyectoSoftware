package com.software.MyProyect.utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;

public class pdfBuilder {
    private Document document;
    private ByteArrayOutputStream outputStream;
    private PdfWriter writer;

    public pdfBuilder iniciarDocumento() {
        this.document = new Document();
        this.outputStream = new ByteArrayOutputStream();
        try {
            this.writer = PdfWriter.getInstance(document, outputStream);
            this.document.open();
        } catch (DocumentException e) {
            throw new RuntimeException("Error al iniciar el documento PDF", e);
        }
        return this;
    }

    public pdfBuilder agregarTitulo(String titulo) {
        try {
            this.document.add(new Paragraph(titulo));
        } catch (DocumentException e) {
            throw new RuntimeException("Error al agregar título al documento PDF", e);
        }
        return this;
    }

    public pdfBuilder agregarTexto(String texto) {
        try {
            document.add(new Paragraph(texto));
        } catch (DocumentException e) {
            throw new RuntimeException("Error al agregar el texto", e);
        }
        return this;
    }

    public pdfBuilder agregarTabla(PdfPTable tabla) {
        try {
            this.document.add(tabla);
        } catch (DocumentException e) {
            throw new RuntimeException("Error al agregar tabla al documento PDF", e);
        }
        return this;
    }

    public byte[] construir() {
        try {
            document.close();
            return outputStream.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Error al construir el PDF", e);
        }
    }
}

package com.software.MyProyect.utils;

import java.nio.charset.StandardCharsets;
import java.util.Stack;

public class XMLBuilder {
    private final StringBuilder xml;
    private final Stack<String> elementosAbiertos;

    public XMLBuilder() {
        xml = new StringBuilder();
        elementosAbiertos = new Stack<>();
    }

    public XMLBuilder iniciarDocumento(String rootElement) {
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        xml.append("<").append(rootElement).append(">\n");
        elementosAbiertos.push(rootElement);
        return this;
    }

    public XMLBuilder agregarElemento(String elemento) {
        xml.append("<").append(elemento).append(">\n");
        elementosAbiertos.push(elemento); 
        return this;
    }

    public XMLBuilder agregarSubElemento(String nombre, String valor) {
        xml.append("<").append(nombre).append(">") 
           .append(valor)
           .append("</").append(nombre).append(">\n");
        return this;
    }

    public XMLBuilder cerrarElemento() {
        String ultimoElemento = obtenerUltimoElementoAbierto();
        xml.append("</").append(ultimoElemento).append(">\n");
        elementosAbiertos.pop(); 
        return this;
    }

    public XMLBuilder cerrarDocumento() {
        xml.append("</").append(elementosAbiertos.peek()).append(">\n"); 
        return this;
    }

    public byte[] obtenerBytes() {
        return xml.toString().getBytes(StandardCharsets.UTF_8);
    }

    private String obtenerUltimoElementoAbierto() {
        if (elementosAbiertos.isEmpty()) {
            throw new IllegalStateException("No hay elementos abiertos.");
        }
        return elementosAbiertos.peek(); 
    }
}

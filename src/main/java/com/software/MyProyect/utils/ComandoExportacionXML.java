package com.software.MyProyect.utils;

import com.software.MyProyect.modelos.Factura;
import com.software.MyProyect.servicios.XMLGeneratorService;
import java.util.List;

public class ComandoExportacionXML implements ComandoExportacion {
    private final XMLGeneratorService xmlGeneratorService;

    public ComandoExportacionXML(XMLGeneratorService xmlGeneratorService) {
        this.xmlGeneratorService = xmlGeneratorService;
    }

    @Override
    public byte[] ejecutar(List<Factura> facturas) {
        return xmlGeneratorService.exportar(facturas);
    }
}

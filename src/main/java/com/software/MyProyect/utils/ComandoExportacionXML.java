package com.software.MyProyect.utils;

import com.software.MyProyect.servicios.XMLGeneratorService;

public class ComandoExportacionXML implements ComandoExportacion {
    private final XMLGeneratorService xmlGeneratorService;

    public ComandoExportacionXML(XMLGeneratorService xmlGeneratorService) {
        this.xmlGeneratorService = xmlGeneratorService;
    }

    @Override
    public byte[] ejecutar(FacturaCollection facturaCollection) {
        return xmlGeneratorService.exportar(facturaCollection);
    }
}

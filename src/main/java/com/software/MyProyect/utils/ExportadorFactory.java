package com.software.MyProyect.utils;

import org.springframework.stereotype.Component;

import com.software.MyProyect.servicios.ExportadorExcelService;
import com.software.MyProyect.servicios.ExportadorPDFService;

@Component
public class ExportadorFactory {
    private static ExportadorFactory instancia;

    private ExportadorFactory() {}

    // Método para obtener la única instancia
    public static ExportadorFactory getInstancia() {
        if (instancia == null) {
            instancia = new ExportadorFactory();
        }
        return instancia;
    }

    public ExportadorInforme getExportador(String tipo) {
        switch (tipo.toLowerCase()) {
            case "excel":
                return new ExcelAdapter(new ExportadorExcelService());
            case "pdf":
                return new ExportadorPDFService();
            default:
                throw new IllegalArgumentException("Formato de exportación no soportado: " + tipo);
        }
    }
}

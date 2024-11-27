package com.software.MyProyect.utils;

import com.software.MyProyect.servicios.ExportadorExcelService;
import com.software.MyProyect.servicios.ExportadorPDFService;
import org.springframework.stereotype.Component;

@Component
public class ExportadorFactory {
    // Instancia única de la fábrica
    private static ExportadorFactory instancia;

    // Constructor privado para evitar instanciación externa
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
                return new ExportadorExcelService();
            case "pdf":
                return new ExportadorPDFService();
            default:
                throw new IllegalArgumentException("Formato de exportación no soportado: " + tipo);
        }
    }
}

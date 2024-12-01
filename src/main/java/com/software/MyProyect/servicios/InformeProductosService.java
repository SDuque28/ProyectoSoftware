package com.software.MyProyect.servicios;

import com.software.MyProyect.utils.ExportadorInformeProductos;
import com.software.MyProyect.utils.GeneradorInformeProductos;
import java.util.List;
import java.util.Map;

public class InformeProductosService {
    private final GeneradorInformeProductos generadorInformeProductos;
    private final ExportadorInformeProductos exportadorInformeProductos;

    public InformeProductosService(GeneradorInformeProductos generadorInformeProductos,
                                   ExportadorInformeProductos exportadorInformeProductos) {
        this.generadorInformeProductos = generadorInformeProductos;
        this.exportadorInformeProductos = exportadorInformeProductos;
    }

    public byte[] generarPDFTop10() {
        List<Map<String, Object>> datosInforme = generadorInformeProductos.generarDatosTop10();
        return exportadorInformeProductos.exportarInformeTop10(datosInforme);
    }
}

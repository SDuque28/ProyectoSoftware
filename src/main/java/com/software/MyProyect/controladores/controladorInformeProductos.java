package com.software.MyProyect.controladores;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.software.MyProyect.servicios.InformeProductosService;
import com.software.MyProyect.utils.HttpResponseBuilder;

@RestController
@RequestMapping("/informes/productos")
public class controladorInformeProductos {

    private final InformeProductosService informeProductosService;

    public controladorInformeProductos(InformeProductosService informeProductosService) {
        this.informeProductosService = informeProductosService;
    }

    /**
     * Genera un informe en PDF del Top 10 de productos más vendidos.
     * @return El archivo PDF como respuesta.
     */
    @GetMapping("/top10")
    public ResponseEntity<byte[]> generarInformeTop10() {
        try {
            byte[] pdfBytes = informeProductosService.generarPDFTop10();
            return HttpResponseBuilder.crearRespuestaConArchivo(pdfBytes, "Top10_Productos.pdf", "application/pdf");
        } catch (Exception e) {
            return HttpResponseBuilder.crearRespuestaConError("Error al generar el informe: " + e.getMessage());
        }
    }
}

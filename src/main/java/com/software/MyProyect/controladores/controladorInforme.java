package com.software.MyProyect.controladores;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.software.MyProyect.modelos.Factura;
import com.software.MyProyect.utils.ExportadorFactory;
import com.software.MyProyect.utils.ExportadorInforme;

@RestController
@RequestMapping("/informes")
public class controladorInforme {

    @Autowired
    private ExportadorFactory exportadorFactory;

    @PostMapping("/exportar")
    public ResponseEntity<byte[]> exportarInforme(
        @RequestBody List<Factura> facturas,
        @RequestParam String formato,
        @RequestParam(required = false) String mes) {

        try {
            // Filtrar las facturas si se proporciona un mes
            if (mes != null) {
                System.out.println("Valor recibido para 'mes': " + mes);
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate inicioMes = LocalDate.parse(mes.trim(), formatter).withDayOfMonth(1);
                    LocalDate finMes = inicioMes.plusMonths(1).minusDays(1);

                    System.out.println("Inicio del mes: " + inicioMes + ", Fin del mes: " + finMes);

                    facturas = facturas.stream()
                            .filter(f -> {
                                LocalDate fechaFactura = f.getFecha();
                                return (fechaFactura.isEqual(inicioMes) || fechaFactura.isAfter(inicioMes)) &&
                                        (fechaFactura.isBefore(finMes) || fechaFactura.isEqual(finMes));
                            })
                            .collect(Collectors.toList());
                } catch (Exception e) {
                    e.printStackTrace(); // Registrar la excepción en la consola para más detalles
                    return ResponseEntity.badRequest()
                            .header(HttpHeaders.CONTENT_DISPOSITION, "Error al procesar el parámetro 'mes'.")
                            .body(null);
                }
            }

            // Validar si hay facturas para exportar
            if (facturas.isEmpty()) {
                return ResponseEntity.badRequest()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "No hay facturas para el mes proporcionado.")
                        .body(null);
            }

            // Obtener el exportador adecuado
            ExportadorInforme exportador = exportadorFactory.getExportador(formato);

            // Generar el contenido del informe
            byte[] contenido = exportador.exportar(facturas);

            // Configurar el nombre del archivo
            String filename = "informe." + (formato.equals("excel") ? "xlsx" : "pdf");
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                    .contentType(formato.equals("excel") ? MediaType.APPLICATION_OCTET_STREAM : MediaType.APPLICATION_PDF)
                    .body(contenido);
                    
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }
}

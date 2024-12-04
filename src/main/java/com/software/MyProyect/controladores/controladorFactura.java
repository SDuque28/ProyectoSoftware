package com.software.MyProyect.controladores;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.software.MyProyect.modelos.Factura;
import com.software.MyProyect.modelos.ProductoFactura;
import com.software.MyProyect.modelos.Productos;
import com.software.MyProyect.servicios.FacturaMediator;
import com.software.MyProyect.servicios.XMLGeneratorService;
import com.software.MyProyect.servicios.servicioFactura;
import com.software.MyProyect.utils.ComandoExportacion;
import com.software.MyProyect.utils.ComandoExportacionXML;
import com.software.MyProyect.utils.FacturaCollection;
import com.software.MyProyect.utils.FacturaIterator;

@RestController
@RequestMapping("/facturas")
public class controladorFactura {
    private final FacturaMediator facturaMediator;
    private final servicioFactura facturaService;
    private final Map<String, ComandoExportacion> comandosExportacion;

    @Autowired
    public controladorFactura(servicioFactura facturaService, XMLGeneratorService xmlGeneratorService, FacturaMediator facturaMediator) {
        this.facturaService = facturaService;
        this.facturaMediator = facturaMediator;
        this.comandosExportacion = new HashMap<>();
        this.comandosExportacion.put("xml", new ComandoExportacionXML(xmlGeneratorService));
    }

    @PostMapping("/add")
    public ResponseEntity<Factura> addFactura(@RequestBody Factura factura) {
        
        Factura nuevaFactura = facturaMediator.crearFactura(factura);
        return ResponseEntity.ok(nuevaFactura);
    }

    @GetMapping("/all")
    public List<Factura> getAllFacturas() {
        return facturaService.getAllFacturas();
    }

    @GetMapping("/generarExportacion")
    public ResponseEntity<byte[]> generarExportacion(@RequestParam String tipo) {
        FacturaCollection facturaCollection = facturaService.getFacturaCollection();
        FacturaIterator facturaIterator = facturaCollection.createIterator();
    
        ComandoExportacion comando = comandosExportacion.get(tipo);

        if (comando == null) {
            return ResponseEntity.badRequest().body("Tipo de exportación no soportado".getBytes());
        }

        byte[] datosExportados = comando.ejecutar(facturaCollection);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", tipo.equals("xml") ? "application/xml" : "application/json");
        headers.add("Content-Disposition", "attachment; filename=facturas." + tipo);

        return ResponseEntity.ok().headers(headers).body(datosExportados);
    }
}

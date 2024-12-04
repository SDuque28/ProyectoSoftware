package com.software.MyProyect.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.software.MyProyect.modelos.Factura;
import com.software.MyProyect.modelos.ProductoFactura;
import com.software.MyProyect.utils.Mediator;

@Component
public class FacturaMediator implements Mediator {

    private final servicioFactura facturaService;

    @Autowired
    public FacturaMediator(servicioFactura facturaService) {
        this.facturaService = facturaService;
    }

    @Override
    public Factura crearFactura(Factura factura) {
        double subtotal = 0.0;
        double totalImpuestos = 0.0;

        for (ProductoFactura productoFactura : factura.getProductosVendidos()) {
            subtotal += productoFactura.getTotal();
        }

        totalImpuestos = subtotal * 0.15;

        factura.setSubtotal(subtotal);
        factura.setTotalImpuestos(totalImpuestos);
        factura.setTotal(subtotal + totalImpuestos);

        Factura nuevaFactura = facturaService.saveFactura(factura);
        return nuevaFactura;
    }
}

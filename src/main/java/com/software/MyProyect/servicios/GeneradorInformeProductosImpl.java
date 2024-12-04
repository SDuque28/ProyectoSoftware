package com.software.MyProyect.servicios;

import com.software.MyProyect.modelos.Factura;
import com.software.MyProyect.modelos.ProductoFactura;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GeneradorInformeProductosImpl implements GeneradorInformeProductos {

    private final List<Factura> facturas; // Suponemos que las facturas vienen de un repositorio o similar.

    public GeneradorInformeProductosImpl(List<Factura> facturas) {
        this.facturas = facturas;
    }

    @Override
    public List<Map<String, Object>> generarDatosTop10() {
        // Map para sumar la cantidad total por producto
        Map<String, Integer> productosCantidad = new HashMap<>();

        // Recorrer las facturas y sus productos
        for (Factura factura : facturas) {
            for (ProductoFactura producto : factura.getProductosVendidos()) {
                productosCantidad.put(
                        producto.getDescripcion(),
                        productosCantidad.getOrDefault(producto.getDescripcion(), 0) + producto.getCantidad()
                );
            }
        }

        // Ordenar productos por cantidad descendente y tomar el top 10
        return productosCantidad.entrySet().stream()
                .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                .limit(10)
                .map(entry -> {
                    Map<String, Object> producto = new HashMap<>();
                    producto.put("producto", entry.getKey());
                    producto.put("cantidad", entry.getValue());
                    producto.put("facturas", obtenerFacturasAsociadas(entry.getKey()));
                    return producto;
                })
                .collect(Collectors.toList());
    }

    private String obtenerFacturasAsociadas(String productoDescripcion) {
        return facturas.stream()
                .filter(factura -> factura.getProductosVendidos().stream()
                        .anyMatch(producto -> producto.getDescripcion().equals(productoDescripcion)))
                .map(Factura::getId)
                .collect(Collectors.joining(", "));
    }
}

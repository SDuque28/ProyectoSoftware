package com.software.MyProyect.servicios;
import com.software.MyProyect.modelos.Factura;
import com.software.MyProyect.modelos.ProductoFactura;
import com.software.MyProyect.modelos.Productos;
import com.software.MyProyect.repositorios.repositorioFactura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class servicioFactura {
    private final repositorioFactura facturaRepository;

    @Autowired
    public servicioFactura(repositorioFactura facturaRepository) {
        this.facturaRepository = facturaRepository;
    }

    public Factura saveFactura(Factura factura) {
        return facturaRepository.save(factura);
    }

    public List<Factura> getAllFacturas() {
        return facturaRepository.findAll();
    }

    public void agregarProductosAFactura(Factura factura, List<Productos> productos, List<Integer> cantidades) {
        for (int i = 0; i < productos.size(); i++) {
            Productos producto = productos.get(i);
            int cantidad = cantidades.get(i);

            ProductoFactura productoFactura = new ProductoFactura(producto, cantidad);
            factura.getProductosVendidos().add(productoFactura);
        }
    }
}

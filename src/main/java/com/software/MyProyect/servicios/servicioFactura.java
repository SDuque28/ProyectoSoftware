package com.software.MyProyect.servicios;
import com.software.MyProyect.modelos.Factura;
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
}

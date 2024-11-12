package com.software.MyProyect.servicios;
import com.software.MyProyect.repositorios.repositorioDetalle;
import com.software.MyProyect.modelos.DetalleFactura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class servicioDetalle {
    private final repositorioDetalle detalleFacturaRepository;

    @Autowired
    public servicioDetalle(repositorioDetalle detalleFacturaRepository) {
        this.detalleFacturaRepository = detalleFacturaRepository;
    }

    public DetalleFactura saveDetalleFactura(DetalleFactura detalleFactura) {
        return detalleFacturaRepository.save(detalleFactura);
    }

    public List<DetalleFactura> getAllDetalleFacturas() {
        return detalleFacturaRepository.findAll();
    }
}

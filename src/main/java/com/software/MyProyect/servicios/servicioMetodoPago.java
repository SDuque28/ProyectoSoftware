package com.software.MyProyect.servicios;
import com.software.MyProyect.modelos.MetodoPago;
import com.software.MyProyect.repositorios.repositorioMetodoPago;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class servicioMetodoPago {
    private final repositorioMetodoPago metodoPagoRepository;

    @Autowired
    public servicioMetodoPago(repositorioMetodoPago metodoPagoRepository) {
        this.metodoPagoRepository = metodoPagoRepository;
    }

    public MetodoPago saveMetodoPago(MetodoPago metodoPago) {
        return metodoPagoRepository.save(metodoPago);
    }

    public List<MetodoPago> getAllMetodosPago() {
        return metodoPagoRepository.findAll();
    }
}

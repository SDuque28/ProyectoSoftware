package com.software.MyProyect.servicios;
import com.software.MyProyect.repositorios.repositorioImpuesto;
import com.software.MyProyect.modelos.Impuestos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class servicioImpuesto {
    private final repositorioImpuesto impuestoRepository;

    @Autowired
    public servicioImpuesto(repositorioImpuesto impuestoRepository) {
        this.impuestoRepository = impuestoRepository;
    }

    public Impuestos saveImpuesto(Impuestos impuesto) {
        return impuestoRepository.save(impuesto);
    }

    public List<Impuestos> getAllImpuestos() {
        return impuestoRepository.findAll();
    }
}

package com.software.MyProyect.servicios;
import com.software.MyProyect.modelos.Clientes;
import com.software.MyProyect.repositorios.repositorioImpuesto;
import com.software.MyProyect.modelos.Impuestos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Impuestos getImpuestoById(String id) {
        Optional<Impuestos> impuesto = impuestoRepository.findById(id);
        if (impuesto.isPresent()) {
            return impuesto.get();
        } else {
            throw new IllegalArgumentException("Cliente con ID " + id + " no encontrado.");
        }
    }

    public Impuestos updateImpuesto(String id, Impuestos impuestos) {
        Impuestos impuestos1 = impuestoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Impuesto con ID " + id + " no encontrado."));

        // Actualizar los campos del cliente
        impuestos1.setNombre(impuestos.getNombre());
        impuestos1.setPorcentaje(impuestos.getPorcentaje());

        return impuestoRepository.save(impuestos1);
    }

    public boolean deleteImpuesto(String id) {
        boolean respuesta = true;
        if (!impuestoRepository.existsById(id)) {
            System.out.println("Impuesto con ID " + id + " no encontrado.");
            respuesta = false;
        }

        impuestoRepository.deleteById(id);
        return respuesta;
    }
}

package com.software.MyProyect.servicios;
import com.software.MyProyect.modelos.Inventarios;
import com.software.MyProyect.repositorios.repositorioInventario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class servicioInventario {
    private final repositorioInventario inventarioRepository;

    @Autowired
    public servicioInventario(repositorioInventario inventarioRepository) {
        this.inventarioRepository = inventarioRepository;
    }

    public Inventarios saveInventario(Inventarios inventario) {
        return inventarioRepository.save(inventario);
    }

    public List<Inventarios> getAllInventarios() {
        return inventarioRepository.findAll();
    }
}

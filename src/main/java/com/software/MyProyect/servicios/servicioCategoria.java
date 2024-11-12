package com.software.MyProyect.servicios;
import com.software.MyProyect.modelos.Categorias;
import com.software.MyProyect.repositorios.repositorioCategoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class servicioCategoria {
    private final repositorioCategoria categoriaRepository;

    @Autowired
    public servicioCategoria(repositorioCategoria categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public Categorias saveCategoria(Categorias categoria) {
        return categoriaRepository.save(categoria);
    }

    public List<Categorias> getAllCategorias() {
        return categoriaRepository.findAll();
    }
}

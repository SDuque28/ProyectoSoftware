package com.software.MyProyect.servicios;
import com.software.MyProyect.repositorios.repositorioInforme;
import com.software.MyProyect.modelos.Informes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class servicioInforme {
    private final repositorioInforme informeRepository;

    @Autowired
    public servicioInforme(repositorioInforme informeRepository) {
        this.informeRepository = informeRepository;
    }

    public Informes saveInforme(Informes informe) {
        return informeRepository.save(informe);
    }

    public List<Informes> getAllInformes() {
        return informeRepository.findAll();
    }
}

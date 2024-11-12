package com.software.MyProyect.controladores;
import com.software.MyProyect.modelos.Informes;
import com.software.MyProyect.servicios.servicioInforme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/informes")
public class controladorInforme {
    private final servicioInforme informeService;

    @Autowired
    public controladorInforme(servicioInforme informeService) {
        this.informeService = informeService;
    }

    @PostMapping("/add")
    public Informes addInforme(@RequestBody Informes informe) {
        return informeService.saveInforme(informe);
    }

    @GetMapping("/all")
    public List<Informes> getAllInformes() {
        return informeService.getAllInformes();
    }
}

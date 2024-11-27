package com.software.MyProyect.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.software.MyProyect.modelos.Factura;
import com.software.MyProyect.servicios.GeneradorInformeProductosImpl;
import com.software.MyProyect.utils.ExportadorInformeProductos;
import java.util.List;

@RestController
@RequestMapping("/api/informes")
public class controladorInformeProductos {

    @Autowired
    private GeneradorInformeProductosImpl generadorInformeProductos;

    @Autowired
    private ExportadorInformeProductos exportadorInformeProductos;

}

package com.software.MyProyect.utils;

import com.software.MyProyect.modelos.Factura;
import java.util.List;

public interface ComandoExportacion {
    byte[] ejecutar(List<Factura> facturas);
}

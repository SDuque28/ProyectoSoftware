package com.software.MyProyect.utils;

import java.util.List;

import com.software.MyProyect.modelos.Factura;

public interface ExportadorInforme {
    byte[] exportar(List<Factura> facturas);
}

package com.software.MyProyect.utils;

import com.software.MyProyect.modelos.Factura;

public interface FacturaIterator {
    boolean hasNext();
    Factura next();
}

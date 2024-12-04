package com.software.MyProyect.utils;

import java.util.Iterator;
import java.util.List;

import com.software.MyProyect.modelos.Factura;

public class FacturaIteratorImpl implements FacturaIterator {
    private final Iterator<Factura> iterator;

    public FacturaIteratorImpl(List<Factura> facturas) {
        this.iterator = facturas.iterator();
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public Factura next() {
        return iterator.next();
    }
}

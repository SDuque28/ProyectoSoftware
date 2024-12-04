package com.software.MyProyect.utils;

import java.util.List;

import com.software.MyProyect.modelos.Factura;

public class FacturaCollectionImpl implements FacturaCollection {
    private final List<Factura> facturas;

    public FacturaCollectionImpl(List<Factura> facturas) {
        this.facturas = facturas;
    }

    @Override
    public FacturaIterator createIterator() {
        return new FacturaIteratorImpl(this.facturas);
    }
}

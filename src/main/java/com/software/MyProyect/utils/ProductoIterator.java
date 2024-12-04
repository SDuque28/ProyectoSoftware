package com.software.MyProyect.utils;

import com.software.MyProyect.modelos.ProductoFactura;
import java.util.List;

public class ProductoIterator {
    private List<ProductoFactura> productos;
    private int position = 0;

    public ProductoIterator(List<ProductoFactura> productos) {
        this.productos = productos;
    }

    public boolean hasNext() {
        return position < productos.size();
    }

    public ProductoFactura next() {
        if (!hasNext()) {
            throw new IllegalStateException("No more elements");
        }
        return productos.get(position++);
    }
}


package com.software.MyProyect.modelos;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "productoFactura")
public class ProductoFactura {
    private String productoId;
    private String descripcion;
    private int cantidad;
    private double precioUnitario;
    private double total;

    public ProductoFactura(Productos producto, int cantidad) {
        this.productoId = producto.getId();
        this.descripcion = producto.getDescripcion();
        this.cantidad = cantidad;
        this.precioUnitario = producto.getPrecioVenta();
        this.total = cantidad * producto.getPrecioVenta();
    }

    // Getters y setters
    public String getProductoId() {
        return productoId;
    }

    public void setProductoId(String productoId) {
        this.productoId = productoId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public double getTotal() {
        return total;
    }
}

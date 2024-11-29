package com.software.MyProyect.modelos;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;


@Document(collection = "facturas")
public class Factura {
    @Id
    private String id;
    private String codigo;
    private LocalDate fecha;
    private double subtotal;
    private double totalImpuestos;
    private double total;
    private String estado;
    private String idCliente;
    private String idMetodoPago;

    public Factura(String id, String codigo, LocalDate fecha, double subtotal, double totalImpuestos, double total, String estado, String idCliente, String idMetodoPago) {
        this.id = id;
        this.codigo = codigo;
        this.fecha = fecha;
        this.subtotal = subtotal;
        this.totalImpuestos = totalImpuestos;
        this.total = total;
        this.estado = estado;
        this.idCliente = idCliente;
        this.idMetodoPago = idMetodoPago;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getTotalImpuestos() {
        return totalImpuestos;
    }

    public void setTotalImpuestos(double totalImpuestos) {
        this.totalImpuestos = totalImpuestos;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getIdMetodoPago() {
        return idMetodoPago;
    }

    public void setIdMetodoPago(String idMetodoPago) {
        this.idMetodoPago = idMetodoPago;
    }
}

package com.software.MyProyect.modelos;

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "Factura")
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
    private List<ProductoFactura> productosVendidos;

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
        productosVendidos = new ArrayList<ProductoFactura>();
    }

    public Factura() {
        productosVendidos = new ArrayList<>();
    }

    @XmlElement
    public String getId() {
        return id;
    }

    @XmlElement
    public List<ProductoFactura> getProductosVendidos() {
        return productosVendidos;
    }

    public void setProductosVendidos(List<ProductoFactura> productosVendidos) {
        this.productosVendidos = productosVendidos;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlElement
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @XmlElement
    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    @XmlElement
    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    @XmlElement
    public double getTotalImpuestos() {
        return totalImpuestos;
    }

    public void setTotalImpuestos(double totalImpuestos) {
        this.totalImpuestos = totalImpuestos;
    }

    @XmlElement
    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @XmlElement
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @XmlElement
    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    @XmlElement
    public String getIdMetodoPago() {
        return idMetodoPago;
    }

    public void setIdMetodoPago(String idMetodoPago) {
        this.idMetodoPago = idMetodoPago;
    }
}

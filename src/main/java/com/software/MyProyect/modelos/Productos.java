package com.software.MyProyect.modelos;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "productos")
public class Productos {
    @Id
    private String id;
    private String codigo;
    private String nombre;
    private String descripcion;
    private double precioVenta;
    private String impuestoId;
    private String medida;
    private String categoriaId;
    private int stock;

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
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("El código no puede estar vacío.");
        }
        this.codigo = codigo;
    }

    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){
        if(nombre == null || nombre.trim().isEmpty() ){
            throw new IllegalArgumentException("El nombre no puede estar vacio.");
        }
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        if (precioVenta <= 0) {
            throw new IllegalArgumentException("El precio de venta debe ser mayor a 0.");
        }
        this.precioVenta = precioVenta;
    }

    public String getImpuestoId() {
        return impuestoId;
    }

    public void setImpuestoId(String impuestoId) {
        this.impuestoId = impuestoId;
    }

    public String getMedida() {
        return medida;
    }

    public void setMedida(String medida) {
        this.medida = medida;
    }

    public String getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(String categoriaId) {
        this.categoriaId = categoriaId;
    }

    public int getStock(){
        return stock;
    }

    public void setStock(int stock){
        if(stock <= 0){
            throw new IllegalArgumentException("El stock debe ser mayor a 0");
        }
        this.stock = stock;
    }
}


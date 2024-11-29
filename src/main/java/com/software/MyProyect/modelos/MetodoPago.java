package com.software.MyProyect.modelos;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "metodosPago")
public class MetodoPago {
    @Id
    private String id;
    private String descripcion;  // Description of the payment method (e.g., "Credit Card", "Cash", etc.)
    private String identificador;  // Unique identifier for the payment method (e.g., "VISA", "MasterCard", "CASH")

    public MetodoPago(String id, String descripcion, String identificador) {
        this.id = id;
        this.descripcion = descripcion;
        this.identificador = identificador;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }
}

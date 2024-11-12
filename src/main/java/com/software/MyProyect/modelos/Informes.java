package com.software.MyProyect.modelos;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Document(collection = "informes")
public class Informes {
    @Id
    private String id;
    private String tipoInforme;
    private Date fecha;
    private String datosJson;

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipoInforme() {
        return tipoInforme;
    }

    public void setTipoInforme(String tipoInforme) {
        this.tipoInforme = tipoInforme;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDatosJson() {
        return datosJson;
    }

    public void setDatosJson(String datosJson) {
        this.datosJson = datosJson;
    }
}

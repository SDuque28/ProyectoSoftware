package com.software.MyProyect.utils;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class HttpResponseBuilder {
    public static ResponseEntity<byte[]> crearRespuestaConArchivo(byte[] contenido, String nombreArchivo, String tipoContenido) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=" + nombreArchivo);
        headers.add("Content-Type", tipoContenido);
        return new ResponseEntity<>(contenido, headers, HttpStatus.OK);
    }

    public static ResponseEntity<byte[]> crearRespuestaConError(String mensajeError) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(mensajeError.getBytes());
    }
}

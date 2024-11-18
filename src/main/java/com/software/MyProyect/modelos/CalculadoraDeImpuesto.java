/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.software.MyProyect.modelos;

/**
 *
 * @author az230
 */
public class CalculadoraDeImpuesto {
    public double calcularPrecioConImpuesto(double precioVenta, double porcentajeImpuesto) {
        if (porcentajeImpuesto < 0) {
            throw new IllegalArgumentException("El porcentaje de impuesto no puede ser negativo.");
        }
        return precioVenta + (precioVenta * porcentajeImpuesto / 100);
    }
}


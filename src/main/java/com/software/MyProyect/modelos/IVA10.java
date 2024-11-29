/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.software.MyProyect.modelos;

/**
 *
 * @author az230
 */
public class IVA10 implements Impuesto {
    @Override
    public double calcular(double base) {
        return base * 0.10;
    }
}

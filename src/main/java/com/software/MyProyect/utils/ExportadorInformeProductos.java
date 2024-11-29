/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.software.MyProyect.utils;

import java.util.List;
import java.util.Map;

/**
 *
 * @author az230
 */
public interface ExportadorInformeProductos {
    byte[] exportarInformeTop10(List<Map<String, Object>> informe);
}

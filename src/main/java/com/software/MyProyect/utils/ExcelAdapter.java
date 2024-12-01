package com.software.MyProyect.utils;

import com.software.MyProyect.modelos.Factura;
import com.software.MyProyect.servicios.ExportadorExcelService;
import java.util.List;

public class ExcelAdapter implements ExportadorInforme{
    private ExportadorExcelService exportadorExcel;

    public ExcelAdapter(ExportadorExcelService exportadorExcel) {
        this.exportadorExcel = exportadorExcel;
    }

    @Override
    public byte[] exportar(List<Factura> facturas) {
        return exportadorExcel.exportarExcel(facturas);
    }
}

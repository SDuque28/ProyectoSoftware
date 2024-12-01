package com.software.MyProyect.servicios;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.software.MyProyect.modelos.Factura;
import com.software.MyProyect.repositorios.repositorioFactura;
import com.software.MyProyect.utils.ExportadorInforme;
import com.software.MyProyect.utils.XMLBuilder;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import java.util.List;

@Service
public class XMLGeneratorService implements ExportadorInforme {

    @Override
    public byte[] exportar(List<Factura> facturas) {
        XMLBuilder xmlBuilder = new XMLBuilder();
        xmlBuilder.iniciarDocumento("Facturas");

        for (Factura factura : facturas) {
            xmlBuilder.agregarElemento("Factura")
                    .agregarSubElemento("ID", factura.getId())
                    .agregarSubElemento("ClienteID", factura.getIdCliente())
                    .agregarSubElemento("Subtotal", String.valueOf(factura.getSubtotal()))
                    .agregarSubElemento("TotalImpuestos", String.valueOf(factura.getTotalImpuestos()))
                    .agregarSubElemento("Total", String.valueOf(factura.getTotal()))
                    .cerrarElemento(); 
        }

        xmlBuilder.cerrarDocumento();

        return xmlBuilder.obtenerBytes();
    }
}
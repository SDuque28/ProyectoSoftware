package com.software.MyProyect.servicios;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.software.MyProyect.modelos.Factura;
import com.software.MyProyect.repositorios.repositorioFactura;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

@Service
public class XMLGeneratorService {
    
    @Autowired
    private repositorioFactura facturaRepository;

    public String generarXMLFactura(String facturaId) throws Exception {
        Optional<Factura> optionalFactura = facturaRepository.findById(facturaId);
        if (optionalFactura.isEmpty()) {
            throw new Exception("Factura no encontrada");
        }

        Factura factura = optionalFactura.get();
        JAXBContext context = JAXBContext.newInstance(Factura.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        StringWriter writer = new StringWriter();
        marshaller.marshal(factura, writer);

        return writer.toString(); 
    }

    public Factura obtenerDatosDesdeXML(String xml) throws Exception {
        JAXBContext context = JAXBContext.newInstance(Factura.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        StringReader reader = new StringReader(xml);
        return (Factura) unmarshaller.unmarshal(reader);
    }
}

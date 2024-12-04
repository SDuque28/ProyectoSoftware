package com.software.MyProyect.servicios;

import org.springframework.stereotype.Service;

import com.software.MyProyect.modelos.Factura;
import com.software.MyProyect.modelos.ProductoFactura;
import com.software.MyProyect.utils.ExportadorInformeXML;
import com.software.MyProyect.utils.FacturaCollection;
import com.software.MyProyect.utils.FacturaIterator;
import com.software.MyProyect.utils.ProductoIterator;
import com.software.MyProyect.utils.XMLBuilder;

@Service
public class XMLGeneratorService implements ExportadorInformeXML {

    @Override
    public byte[] exportar(FacturaCollection facturaCollection) {
        FacturaIterator facturaIterator = facturaCollection.createIterator();

        XMLBuilder builder = new XMLBuilder()
            .iniciarDocumento("Facturas");
        
        while (facturaIterator.hasNext()) {
            Factura factura = facturaIterator.next();
            builder.agregarElemento("Factura")
               .agregarSubElemento("FacturaID", factura.getId())
               .agregarSubElemento("ClienteID", factura.getIdCliente());

            // Iterar sobre los productos de la factura
            ProductoIterator productoIterator = new ProductoIterator(factura.getProductosVendidos());
            while (productoIterator.hasNext()) {
                ProductoFactura producto = productoIterator.next();
                builder.agregarElemento("Producto")
                    .agregarSubElemento("ProductoID", producto.getProductoId())
                    .agregarSubElemento("Descripcion", producto.getDescripcion())
                    .cerrarElemento(); // Producto
            }

            builder.cerrarElemento(); // Factura
        }

        builder.cerrarDocumento();
        return builder.obtenerBytes();
    }
}
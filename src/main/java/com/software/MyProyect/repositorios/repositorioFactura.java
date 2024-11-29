package com.software.MyProyect.repositorios;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.software.MyProyect.modelos.Factura;

public interface repositorioFactura extends MongoRepository<Factura, String>{
}

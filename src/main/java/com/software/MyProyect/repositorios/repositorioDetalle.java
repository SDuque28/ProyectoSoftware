package com.software.MyProyect.repositorios;
import com.software.MyProyect.modelos.DetalleFactura;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface repositorioDetalle extends MongoRepository<DetalleFactura, String>{
}

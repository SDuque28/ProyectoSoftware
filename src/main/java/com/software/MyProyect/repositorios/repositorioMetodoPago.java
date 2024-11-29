package com.software.MyProyect.repositorios;
import com.software.MyProyect.modelos.MetodoPago;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface repositorioMetodoPago extends MongoRepository<MetodoPago, String> {
}

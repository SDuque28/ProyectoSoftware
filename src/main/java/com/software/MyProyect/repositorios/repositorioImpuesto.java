package com.software.MyProyect.repositorios;
import com.software.MyProyect.modelos.Impuestos;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface repositorioImpuesto extends MongoRepository<Impuestos, String>{
}

package com.software.MyProyect.repositorios;
import com.software.MyProyect.modelos.Inventarios;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface repositorioInventario extends MongoRepository<Inventarios, String>{
}

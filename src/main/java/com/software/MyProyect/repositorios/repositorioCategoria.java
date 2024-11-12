package com.software.MyProyect.repositorios;
import com.software.MyProyect.modelos.Categorias;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface repositorioCategoria extends MongoRepository<Categorias, String> {
}

package com.software.MyProyect.repositorios;
import com.software.MyProyect.modelos.Informes;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface repositorioInforme extends MongoRepository<Informes, String>{
}

package com.software.MyProyect.repositorios;
import com.software.MyProyect.modelos.Clientes;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface repositorioCliente extends MongoRepository<Clientes, String>{
}

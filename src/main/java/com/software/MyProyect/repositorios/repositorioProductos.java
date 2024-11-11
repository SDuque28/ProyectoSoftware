package com.software.MyProyect.repositorios;
import com.software.MyProyect.modelos.Productos;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface respositorioProductos extends MongoRepository<Productos, String>{
}

package com.software.MyProyect.repositorios;
import com.software.MyProyect.modelos.Productos;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface repositorioProducto extends MongoRepository<Productos, String>{

    List<Productos> findByNombreContaining(String nombre);
    List<Productos> findByCodigo(String codigo);
    List<Productos> findByCategoriaId(String categoriaId);
}

package com.software.MyProyect.repositorios;

import com.software.MyProyect.modelos.Productos;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import java.util.List;

public interface repositorioProducto extends MongoRepository<Productos, String>{
    // Buscar productos por nombre, código y categoría (combinado)
    @Query("{ '$or': [ {'descripcion': { $regex: ?0, $options: 'i' } }, {'codigo': ?0}, {'categoriaId': ?0} ] }")
    List<Productos> findByNombreCodigoOCategoria(String keyword);
}

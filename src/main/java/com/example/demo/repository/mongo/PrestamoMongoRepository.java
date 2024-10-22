package com.example.demo.repository.mongo;

import com.example.demo.model.mongo.Prestamo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrestamoMongoRepository extends MongoRepository<Prestamo, String> {
    List<Prestamo> findByUsuarioId(String usuarioId);
}
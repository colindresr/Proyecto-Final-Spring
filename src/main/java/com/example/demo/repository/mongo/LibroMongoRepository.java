package com.example.demo.repository.mongo;

import com.example.demo.model.mongo.Libro;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroMongoRepository extends MongoRepository<Libro, String> {
    List<Libro> findByAutor(String autor);
}
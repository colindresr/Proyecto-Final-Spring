package com.example.demo.repository.mongo;

import com.example.demo.model.mongo.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioMongoRepository extends MongoRepository<Usuario, String> {
    Usuario findByEmail(String email);
}
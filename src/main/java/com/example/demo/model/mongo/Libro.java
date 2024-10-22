package com.example.demo.model.mongo;

import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "libros")
public class Libro {
    @Id
    private Long id; // ID de MongoDB
    private String titulo;
    private String autor;


}

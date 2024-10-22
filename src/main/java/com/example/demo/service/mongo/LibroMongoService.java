package com.example.demo.service.mongo;

import com.example.demo.model.mongo.Libro;
import com.example.demo.repository.mongo.LibroMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroMongoService {

    @Autowired
    private LibroMongoRepository libroMongoRepository;

    public Libro saveLibro(Libro libro) {
        Libro mongoLibro = new Libro();
        mongoLibro.setId(Long.valueOf(libro.getId().toString())); // Convertir Long a String
        mongoLibro.setTitulo(libro.getTitulo());
        mongoLibro.setAutor(libro.getAutor());
        return libroMongoRepository.save(mongoLibro);
    }

    public List<Libro> getAllLibros() {
        return libroMongoRepository.findAll();
    }

    public Libro getLibroById(String id) {
        return libroMongoRepository.findById(id).orElse(null);
    }

    public void deleteLibro(String id) {
        libroMongoRepository.deleteById(id);
    }
}
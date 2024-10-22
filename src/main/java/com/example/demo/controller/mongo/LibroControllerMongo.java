package com.example.demo.controller.mongo;

import com.example.demo.model.mongo.Libro;
import com.example.demo.service.mongo.LibroMongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mongo/libros")
public class LibroControllerMongo {

    @Autowired
    private LibroMongoService libroMongoService;

    // Obtener todos los libros
    @GetMapping
    public ResponseEntity<List<Libro>> getAllLibros() {
        List<Libro> libros = libroMongoService.getAllLibros();
        return new ResponseEntity<>(libros, HttpStatus.OK);
    }

    // Obtener un libro por ID
    @GetMapping("/{id}")
    public ResponseEntity<Libro> getLibroById(@PathVariable String id) {
        Libro libro = libroMongoService.getLibroById(id);
        if (libro != null) {
            return new ResponseEntity<>(libro, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Crear un nuevo libro
    @PostMapping
    public ResponseEntity<Libro> createLibro(@RequestBody Libro libro) {
        Libro newLibro = libroMongoService.saveLibro(libro);
        return new ResponseEntity<>(newLibro, HttpStatus.CREATED);
    }

    // Actualizar un libro existente por ID
    @PutMapping("/{id}")
    public ResponseEntity<Libro> updateLibro(@PathVariable String id, @RequestBody Libro libro) {
        Libro existingLibro = libroMongoService.getLibroById(id);
        if (existingLibro != null) {
            libro.setId(Long.valueOf(id)); // Asegurar que se está actualizando el libro correcto
            Libro updatedLibro = libroMongoService.saveLibro(libro);
            return new ResponseEntity<>(updatedLibro, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar un libro por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLibro(@PathVariable String id) {
        Libro libro = libroMongoService.getLibroById(id);
        if (libro != null) {
            libroMongoService.deleteLibro(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

package com.example.demo.controller.sql;

import com.example.demo.model.sql.Libro;
import com.example.demo.service.sql.LibroSqlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sql/libros")
public class LibroControllerSQL {

    @Autowired
    private LibroSqlService libroSqlService;

    // Obtener todos los libros
    @GetMapping
    public ResponseEntity<List<Libro>> getAllLibros() {
        List<Libro> libros = libroSqlService.getAllLibros();
        return new ResponseEntity<>(libros, HttpStatus.OK);
    }

    // Obtener un libro por ID
    @GetMapping("/{id}")
    public ResponseEntity<Libro> getLibroById(@PathVariable Long id) {
        Libro libro = libroSqlService.getLibroById(id);
        if (libro != null) {
            return new ResponseEntity<>(libro, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Crear un nuevo libro
    @PostMapping
    public ResponseEntity<Libro> createLibro(@RequestBody Libro libro) {
        Libro newLibro = libroSqlService.saveLibro(libro);
        return new ResponseEntity<>(newLibro, HttpStatus.CREATED);
    }

    // Actualizar un libro existente por ID
    @PutMapping("/{id}")
    public ResponseEntity<Libro> updateLibro(@PathVariable Long id, @RequestBody Libro libro) {
        Libro existingLibro = libroSqlService.getLibroById(id);
        if (existingLibro != null) {
            libro.setId(id); // Asegurar que se está actualizando el libro correcto
            Libro updatedLibro = libroSqlService.saveLibro(libro);
            return new ResponseEntity<>(updatedLibro, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar un libro por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLibro(@PathVariable Long id) {
        Libro libro = libroSqlService.getLibroById(id);
        if (libro != null) {
            libroSqlService.deleteLibro(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Obtener libros por autor
    @GetMapping("/autor/{autor}")
    public ResponseEntity<List<Libro>> getLibrosByAutor(@PathVariable String autor) {
        List<Libro> libros = libroSqlService.findByAutor(autor);
        return new ResponseEntity<>(libros, HttpStatus.OK);
    }
}

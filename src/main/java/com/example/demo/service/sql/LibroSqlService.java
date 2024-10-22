package com.example.demo.service.sql;

import com.example.demo.model.sql.Libro;
import com.example.demo.repository.sql.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroSqlService {

    @Autowired
    private LibroRepository libroRepository;

    public Libro saveLibro(Libro libro) {
        return libroRepository.save(libro);
    }

    public List<Libro> getAllLibros() {
        return libroRepository.findAll();
    }

    public Libro getLibroById(Long id) {
        return libroRepository.findById(id).orElse(null);
    }

    public void deleteLibro(Long id) {
        libroRepository.deleteById(id);
    }

    public List<Libro> findByAutor(String autor) {
        return libroRepository.findByAutor(autor);
    }
}
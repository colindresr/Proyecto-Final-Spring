package com.example.demo;

import com.example.demo.controller.sql.LibroControllerSQL;
import com.example.demo.model.sql.Libro;
import com.example.demo.service.sql.LibroSqlService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LibroControllerSQLTest {

    @Mock
    private LibroSqlService libroSqlService;

    @InjectMocks
    private LibroControllerSQL libroControllerSQL;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllLibros() {
        // Arrange
        Libro libro1 = new Libro(1L, "Libro Uno", "Autor Uno");
        Libro libro2 = new Libro(2L, "Libro Dos", "Autor Dos");
        List<Libro> libros = Arrays.asList(libro1, libro2);
        when(libroSqlService.getAllLibros()).thenReturn(libros);

        // Act
        ResponseEntity<List<Libro>> response = libroControllerSQL.getAllLibros();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(libros, response.getBody());
        verify(libroSqlService, times(1)).getAllLibros();
    }

    @Test
    public void testGetLibroByIdFound() {
        // Arrange
        Long id = 1L;
        Libro libro = new Libro(id, "Libro Uno", "Autor Uno");
        when(libroSqlService.getLibroById(id)).thenReturn(libro);

        // Act
        ResponseEntity<Libro> response = libroControllerSQL.getLibroById(id);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(libro, response.getBody());
        verify(libroSqlService, times(1)).getLibroById(id);
    }

    @Test
    public void testGetLibroByIdNotFound() {
        // Arrange
        Long id = 1L;
        when(libroSqlService.getLibroById(id)).thenReturn(null);

        // Act
        ResponseEntity<Libro> response = libroControllerSQL.getLibroById(id);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(libroSqlService, times(1)).getLibroById(id);
    }

    @Test
    public void testCreateLibro() {
        // Arrange
        Libro libro = new Libro(null, "Libro Nuevo", "Autor Nuevo");
        Libro savedLibro = new Libro(1L, "Libro Nuevo", "Autor Nuevo");
        when(libroSqlService.saveLibro(libro)).thenReturn(savedLibro);

        // Act
        ResponseEntity<Libro> response = libroControllerSQL.createLibro(libro);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(savedLibro, response.getBody());
        verify(libroSqlService, times(1)).saveLibro(libro);
    }

    @Test
    public void testUpdateLibroFound() {
        // Arrange
        Long id = 1L;
        Libro existingLibro = new Libro(id, "Libro Existente", "Autor Existente");
        Libro updatedLibro = new Libro(id, "Libro Actualizado", "Autor Actualizado");
        when(libroSqlService.getLibroById(id)).thenReturn(existingLibro);
        when(libroSqlService.saveLibro(updatedLibro)).thenReturn(updatedLibro);

        // Act
        ResponseEntity<Libro> response = libroControllerSQL.updateLibro(id, updatedLibro);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedLibro, response.getBody());
        verify(libroSqlService, times(1)).getLibroById(id);
        verify(libroSqlService, times(1)).saveLibro(updatedLibro);
    }

    @Test
    public void testUpdateLibroNotFound() {
        // Arrange
        Long id = 1L;
        Libro updatedLibro = new Libro(id, "Libro Actualizado", "Autor Actualizado");
        when(libroSqlService.getLibroById(id)).thenReturn(null);

        // Act
        ResponseEntity<Libro> response = libroControllerSQL.updateLibro(id, updatedLibro);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(libroSqlService, times(1)).getLibroById(id);
        verify(libroSqlService, never()).saveLibro(any());
    }

    @Test
    public void testDeleteLibroFound() {
        // Arrange
        Long id = 1L;
        Libro libro = new Libro(id, "Libro Para Borrar", "Autor Para Borrar");
        when(libroSqlService.getLibroById(id)).thenReturn(libro);

        // Act
        ResponseEntity<Void> response = libroControllerSQL.deleteLibro(id);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(libroSqlService, times(1)).getLibroById(id);
        verify(libroSqlService, times(1)).deleteLibro(id);
    }

    @Test
    public void testDeleteLibroNotFound() {
        // Arrange
        Long id = 1L;
        when(libroSqlService.getLibroById(id)).thenReturn(null);

        // Act
        ResponseEntity<Void> response = libroControllerSQL.deleteLibro(id);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(libroSqlService, times(1)).getLibroById(id);
        verify(libroSqlService, never()).deleteLibro(any());
    }

    @Test
    public void testGetLibrosByAutor() {
        // Arrange
        String autor = "Autor Uno";
        Libro libro = new Libro(1L, "Libro Uno", autor);
        when(libroSqlService.findByAutor(autor)).thenReturn(Arrays.asList(libro));

        // Act
        ResponseEntity<List<Libro>> response = libroControllerSQL.getLibrosByAutor(autor);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Arrays.asList(libro), response.getBody());
        verify(libroSqlService, times(1)).findByAutor(autor);
    }
}
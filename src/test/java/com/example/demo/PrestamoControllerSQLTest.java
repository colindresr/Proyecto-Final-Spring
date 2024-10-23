package com.example.demo;

import com.example.demo.controller.sql.PrestamoControllerSQL;
import com.example.demo.model.sql.Prestamo;
import com.example.demo.service.sql.PrestamoSqlService;
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

class PrestamoControllerSQLTest {

    @InjectMocks
    private PrestamoControllerSQL prestamoController;

    @Mock
    private PrestamoSqlService prestamoSqlService;

    private Prestamo prestamo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        prestamo = new Prestamo(); // Suponiendo que Prestamo tiene un constructor vacío
        prestamo.setId(1L);
        // Agregar más propiedades si es necesario
    }

    @Test
    void testGetAllPrestamos() {
        List<Prestamo> prestamos = Arrays.asList(prestamo);
        when(prestamoSqlService.getAllPrestamos()).thenReturn(prestamos);

        ResponseEntity<List<Prestamo>> response = prestamoController.getAllPrestamos();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(prestamos, response.getBody());
    }

    @Test
    void testGetPrestamoByIdFound() {
        when(prestamoSqlService.getPrestamoById(1L)).thenReturn(prestamo);

        ResponseEntity<Prestamo> response = prestamoController.getPrestamoById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(prestamo, response.getBody());
    }

    @Test
    void testGetPrestamoByIdNotFound() {
        when(prestamoSqlService.getPrestamoById(1L)).thenReturn(null);

        ResponseEntity<Prestamo> response = prestamoController.getPrestamoById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testCreatePrestamo() {
        when(prestamoSqlService.savePrestamo(any())).thenReturn(prestamo);

        ResponseEntity<Prestamo> response = prestamoController.createPrestamo(prestamo);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(prestamo, response.getBody());
    }

    @Test
    void testUpdatePrestamoFound() {
        when(prestamoSqlService.getPrestamoById(1L)).thenReturn(prestamo);
        when(prestamoSqlService.savePrestamo(prestamo)).thenReturn(prestamo);

        ResponseEntity<Prestamo> response = prestamoController.updatePrestamo(1L, prestamo);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(prestamo, response.getBody());
    }

    @Test
    void testUpdatePrestamoNotFound() {
        when(prestamoSqlService.getPrestamoById(1L)).thenReturn(null);

        ResponseEntity<Prestamo> response = prestamoController.updatePrestamo(1L, prestamo);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testDeletePrestamoFound() {
        when(prestamoSqlService.getPrestamoById(1L)).thenReturn(prestamo);

        ResponseEntity<Void> response = prestamoController.deletePrestamo(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(prestamoSqlService, times(1)).deletePrestamo(1L);
    }

    @Test
    void testDeletePrestamoNotFound() {
        when(prestamoSqlService.getPrestamoById(1L)).thenReturn(null);

        ResponseEntity<Void> response = prestamoController.deletePrestamo(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testGetPrestamosByUsuarioId() {
        List<Prestamo> prestamos = Arrays.asList(prestamo);
        when(prestamoSqlService.findByUsuarioId(1L)).thenReturn(prestamos);

        ResponseEntity<List<Prestamo>> response = prestamoController.getPrestamosByUsuarioId(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(prestamos, response.getBody());
    }
}
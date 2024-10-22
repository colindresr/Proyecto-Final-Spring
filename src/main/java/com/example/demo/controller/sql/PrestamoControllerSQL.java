package com.example.demo.controller.sql;

import com.example.demo.model.sql.Prestamo;
import com.example.demo.service.sql.PrestamoSqlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sql/prestamos")
public class PrestamoControllerSQL {

    @Autowired
    private PrestamoSqlService prestamoSqlService;

    // Obtener todos los préstamos
    @GetMapping
    public ResponseEntity<List<Prestamo>> getAllPrestamos() {
        List<Prestamo> prestamos = prestamoSqlService.getAllPrestamos();
        return new ResponseEntity<>(prestamos, HttpStatus.OK);
    }

    // Obtener un préstamo por ID
    @GetMapping("/{id}")
    public ResponseEntity<Prestamo> getPrestamoById(@PathVariable Long id) {
        Prestamo prestamo = prestamoSqlService.getPrestamoById(id);
        if (prestamo != null) {
            return new ResponseEntity<>(prestamo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Crear un nuevo préstamo
    @PostMapping
    public ResponseEntity<Prestamo> createPrestamo(@RequestBody Prestamo prestamo) {
        Prestamo newPrestamo = prestamoSqlService.savePrestamo(prestamo);
        return new ResponseEntity<>(newPrestamo, HttpStatus.CREATED);
    }

    // Actualizar un préstamo existente por ID
    @PutMapping("/{id}")
    public ResponseEntity<Prestamo> updatePrestamo(@PathVariable Long id, @RequestBody Prestamo prestamo) {
        Prestamo existingPrestamo = prestamoSqlService.getPrestamoById(id);
        if (existingPrestamo != null) {
            prestamo.setId(id); // Asegurar que se está actualizando el préstamo correcto
            Prestamo updatedPrestamo = prestamoSqlService.savePrestamo(prestamo);
            return new ResponseEntity<>(updatedPrestamo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar un préstamo por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrestamo(@PathVariable Long id) {
        Prestamo prestamo = prestamoSqlService.getPrestamoById(id);
        if (prestamo != null) {
            prestamoSqlService.deletePrestamo(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Obtener todos los préstamos por ID de usuario
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Prestamo>> getPrestamosByUsuarioId(@PathVariable Long usuarioId) {
        List<Prestamo> prestamos = prestamoSqlService.findByUsuarioId(usuarioId);
        return new ResponseEntity<>(prestamos, HttpStatus.OK);
    }
}
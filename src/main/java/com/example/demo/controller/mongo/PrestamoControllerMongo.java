package com.example.demo.controller.mongo;

import com.example.demo.model.mongo.Prestamo;
import com.example.demo.service.mongo.PrestamoMongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mongo/prestamos")
public class PrestamoControllerMongo {

    @Autowired
    private PrestamoMongoService prestamoMongoService;

    // Obtener todos los préstamos
    @GetMapping
    public ResponseEntity<List<Prestamo>> getAllPrestamos() {
        List<Prestamo> prestamos = prestamoMongoService.getAllPrestamos();
        return new ResponseEntity<>(prestamos, HttpStatus.OK);
    }

    // Obtener un préstamo por ID
    @GetMapping("/{id}")
    public ResponseEntity<Prestamo> getPrestamoById(@PathVariable String id) {
        Prestamo prestamo = prestamoMongoService.getPrestamoById(id);
        if (prestamo != null) {
            return new ResponseEntity<>(prestamo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Crear un nuevo préstamo
    @PostMapping
    public ResponseEntity<Prestamo> createPrestamo(@RequestBody Prestamo prestamo) {
        Prestamo newPrestamo = prestamoMongoService.savePrestamo(prestamo);
        return new ResponseEntity<>(newPrestamo, HttpStatus.CREATED);
    }

    // Actualizar un préstamo existente por ID
    @PutMapping("/{id}")
    public ResponseEntity<Prestamo> updatePrestamo(@PathVariable String id, @RequestBody Prestamo prestamo) {
        Prestamo existingPrestamo = prestamoMongoService.getPrestamoById(id);
        if (existingPrestamo != null) {
            prestamo.setId(Long.valueOf(id)); // Asegurar que se está actualizando el préstamo correcto
            Prestamo updatedPrestamo = prestamoMongoService.savePrestamo(prestamo);
            return new ResponseEntity<>(updatedPrestamo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar un préstamo por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrestamo(@PathVariable String id) {
        Prestamo prestamo = prestamoMongoService.getPrestamoById(id);
        if (prestamo != null) {
            prestamoMongoService.deletePrestamo(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Obtener préstamos por usuarioId
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Prestamo>> getPrestamosByUsuarioId(@PathVariable String usuarioId) {
        List<Prestamo> prestamos = prestamoMongoService.findByUsuarioId(usuarioId);
        return new ResponseEntity<>(prestamos, HttpStatus.OK);
    }
}

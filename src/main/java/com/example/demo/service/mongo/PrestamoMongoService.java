package com.example.demo.service.mongo;

import com.example.demo.model.mongo.Prestamo;
import com.example.demo.repository.mongo.PrestamoMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrestamoMongoService {

    @Autowired
    private PrestamoMongoRepository prestamoMongoRepository;

    public Prestamo savePrestamo(Prestamo prestamo) {
        Prestamo mongoPrestamo = new Prestamo();
        mongoPrestamo.setId(Long.valueOf(prestamo.getId().toString())); // Convertir Long a String
        mongoPrestamo.setUsuarioId(Long.valueOf(prestamo.getUsuarioId().toString()));
        mongoPrestamo.setLibroId(Long.valueOf(prestamo.getLibroId().toString()));
        mongoPrestamo.setFechaPrestamo(prestamo.getFechaPrestamo());
        mongoPrestamo.setFechaDevolucion(prestamo.getFechaDevolucion());
        return prestamoMongoRepository.save(mongoPrestamo);
    }

    public List<Prestamo> getAllPrestamos() {
        return prestamoMongoRepository.findAll();
    }

    public Prestamo getPrestamoById(String id) {
        return prestamoMongoRepository.findById(id).orElse(null);
    }

    public void deletePrestamo(String id) {
        prestamoMongoRepository.deleteById(id);
    }

    public List<Prestamo> findByUsuarioId(String usuarioId) {
        return prestamoMongoRepository.findByUsuarioId(usuarioId);
    }
}

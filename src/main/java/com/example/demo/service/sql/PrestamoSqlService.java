package com.example.demo.service.sql;

import com.example.demo.model.sql.Prestamo;
import com.example.demo.repository.sql.PrestamoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrestamoSqlService {

    @Autowired
    private PrestamoRepository prestamoRepository;

    public Prestamo savePrestamo(Prestamo prestamo) {
        return prestamoRepository.save(prestamo);
    }

    public List<Prestamo> getAllPrestamos() {
        return prestamoRepository.findAll();
    }

    public Prestamo getPrestamoById(Long id) {
        return prestamoRepository.findById(id).orElse(null);
    }

    public void deletePrestamo(Long id) {
        prestamoRepository.deleteById(id);
    }

    public List<Prestamo> findByUsuarioId(Long usuarioId) {
        return prestamoRepository.findByUsuarioId(usuarioId);
    }
}

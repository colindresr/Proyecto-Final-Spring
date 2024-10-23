package com.example.demo;


import com.example.demo.controller.sql.UsuarioControllerSQL;
import com.example.demo.model.sql.Usuario;
import com.example.demo.service.sql.UsuarioSqlService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioControllerSQLTest {

    @InjectMocks
    private UsuarioControllerSQL usuarioController;

    @Mock
    private UsuarioSqlService usuarioSqlService;

    private Usuario usuario;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        usuario = new Usuario(); // Suponiendo que Usuario tiene un constructor vacío
        usuario.setId(1L);
        // Agregar más propiedades si es necesario
    }

    @Test
    void testGetAllUsuarios() {
        List<Usuario> usuarios = Arrays.asList(usuario);
        when(usuarioSqlService.getAllUsuarios()).thenReturn(usuarios);

        ResponseEntity<List<Usuario>> response = usuarioController.getAllUsuarios();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(usuarios, response.getBody());
    }

    @Test
    void testGetUsuarioByIdFound() {
        when(usuarioSqlService.getUsuarioById(1L)).thenReturn(usuario);

        ResponseEntity<Usuario> response = usuarioController.getUsuarioById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(usuario, response.getBody());
    }

    @Test
    void testGetUsuarioByIdNotFound() {
        when(usuarioSqlService.getUsuarioById(1L)).thenReturn(null);

        ResponseEntity<Usuario> response = usuarioController.getUsuarioById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testCreateUsuario() {
        when(usuarioSqlService.saveUsuario(any())).thenReturn(usuario);

        ResponseEntity<Usuario> response = usuarioController.createUsuario(usuario);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(usuario, response.getBody());
    }

    @Test
    void testUpdateUsuarioFound() {
        when(usuarioSqlService.getUsuarioById(1L)).thenReturn(usuario);
        when(usuarioSqlService.saveUsuario(usuario)).thenReturn(usuario);

        ResponseEntity<Usuario> response = usuarioController.updateUsuario(1L, usuario);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(usuario, response.getBody());
    }

    @Test
    void testUpdateUsuarioNotFound() {
        when(usuarioSqlService.getUsuarioById(1L)).thenReturn(null);

        ResponseEntity<Usuario> response = usuarioController.updateUsuario(1L, usuario);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testDeleteUsuarioFound() {
        when(usuarioSqlService.getUsuarioById(1L)).thenReturn(usuario);

        ResponseEntity<Void> response = usuarioController.deleteUsuario(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(usuarioSqlService, times(1)).deleteUsuario(1L);
    }

    @Test
    void testDeleteUsuarioNotFound() {
        when(usuarioSqlService.getUsuarioById(1L)).thenReturn(null);

        ResponseEntity<Void> response = usuarioController.deleteUsuario(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testGetUsuarioByEmailFound() {
        when(usuarioSqlService.findByEmail("test@example.com")).thenReturn(usuario);

        ResponseEntity<Usuario> response = usuarioController.getUsuarioByEmail("test@example.com");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(usuario, response.getBody());
    }

    @Test
    void testGetUsuarioByEmailNotFound() {
        when(usuarioSqlService.findByEmail("test@example.com")).thenReturn(null);

        ResponseEntity<Usuario> response = usuarioController.getUsuarioByEmail("test@example.com");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}

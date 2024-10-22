package com.example.demo.service.mongo;

import com.example.demo.model.mongo.Usuario;
import com.example.demo.repository.mongo.UsuarioMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioMongoService {

    @Autowired
    private UsuarioMongoRepository usuarioMongoRepository;

    public Usuario saveUsuario(Usuario usuario) {
        Usuario mongoUsuario = new Usuario();
        mongoUsuario.setId(Long.valueOf(usuario.getId().toString())); // Convertir Long a String
        mongoUsuario.setNombre(usuario.getNombre());
        mongoUsuario.setEmail(usuario.getEmail());
        mongoUsuario.setPassword(usuario.getPassword());
        return usuarioMongoRepository.save(mongoUsuario);
    }

    public List<Usuario> getAllUsuarios() {
        return usuarioMongoRepository.findAll();
    }

    public Usuario getUsuarioById(String id) {
        return usuarioMongoRepository.findById(id).orElse(null);
    }

    public void deleteUsuario(String id) {
        usuarioMongoRepository.deleteById(id);
    }
}

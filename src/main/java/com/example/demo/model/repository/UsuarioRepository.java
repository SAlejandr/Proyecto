package com.example.demo.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.pojos.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	public Usuario findByUsername(String username);
}

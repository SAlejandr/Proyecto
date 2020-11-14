package com.example.demo.model.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.pojos.Usuario;

public interface IUsuarioService {

	public boolean existUser(Usuario user);
	
	public List<Usuario> findAll();
	
	public Optional<Usuario> findById(int id);

	public void save(Usuario user);
	
	public boolean deleteById(int id);
}

package com.example.demo.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.pojos.Usuario;
import com.example.demo.model.repository.UsuarioRepository;

@Service
public class UsuarioService implements IUsuarioService{

	@Autowired private UsuarioRepository dao;
	@Override
	public boolean existUser(Usuario user) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		Usuario u = dao.findByUsername(user.getUsername()).orElse(new Usuario());

		if(!u.getUsername().equals("")&& u.getPassword().equals(user.getPassword()))
			resultado = true;

		return resultado;
	}

	@Override
	public List<Usuario> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public Optional<Usuario> findById(int id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}
	
	@Override
	public void save(Usuario user) {
		// TODO Auto-generated method stub
		dao.save(user);
	}

	@Override
	public boolean deleteById(int id) {
		// TODO Auto-generated method stub
		boolean exito = false;

		if(findById(id).isPresent()) {

			dao.deleteById(id);

			exito = true;
		}

		return exito;
	}

	@Override
	public Optional<Usuario> findByUsername(String username) {
		// TODO Auto-generated method stub
		return dao.findByUsername(username);
	}

}

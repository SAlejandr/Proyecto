package com.example.demo.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.pojos.Rol;
import com.example.demo.model.pojos.Usuario;
import com.example.demo.model.repository.UsuarioRepository;

import lombok.extern.slf4j.Slf4j;

@Service("userDetailsService")
@Slf4j
public class UsuarioService implements IUsuarioService, UserDetailsService{

	@Autowired private UsuarioRepository dao;
	
	@Override
	public boolean existUser(Usuario user) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		Usuario u = dao.findByUsername(user.getUsername());

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
		return Optional.of(dao.findByUsername(username));
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = dao.findByUsername(username);
		
		
		if (usuario == null) {
			throw new UsernameNotFoundException(username);
		}
		
		ArrayList<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
		
		for (Rol rol: usuario.getRoles()) {
			roles.add(new SimpleGrantedAuthority(rol.getNombreRol()));
		}
		return new User(usuario.getUsername(), usuario.getPassword(), roles);
	}

}

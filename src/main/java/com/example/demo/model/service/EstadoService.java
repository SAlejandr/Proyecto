package com.example.demo.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.pojos.Estado;
import com.example.demo.model.repository.EstadoRepository;

@Service
public class EstadoService implements IEstadoService{

	@Autowired private EstadoRepository dao;
	
	@Override
	public Optional<Estado> findById(byte id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	@Override
	public List<Estado> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public void save(Estado estado) {
		// TODO Auto-generated method stub
		
		dao.save(estado);
	}

	@Override
	public boolean deleteById(byte id) {
		// TODO Auto-generated method stub
		boolean exito = false;
		
		if(dao.existsById(id)) {
			dao.deleteById(id);
			exito = true;
		}
		
		return exito;
	}

}

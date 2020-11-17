package com.example.demo.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.pojos.Tercero;
import com.example.demo.model.repository.TerceroRepository;

@Service
public class TerceroService implements ITerceroService{
	
	@Autowired private TerceroRepository dao;

	@Override
	public void save(Tercero tercero) {
		// TODO Auto-generated method stub
		dao.save(tercero);
	}

	@Override
	public List<Tercero> findAll() {
		// TODO Auto-generated method stub
		return (List<Tercero>) dao.findAll();
	}

	@Override
	public Optional<Tercero> findById(String id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	@Override
	public boolean deleteById(String id) {
		// TODO Auto-generated method stub
		boolean exito = false;
		
		if(dao.existsById(id)) {
			dao.deleteById(id);
			exito = true;
		}
		
		return exito;
	}

}

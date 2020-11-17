package com.example.demo.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.pojos.Ciudad;
import com.example.demo.model.pojos.IdCiudad;
import com.example.demo.model.repository.CiudadRepository;
import com.example.demo.model.repository.DepartamentoRepository;

@Service
public class CiudadServicio implements ICiudadService {

	@Autowired
	private CiudadRepository dao;

	@Override
	public void save(Ciudad ciudad) {
		// TODO Auto-generated method stub
		dao.save(ciudad);
	}

	@Override
	public List<Ciudad> findAll() {
		// TODO Auto-generated method stub
		return (List<Ciudad>) dao.findAll();
	}

	@Override
	public Optional<Ciudad> findById(IdCiudad id) {
		// TODO Auto-generated method stub

		return dao.findById(id);
	}

	@Override
	public boolean deleteById(IdCiudad id) {
		// TODO Auto-generated method stub

		boolean exito = false;
		if (findById(id).isPresent()) {
			dao.deleteById(id);
			exito = true;
		}

		return exito;
	}

}

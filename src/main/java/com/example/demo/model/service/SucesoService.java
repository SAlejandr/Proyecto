package com.example.demo.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.pojos.Suceso;
import com.example.demo.model.repository.SucesoRepository;

@Service
public class SucesoService implements ISucesoService {

	@Autowired
	private SucesoRepository dao;

	@Override
	public void save(Suceso suceso) {
		// TODO Auto-generated method stub
		dao.save(suceso);
	}

	@Override
	public List<Suceso> findAll() {
		// TODO Auto-generated method stub
		return (List<Suceso>) dao.findAll();
	}

	@Override
	public Optional<Suceso> findById(String id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	@Override
	public boolean deleteById(String id) {
		// TODO Auto-generated method stub

		boolean exito = false;

		if(dao.existsById(id)) {

			exito = true;

			dao.deleteById(id);
		}

		return exito;
	}

}

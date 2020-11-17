package com.example.demo.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.pojos.Departamento;
import com.example.demo.model.repository.DepartamentoRepository;

@Service
public class DepartamentoService implements IDepartamentoService {

	@Autowired
	private DepartamentoRepository dao;

	@Override
	public void save(Departamento departamento) {
		// TODO Auto-generated method stub
		dao.save(departamento);
	}

	@Override
	public List<Departamento> findAll() {
		// TODO Auto-generated method stub
		return (List<Departamento>) dao.findAll();
	}

	@Override
	public Optional<Departamento> findById(int id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	@Override
	public boolean deleteById(int id) {
		// TODO Auto-generated method stub

		boolean exito = false;

		if(dao.existsById(id)) {

			exito = true;

			dao.deleteById(id);
		}

		return exito;
	}

}

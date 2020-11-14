package com.example.demo.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.pojos.Anno;
import com.example.demo.model.repository.AnnoRepository;

@Service
public class AnnoService implements IAnnoService{

	@Autowired AnnoRepository dao;

	@Override
	public void save(Anno anno) {
		// TODO Auto-generated method stub
		dao.save(anno);
	}

	@Override
	public List<Anno> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();

	}
	
	@Override
	public Optional<Anno> findById(int id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	@Override
	public boolean deleteById(int id) {
		// TODO Auto-generated method stub
		boolean exito = true;

		if(dao.existsById(id))
			dao.deleteById(id);
		else
			exito = false;

		return exito;
	}

	@Override
	public List<Anno> findByCerrado(boolean cerrado) {
		// TODO Auto-generated method stub
		return dao.findByCerrado(cerrado);
	}

	@Override
	public String inactivarTodos() {
		// TODO Auto-generated method stub
		dao.inactivarTodos();
		return "exito";
	}
}


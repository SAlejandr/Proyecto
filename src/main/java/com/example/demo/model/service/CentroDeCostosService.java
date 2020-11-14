package com.example.demo.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.pojos.CentroDeCosto;
import com.example.demo.model.repository.CentroDeCostosRepository;

@Service
public class CentroDeCostosService implements ICentroDeCostosService{

	@Autowired CentroDeCostosRepository dao;
	
	@Override
	public void save(CentroDeCosto centroDeCostos) {
		// TODO Auto-generated method stub
		
		dao.save(centroDeCostos);
		
	}

	@Override
	public List<CentroDeCosto> findAll() {
		// TODO Auto-generated method stub
		return (List<CentroDeCosto>) dao.findAll();
	}

	@Override
	public Optional<CentroDeCosto> findById(int id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	@Override
	public boolean deleteById(int id) {
		// TODO Auto-generated method stub
		
		boolean exito = false;

		if(dao.existsById(id)) {
			exito=true;
			dao.deleteById(id);
		}
		
		
		return exito;
	}

}

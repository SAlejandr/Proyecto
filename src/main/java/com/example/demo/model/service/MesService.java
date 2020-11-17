package com.example.demo.model.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.pojos.Mes;
import com.example.demo.model.pojos.MesFiscalId;
import com.example.demo.model.repository.MesRepository;

@Service
public class MesService implements IMesService{

	@Autowired private MesRepository dao;

	@Override
	public void save(Mes mes) {
		// TODO Auto-generated method stub
		dao.save(mes);
	}

	@Override
	public List<Mes> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public List<Mes> buscarMesesEnRango(LocalDate d1, LocalDate d2) {
		// TODO Auto-generated method stub
		return dao.findByInicioGreaterThanEqualAndFinLessThan(d1, d2);
	}

	@Override
	public Optional<Mes> findById(MesFiscalId id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	@Override
	public boolean deleteById(MesFiscalId id) {
		// TODO Auto-generated method stub
		
		boolean exito = true;
		
		if(dao.existsById(id))
			dao.deleteById(id);
		else
			exito= false;
		
		return exito;
	}

	@Override
	public Optional<Mes> findByFechaFin(LocalDate d) {
		// TODO Auto-generated method stub
		return dao.findByFin(d);
	}

	@Override
	public String inactivarTodos() {				
		// TODO Auto-generated method stub
		dao.inactivarTodos();
		return  "exito";
	}
	
	
	
}

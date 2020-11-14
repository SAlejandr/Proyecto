package com.example.demo.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.pojos.IdSaldoCCostos;
import com.example.demo.model.pojos.Mes;
import com.example.demo.model.pojos.SaldoCentroDeCostos;
import com.example.demo.model.repository.SaldoCCostosRepository;

@Service
public class SaldoCCostosService implements ISaldoCCostosService{

	@Autowired private SaldoCCostosRepository dao;

	@Override
	public void save(SaldoCentroDeCostos saldoCentroDeCostos) {
		// TODO Auto-generated method stub
		dao.save(saldoCentroDeCostos);
	}

	@Override
	public void saveMany(List<SaldoCentroDeCostos> saldos) {
		// TODO Auto-generated method stub
		dao.saveAll(saldos);
	}

	@Override
	public Optional<SaldoCentroDeCostos> findById(IdSaldoCCostos id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	@Override
	public List<SaldoCentroDeCostos> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public boolean deleteById(IdSaldoCCostos id) {
		// TODO Auto-generated method stub
		
		boolean exito = true;
		
		if(dao.existsById(id))
			dao.deleteById(id);
		else 
			exito = false; 
		return exito;
	}

	@Override
	public void borrarPorMes(Mes mes) {
		// TODO Auto-generated method stub
	
		dao.deleteByMes(mes);
	}

	@Override
	public List<SaldoCentroDeCostos> findByMes(Mes mes) {
		// TODO Auto-generated method stub
		return dao.findByMes(mes);
	}
	
	
	
	
}

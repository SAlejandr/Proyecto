package com.example.demo.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.pojos.IdSaldoInCCostos;
import com.example.demo.model.pojos.SaldoInicialCentroDeCostos;
import com.example.demo.model.repository.SaldoInCCostosRepository;

@Service
public class SaldoInicalCCostosService implements  ISaldoIncialCCostosService{

	@Autowired private SaldoInCCostosRepository dao;

	@Override
	public void save(SaldoInicialCentroDeCostos saldoInicialCentroDeCostos) {
		// TODO Auto-generated method stub
		dao.save(saldoInicialCentroDeCostos);
	}

	@Override
	public void saveMany(List<SaldoInicialCentroDeCostos> saldosIniales) {
		// TODO Auto-generated method stub
		dao.saveAll(saldosIniales);
	}

	@Override
	public Optional<SaldoInicialCentroDeCostos> findById(IdSaldoInCCostos id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	@Override
	public List<SaldoInicialCentroDeCostos> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
		
	}

	@Override
	public boolean deleteById(IdSaldoInCCostos id) {
		// TODO Auto-generated method stub
		
		boolean exito = true;
		
		if(dao.existsById(id))
			dao.deleteById(id);
		else
			exito = false;
		
		return exito;
	}
	
	
}

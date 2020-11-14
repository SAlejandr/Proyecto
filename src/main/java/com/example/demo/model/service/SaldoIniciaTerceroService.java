package com.example.demo.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.pojos.IdSaldoInTercero;
import com.example.demo.model.pojos.SaldoInicialTercero;
import com.example.demo.model.repository.SaldoInTerceroRepository;

@Service
public class SaldoIniciaTerceroService implements ISaldoInicialTerceroService{

	@Autowired private SaldoInTerceroRepository dao;

	@Override
	public void save(SaldoInicialTercero saldoIniciaTercero) {
		// TODO Auto-generated method stub
		dao.save(saldoIniciaTercero);
	}

	@Override
	public void saveMany(List<SaldoInicialTercero> saldos) {
		// TODO Auto-generated method stub
		dao.saveAll(saldos);
	}

	@Override
	public Optional<SaldoInicialTercero> findById(IdSaldoInTercero id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	@Override
	public List<SaldoInicialTercero> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public boolean deleteById(IdSaldoInTercero id) {
		// TODO Auto-generated method stub
		
		boolean exito = true;
		
		if(dao.existsById(id))
			dao.deleteById(id);
		else
			exito= false;
		
		return exito;
	}
	
	
	
}

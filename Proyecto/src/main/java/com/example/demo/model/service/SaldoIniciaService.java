package com.example.demo.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.pojos.Anno;
import com.example.demo.model.pojos.IdSaldoInicial;
import com.example.demo.model.pojos.SaldoInicialCuenta;
import com.example.demo.model.repository.SaldoInicialRepository;

@Service
public class SaldoIniciaService implements ISaldoInicialService{

	@Autowired private SaldoInicialRepository dao;

	@Override
	public void save(SaldoInicialCuenta saldo) {
		// TODO Auto-generated method stub
		dao.save(saldo);
	}

	@Override
	public void saveMany(List<SaldoInicialCuenta> saldosIniciales) {
		// TODO Auto-generated method stub
		dao.saveAll(saldosIniciales);
	}

	@Override
	public Optional<SaldoInicialCuenta> findById(IdSaldoInicial id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	@Override
	public List<SaldoInicialCuenta> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public boolean deleteById(IdSaldoInicial id) {
		// TODO Auto-generated method stub
		boolean exito = true;
		
		if(dao.existsById(id))
			dao.deleteById(id);
		else
			exito = false;
		
		return exito;
	}

	@Override
	public List<SaldoInicialCuenta> findByAnno(Anno anno) {
		// TODO Auto-generated method stub
		return dao.findDataByYear(anno);
	} 
	
}

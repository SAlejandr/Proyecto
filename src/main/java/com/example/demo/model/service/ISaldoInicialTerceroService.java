package com.example.demo.model.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.pojos.IdSaldoInTercero;
import com.example.demo.model.pojos.SaldoInicialTercero;

public interface ISaldoInicialTerceroService {

	public void save(SaldoInicialTercero saldoIniciaTercero);
	
	public void saveMany (List<SaldoInicialTercero> saldos);
	
	public Optional<SaldoInicialTercero> findById(IdSaldoInTercero id);
	
	public List<SaldoInicialTercero> findAll();
	
	public boolean deleteById(IdSaldoInTercero id);
	
}

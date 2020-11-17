package com.example.demo.model.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.dto.DtoSaldos;
import com.example.demo.model.pojos.Anno;
import com.example.demo.model.pojos.IdSaldoInicial;
import com.example.demo.model.pojos.SaldoInicialCuenta;

public interface ISaldoInicialService {

	public void save(SaldoInicialCuenta saldo);
	
	public void saveMany(List<SaldoInicialCuenta> saldosIniciales);
	
	public Optional<SaldoInicialCuenta> findById(IdSaldoInicial id);
	
	public List<SaldoInicialCuenta> findAll();
	
	public List<SaldoInicialCuenta> findByAnno(Anno anno);
	
	public boolean deleteById(IdSaldoInicial id);
}

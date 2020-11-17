package com.example.demo.model.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.pojos.IdSaldoCCostos;
import com.example.demo.model.pojos.Mes;
import com.example.demo.model.pojos.SaldoCentroDeCostos;
import com.example.demo.model.pojos.SaldoCuenta;

public interface ISaldoCCostosService {

	public void save(SaldoCentroDeCostos saldoCentroDeCostos);
	
	public void saveMany(List<SaldoCentroDeCostos> saldos);

	public Optional<SaldoCentroDeCostos> findById(IdSaldoCCostos id);

	public List<SaldoCentroDeCostos> findAll();
	
	public List<SaldoCentroDeCostos> findByMes(Mes mes);

	public boolean deleteById(IdSaldoCCostos id);

	public void borrarPorMes(Mes mes);
}

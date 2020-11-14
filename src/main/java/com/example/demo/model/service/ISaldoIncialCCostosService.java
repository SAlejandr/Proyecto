package com.example.demo.model.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.pojos.IdSaldoInCCostos;
import com.example.demo.model.pojos.SaldoInicialCentroDeCostos;

public interface ISaldoIncialCCostosService {
	
	public void save(SaldoInicialCentroDeCostos saldoInicialCentroDeCostos);
	
	public void saveMany(List<SaldoInicialCentroDeCostos> saldosIniales);

	public Optional<SaldoInicialCentroDeCostos> findById(IdSaldoInCCostos id);

	public List<SaldoInicialCentroDeCostos> findAll();

	public boolean deleteById(IdSaldoInCCostos id);

}

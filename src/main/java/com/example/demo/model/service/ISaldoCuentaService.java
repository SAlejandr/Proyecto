package com.example.demo.model.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.pojos.IdSaldo;
import com.example.demo.model.pojos.Mes;
import com.example.demo.model.pojos.SaldoCuenta;

public interface ISaldoCuentaService {

	public void save(SaldoCuenta saldoCuenta);
	
	public void saveMany(List<SaldoCuenta> saldos);

	public Optional<SaldoCuenta> findById(IdSaldo idSaldo);

	public List<SaldoCuenta> findAll();
	
	public List<SaldoCuenta> findByMes(Mes mes);

	public boolean deleteById(IdSaldo idSaldo);

	public void borrarPorMes(Mes mes);
}

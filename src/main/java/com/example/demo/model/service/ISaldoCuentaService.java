package com.example.demo.model.service;

import java.time.LocalDate;
import java.util.Date;
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
	
	public void remayorizarMoviblesEnero(int anno, int mes, LocalDate localDate, LocalDate localDate2);
	
	public void remayorizarMoviblesRestoAnno(int anno, int mes, LocalDate localDate, LocalDate localDate2);
	
	public List<Object[]> remayorizacion2(int anno, int mes, int largo);
	
	public void borrarRegistrosCeros();
	
	
}

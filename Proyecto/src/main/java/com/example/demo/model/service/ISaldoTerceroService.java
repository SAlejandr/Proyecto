package com.example.demo.model.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.pojos.IdSaldoTercero;
import com.example.demo.model.pojos.Mes;
import com.example.demo.model.pojos.SaldoCentroDeCostos;
import com.example.demo.model.pojos.SaldoCuenta;
import com.example.demo.model.pojos.SaldoTercero;

public interface ISaldoTerceroService {

	
	public void save(SaldoTercero saldoTercero);
	
	public void saveMany(List<SaldoTercero> saldos);

	public Optional<SaldoTercero> findById(IdSaldoTercero idSaldoTercero);

	public List<SaldoTercero> findAll();
	
	public List<SaldoTercero> findByMes(Mes mes);

	public boolean deleteById(IdSaldoTercero idSaldoTercero);
	
	public void borrarPorMes(Mes mes);
}

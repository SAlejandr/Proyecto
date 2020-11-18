package com.example.demo.model.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.pojos.Cuenta;

public interface ICuentaService {

	public void save(Cuenta cuenta);

	public List<Cuenta> findAll();
	
	public List<Cuenta> findCuentasMovibles();
	public List<Cuenta> findCuentasMovibleConTerceros();
	public List<Cuenta> findCuentasMoviblesConCCostos();

	public Optional<Cuenta> findById(String id);

	public boolean deleteById(String id);

}

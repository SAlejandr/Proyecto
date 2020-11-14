package com.example.demo.model.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.pojos.ConfiguracionCuentas;

public interface IConfiCuentasService {

	
	public void save(ConfiguracionCuentas conf);
	
	public Optional<ConfiguracionCuentas> findById(String id);
	
	public List<ConfiguracionCuentas> findAll();
	
	public boolean deleteById(String id);
}

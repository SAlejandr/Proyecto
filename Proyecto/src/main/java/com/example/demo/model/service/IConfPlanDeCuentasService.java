package com.example.demo.model.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.pojos.ConfiguracionDePlanDeCuentas;

public interface IConfPlanDeCuentasService {

	public void save(ConfiguracionDePlanDeCuentas conf);
	
	public Optional<ConfiguracionDePlanDeCuentas> findById(int nivel);
	
	public List<ConfiguracionDePlanDeCuentas> findAll();
	
	public boolean deleteById(int nivel);
	
	
}

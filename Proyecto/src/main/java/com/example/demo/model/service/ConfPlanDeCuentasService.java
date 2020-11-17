package com.example.demo.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.pojos.ConfiguracionDePlanDeCuentas;
import com.example.demo.model.repository.ConfPlanDeCuentasRepository;

@Service
public class ConfPlanDeCuentasService implements IConfPlanDeCuentasService{

	@Autowired private ConfPlanDeCuentasRepository dao;
	
	@Override
	public void save(ConfiguracionDePlanDeCuentas conf) {
		// TODO Auto-generated method stub
		dao.save(conf);
	}

	@Override
	public Optional<ConfiguracionDePlanDeCuentas> findById(int nivel) {
		// TODO Auto-generated method stub
		return dao.findById(nivel);
	}

	@Override
	public List<ConfiguracionDePlanDeCuentas> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public boolean deleteById(int nivel) {
		// TODO Auto-generated method stub
		
		boolean exito = false;
		
		if(dao.existsById(nivel)) {
			
			exito = true;
			dao.deleteById(nivel);
		}
		
		return exito;
	}

	
	
	
}

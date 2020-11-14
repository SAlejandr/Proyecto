package com.example.demo.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.pojos.ConfiguracionCuentas;
import com.example.demo.model.repository.ConfCuentasRepository;

@Service
public class ConfiCuentasService implements IConfiCuentasService{

	@Autowired private ConfCuentasRepository dao;
	
	@Override
	public void save(ConfiguracionCuentas conf) {
		// TODO Auto-generated method stub
		dao.save(conf);
	}

	@Override
	public Optional<ConfiguracionCuentas> findById(String id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	@Override
	public List<ConfiguracionCuentas> findAll() {
		// TODO Auto-generated method stub
		return (List<ConfiguracionCuentas>)dao.findAll();
	}

	@Override
	public boolean deleteById(String id) {
		// TODO Auto-generated method stub
		
		boolean exito = false;
		
		if(dao.existsById(id)) {
			exito = true;
			dao.deleteById(id);
		}
		
		return exito;
	}

}

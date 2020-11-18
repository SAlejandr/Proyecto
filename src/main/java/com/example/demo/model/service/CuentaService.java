package com.example.demo.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.pojos.Cuenta;
import com.example.demo.model.repository.CuentaRepository;

@Service
public class CuentaService implements ICuentaService{

	@Autowired private CuentaRepository dao;
	
	@Override
	public void save(Cuenta cuenta) {
		// TODO Auto-generated method stub
		dao.save(cuenta);
	}

	@Override
	public List<Cuenta> findAll() {
		// TODO Auto-generated method stub
		return (List<Cuenta>) dao.findAll();
	}

	@Override
	public Optional<Cuenta> findById(String id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
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

	@Override
	public List<Cuenta> findCuentasMovibles() {
		// TODO Auto-generated method stub
		return dao.findByMovimientos(true);
	}

	@Override
	public List<Cuenta> findCuentasMovibleConTerceros() {
		// TODO Auto-generated method stub
		return dao.findByMovimientosAndTerceros(true, true);
	}

	@Override
	public List<Cuenta> findCuentasMoviblesConCCostos() {
		// TODO Auto-generated method stub
		return dao.findByMovimientosAndCcostos(true, true);
	}

}

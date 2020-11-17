package com.example.demo.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.pojos.IdSaldoTercero;
import com.example.demo.model.pojos.Mes;
import com.example.demo.model.pojos.SaldoCentroDeCostos;
import com.example.demo.model.pojos.SaldoTercero;
import com.example.demo.model.repository.SaldoTerceroRepository;

@Service
public class SaldoTerceroService implements ISaldoTerceroService{

	@Autowired private SaldoTerceroRepository dao;

	@Override
	public void save(SaldoTercero saldoTercero) {
		// TODO Auto-generated method stub
		dao.save(saldoTercero);
	}


	@Override
	public void saveMany(List<SaldoTercero> saldos) {
		// TODO Auto-generated method stub
		dao.saveAll(saldos);
	}
	
	

	@Override
	public Optional<SaldoTercero> findById(IdSaldoTercero idSaldoTercero) {
		// TODO Auto-generated method stub
		return dao.findById(idSaldoTercero);
	}

	@Override
	public List<SaldoTercero> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public boolean deleteById(IdSaldoTercero idSaldoTercero) {
		// TODO Auto-generated method stub
		
		boolean exito = true;
		
		if(dao.existsById(idSaldoTercero))
			dao.deleteById(idSaldoTercero);
		else
			exito = false;
		
		return exito;
	}

	@Override
	public void borrarPorMes(Mes mes) {
		// TODO Auto-generated method stub
		dao.deleteByMes(mes);
	}


	@Override
	public List<SaldoTercero> findByMes(Mes mes) {
		// TODO Auto-generated method stub
		return dao.findByMes(mes);
	}



}

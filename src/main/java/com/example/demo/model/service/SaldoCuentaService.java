package com.example.demo.model.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.pojos.IdSaldo;
import com.example.demo.model.pojos.Mes;
import com.example.demo.model.pojos.SaldoCuenta;
import com.example.demo.model.repository.SaldoRepository;

@Service
public class SaldoCuentaService implements ISaldoCuentaService{

	@Autowired private SaldoRepository dao;
	
	@Override
	public void save(SaldoCuenta saldoCuenta) {
		// TODO Auto-generated method stub
		dao.save(saldoCuenta);
	}

	@Override
	public Optional<SaldoCuenta> findById(IdSaldo idSaldo) {
		// TODO Auto-generated method stub
		return dao.findById(idSaldo);
	}

	@Override
	public List<SaldoCuenta> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public boolean deleteById(IdSaldo idSaldo) {
		// TODO Auto-generated method stub
		
		boolean exito = true;
		
		if(dao.existsById(idSaldo))
			dao.deleteById(idSaldo);
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
	public void saveMany(List<SaldoCuenta> saldos) {
		// TODO Auto-generated method stub
		
		dao.saveAll(saldos);
	}

	@Override
	public List<SaldoCuenta> findByMes(Mes mes) {
		// TODO Auto-generated method stub
		return dao.findByMes(mes);
	}
	
	public void remayorizarMoviblesEnero(int anno, int mes, LocalDate inicioMes, LocalDate finMes) {
		// TODO Auto-generated method stub
		dao.remayorizarMoviblesEnero(anno, mes, inicioMes, finMes);
	}
	
	public void remayorizarMoviblesRestoAnno(int anno, int mes, LocalDate inicioMes, LocalDate finMes) {
	// TODO Auto-generated method stub
	dao.remayorizarMoviblesRestoAnno(anno, mes, inicioMes, finMes);
	}
	
	public List<Object[]> remayorizacion2(int anno, int mes, int largo) {
		// TODO Auto-generated method stub
		 return dao.remayorizacion2(anno, mes, largo);
		}
	
	public void borrarRegistrosCeros() {
		// TODO Auto-generated method stub
		dao.borrarRegistrosCeros();
		}

}

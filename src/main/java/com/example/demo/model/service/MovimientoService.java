package com.example.demo.model.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.pojos.Documento;
import com.example.demo.model.pojos.IdMovimiento;
import com.example.demo.model.pojos.Mes;
import com.example.demo.model.pojos.Movimiento;
import com.example.demo.model.repository.MovimientoRepository;

@Service
public class MovimientoService implements IMovimientoService{

	@Autowired MovimientoRepository dao;

	@Override
	public void save(Movimiento movimiento) {
		// TODO Auto-generated method stub
		dao.save(movimiento);
	}

	@Override
	public void saveAll(List<Movimiento> movimientos) {
		// TODO Auto-generated method stub

		dao.saveAll(movimientos);
	}

	@Override
	public List<Movimiento> findAll() {
		// TODO Auto-generated method stub
		return (List<Movimiento>) dao.findAll();
	}

	@Override
	public Optional<Movimiento> findById(IdMovimiento id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	@Override
	public boolean deleteById(IdMovimiento id) {
		// TODO Auto-generated method stub

		boolean exito = true;

		if(dao.existsById(id))
			dao.deleteById(id);
		else
			exito= false;

		return exito;
	}

	@Override
	public List<Movimiento> buscarPorMes(Mes mes) {
		// TODO Auto-generated method stub
		
		return dao.findByFechaBetween(mes.getInicio(), mes.getFin());
	}

	@Override
	public List<Movimiento> buscarPorMesYDocumento(Documento documento, Mes mes) {
		// TODO Auto-generated method stub
		
		List<Movimiento> porMes = buscarPorMes(mes);
		
		List<Movimiento> porDocumento =	porMes.stream().filter(m -> m.getId().getDocumento().equals(documento)).collect(Collectors.toList());
		
		return porDocumento;
	}

	@Override
	public List<Movimiento> buscarPorDocumento(Documento documento) {
		// TODO Auto-generated method stub
		List<Movimiento> todos = findAll();
		List<Movimiento> porDocumento =	todos.stream().filter(m -> m.getId().getDocumento().equals(documento)).collect(Collectors.toList());

		return null;
	}

}

package com.example.demo.model.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.pojos.Documento;
import com.example.demo.model.pojos.IdMovimiento;
import com.example.demo.model.pojos.Mes;
import com.example.demo.model.pojos.Movimiento;

public interface IMovimientoService {

	public void save(Movimiento movimiento);
	
	public void saveAll(List<Movimiento> movimientos);

	public List<Movimiento> findAll();

	public Optional<Movimiento> findById(IdMovimiento id);

	public boolean deleteById(IdMovimiento id);

	public List<Movimiento> buscarPorMes(Mes mes);
	
	public List<Movimiento> buscarPorMesYDocumento(Documento documento, Mes mes);
	
	public List<Movimiento> buscarPorDocumento(Documento documento);
}

package com.example.demo.model.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.example.demo.model.dto.DTODebitoDiferenteDeCredito;
import com.example.demo.model.pojos.Documento;
import com.example.demo.model.pojos.IdMovimiento;
import com.example.demo.model.pojos.Mes;
import com.example.demo.model.pojos.Movimiento;
import com.example.demo.model.pojos.SaldoCuenta;

public interface IMovimientoService {

	public void save(Movimiento movimiento);
	
	public void saveAll(List<Movimiento> movimientos);

	public List<Movimiento> findAll();

	public Optional<Movimiento> findById(IdMovimiento id);

	public boolean deleteById(IdMovimiento id);

	public List<Movimiento> buscarPorMes(Mes mes);
	
	public List<Movimiento> buscarPorMesYDocumento(Documento documento, Mes mes);
	
	public List<Movimiento> buscarPorDocumento(Documento documento);
	
	public List<Movimiento> movimientosEnCuentasNoAfectables(LocalDate fechaInicio, LocalDate fechaFin);
	
	public List<Movimiento> movimientosSinValor(LocalDate fechaInicio, LocalDate fechaFin);
	public List<DTODebitoDiferenteDeCredito> debitosDiferenteCreditos(LocalDate fechaInicio, LocalDate fechaFin);
	public List<Movimiento> movimientosSinTerceros(LocalDate fechaInicio, LocalDate fechaFin);
	public List<Movimiento> movimientosConTercerosInnecesarios(LocalDate fechaInicio, LocalDate fechaFin);
	public List<Movimiento> movimientosSinCentros(LocalDate fechaInicio, LocalDate fechaFin);
	public List<Movimiento> movimientosConCentrosInnecesarios(LocalDate fechaInicio, LocalDate fechaFin);
	
	public void actualizacionVerticalAsc(Movimiento movimiento);
	public void actualizacionVerticalDes(Movimiento movimiento);
}

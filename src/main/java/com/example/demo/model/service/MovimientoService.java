package com.example.demo.model.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.dto.DTODebitoDiferenteDeCredito;
import com.example.demo.model.pojos.CentroDeCosto;
import com.example.demo.model.pojos.Cuenta;
import com.example.demo.model.pojos.Documento;
import com.example.demo.model.pojos.IdMovimiento;
import com.example.demo.model.pojos.IdSaldo;
import com.example.demo.model.pojos.IdSaldoCCostos;
import com.example.demo.model.pojos.IdSaldoTercero;
import com.example.demo.model.pojos.Mes;
import com.example.demo.model.pojos.Movimiento;
import com.example.demo.model.pojos.SaldoCentroDeCostos;
import com.example.demo.model.pojos.SaldoCuenta;
import com.example.demo.model.pojos.SaldoTercero;
import com.example.demo.model.pojos.Tercero;
import com.example.demo.model.repository.CuentaRepository;
import com.example.demo.model.repository.MesRepository;
import com.example.demo.model.repository.MovimientoRepository;
import com.example.demo.model.repository.SaldoCCostosRepository;
import com.example.demo.model.repository.SaldoRepository;
import com.example.demo.model.repository.SaldoTerceroRepository;

@Service
public class MovimientoService implements IMovimientoService{

	@Autowired MovimientoRepository dao;
	@Autowired private CuentaRepository daoCuenta;
	@Autowired private SaldoRepository daoSaldoCuenta;
	@Autowired private SaldoTerceroRepository daoSaldoTercero;
	@Autowired private SaldoCCostosRepository daoSaldoCCostos;
	@Autowired private MesRepository daoMes;

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

		return porDocumento;
	}

	@Override
	public List<Movimiento> movimientosEnCuentasNoAfectables(LocalDate fechaInicio, LocalDate fechaFin) {
		// TODO Auto-generated method stub
		return dao.movimientosEnCuentasNoAfectables(fechaInicio, fechaFin);
	}

	@Override
	public List<Movimiento> movimientosSinValor(LocalDate fechaInicio, LocalDate fechaFin) {
		// TODO Auto-generated method stub
		return dao.movimientosSinValor(fechaInicio, fechaFin);
	}

	@Override
	public List<DTODebitoDiferenteDeCredito> debitosDiferenteCreditos(LocalDate fechaInicio, LocalDate fechaFin) {
		// TODO Auto-generated method stub
		return dao.debitosDiferenteCreditos(fechaInicio, fechaFin);
	}

	@Override
	public List<Movimiento> movimientosSinTerceros(LocalDate fechaInicio, LocalDate fechaFin) {
		// TODO Auto-generated method stub
		return dao.movimientosSinTerceros(fechaInicio, fechaFin);
	}

	@Override
	public List<Movimiento> movimientosConTercerosInnecesarios(LocalDate fechaInicio, LocalDate fechaFin) {
		// TODO Auto-generated method stub
		return dao.movimientosConTercerosInnecesarios(fechaInicio, fechaFin);
	}

	@Override
	public List<Movimiento> movimientosSinCentros(LocalDate fechaInicio, LocalDate fechaFin) {
		// TODO Auto-generated method stub
		return dao.movimientosSinCentros(fechaInicio, fechaFin);
	}

	@Override
	public List<Movimiento> movimientosConCentrosInnecesarios(LocalDate fechaInicio, LocalDate fechaFin) {
		// TODO Auto-generated method stub
		return dao.movimientosConCentrosInnecesarios(fechaInicio, fechaFin);
	}

	@Override
	public void actualizacionVerticalAsc(Movimiento movimiento) {
		// TODO Auto-generated method stub

		Mes mes = daoMes.findByFechaDeterminadad(movimiento.getFecha()).get(0);
		IdSaldo idSaldo = IdSaldo.builder().cuenta(movimiento.getCuenta()).mes(mes).build();
		SaldoCuenta saldo = daoSaldoCuenta.findById(idSaldo).orElse(SaldoCuenta.builder().
				id(idSaldo).
				sumCredito(BigDecimal.ZERO).
				sumDebito(BigDecimal.ZERO).
				totalCredito(BigDecimal.ZERO).
				totalDebito(BigDecimal.ZERO).
				build());

		//Actualizo Saldo tercero
		if(!movimiento.getTercero().equals(Tercero.builder().id(".").build())) {
			IdSaldoTercero idSTercero = new IdSaldoTercero(idSaldo.getCuenta(), mes, movimiento.getTercero());
			SaldoTercero saldoTercero = daoSaldoTercero.findById(idSTercero).orElse(SaldoTercero.builder().
					id(idSTercero).
					sumCredito(BigDecimal.ZERO).
					sumDebito(BigDecimal.ZERO).
					totalCredito(BigDecimal.ZERO).
					totalDebito(BigDecimal.ZERO).
					build());
			
			saldoTercero.setSumDebito(saldoTercero.getSumDebito().add(saldo.getSumDebito()));
			saldoTercero.setSumDebito(saldoTercero.getSumCredito().add(saldo.getSumCredito()));

			BigDecimal total = BigDecimal.ZERO;

			total = total.add(saldoTercero.getSumDebito());
			total = total.subtract(saldoTercero.getSumCredito());

			if(total.signum() < 0) {
				saldoTercero.setTotalCredito(total.abs());
			}else if(total.signum() > 0) {
				saldoTercero.setTotalDebito(total.abs());
			}
			
		}
		
		if(!movimiento.getCCostos().equals(CentroDeCosto.builder().codCentro(0).build())) {
			IdSaldoCCostos idSCCostos = new IdSaldoCCostos(idSaldo.getCuenta(), mes, movimiento.getCCostos());
			SaldoCentroDeCostos saldoCDeCostos = daoSaldoCCostos.findById(idSCCostos).orElse(SaldoCentroDeCostos.builder().
					elID(idSCCostos).
					sumCredito(BigDecimal.ZERO).
					sumDebito(BigDecimal.ZERO).
					totalCredito(BigDecimal.ZERO).
					totalDebito(BigDecimal.ZERO).
					build());
			
			saldoCDeCostos.setSumDebito(saldoCDeCostos.getSumDebito().add(saldo.getSumDebito()));
			saldoCDeCostos.setSumDebito(saldoCDeCostos.getSumCredito().add(saldo.getSumCredito()));

			BigDecimal total = BigDecimal.ZERO;

			total = total.add(saldoCDeCostos.getSumDebito());
			total = total.subtract(saldoCDeCostos.getSumCredito());

			if(total.signum() < 0) {
				saldoCDeCostos.setTotalCredito(total.abs());
			}else if(total.signum() > 0) {
				saldoCDeCostos.setTotalDebito(total.abs());
			}
			
		}
		String cadena = saldo.getId().getCuenta().getCodCuenta();
		Cuenta cuenta = daoCuenta.getOne(saldo.getId().getCuenta().getCuentaSuperior());

		do {

			IdSaldo id = IdSaldo.builder().
					cuenta(cuenta).
					mes(mes).
					build();

			SaldoCuenta saldoCuentaSuperior = daoSaldoCuenta.findById(id).
					orElse(SaldoCuenta.builder().
							id(id).
							sumCredito(BigDecimal.ZERO).
							sumDebito(BigDecimal.ZERO).
							totalCredito(BigDecimal.ZERO).
							totalDebito(BigDecimal.ZERO).
							build());

			saldoCuentaSuperior.setSumDebito(saldoCuentaSuperior.getSumDebito().add(movimiento.getDebito()));
			saldoCuentaSuperior.setSumDebito(saldoCuentaSuperior.getSumCredito().add(movimiento.getCredito()));

			BigDecimal total = BigDecimal.ZERO;

			total = total.add(saldoCuentaSuperior.getSumDebito());
			total = total.subtract(saldoCuentaSuperior.getSumCredito());

			if(total.signum() < 0) {
				saldoCuentaSuperior.setTotalCredito(total);
			}else if(total.signum() > 0) {
				saldoCuentaSuperior.setTotalDebito(total);
			}

			daoSaldoCuenta.save(saldoCuentaSuperior);

			cadena = cuenta.getCuentaSuperior();
			cuenta = daoCuenta.getOne(saldo.getId().getCuenta().getCuentaSuperior());
		}while(!cadena.equals(cuenta.getCuentaSuperior()) || cadena.isBlank());
				 

	}

	@Override
	public void actualizacionVerticalDes(Movimiento movimiento) {
		// TODO Auto-generated method stub

		Mes mes = daoMes.findByFechaDeterminadad(movimiento.getFecha()).get(0);
		IdSaldo idSaldo = IdSaldo.builder().cuenta(movimiento.getCuenta()).mes(mes).build();
		SaldoCuenta saldo = daoSaldoCuenta.findById(idSaldo).orElse(SaldoCuenta.builder().
				id(idSaldo).
				sumCredito(BigDecimal.ZERO).
				sumDebito(BigDecimal.ZERO).
				totalCredito(BigDecimal.ZERO).
				totalDebito(BigDecimal.ZERO).
				build());

		//Actualizo Saldo tercero
		if(!movimiento.getTercero().equals(Tercero.builder().id(".").build())) {
			IdSaldoTercero idSTercero = new IdSaldoTercero(idSaldo.getCuenta(), mes, movimiento.getTercero());
			SaldoTercero saldoTercero = daoSaldoTercero.findById(idSTercero).orElse(SaldoTercero.builder().
					id(idSTercero).
					sumCredito(BigDecimal.ZERO).
					sumDebito(BigDecimal.ZERO).
					totalCredito(BigDecimal.ZERO).
					totalDebito(BigDecimal.ZERO).
					build());
			
			saldoTercero.setSumDebito(saldoTercero.getSumDebito().subtract(movimiento.getDebito()));
			saldoTercero.setSumDebito(saldoTercero.getSumCredito().subtract(movimiento.getCredito()));

			BigDecimal total = BigDecimal.ZERO;

			total = total.add(saldoTercero.getSumDebito());
			total = total.subtract(saldoTercero.getSumCredito());

			if(total.signum() < 0) {
				saldoTercero.setTotalCredito(total.abs());
			}else if(total.signum() > 0) {
				saldoTercero.setTotalDebito(total.abs());
			}
			
		}
		
		if(!movimiento.getCCostos().equals(CentroDeCosto.builder().codCentro(0).build())) {
			IdSaldoCCostos idSCCostos = new IdSaldoCCostos(idSaldo.getCuenta(), mes, movimiento.getCCostos());
			SaldoCentroDeCostos saldoCDeCostos = daoSaldoCCostos.findById(idSCCostos).orElse(SaldoCentroDeCostos.builder().
					elID(idSCCostos).
					sumCredito(BigDecimal.ZERO).
					sumDebito(BigDecimal.ZERO).
					totalCredito(BigDecimal.ZERO).
					totalDebito(BigDecimal.ZERO).
					build());
			
			saldoCDeCostos.setSumDebito(saldoCDeCostos.getSumDebito().subtract(movimiento.getDebito()));
			saldoCDeCostos.setSumDebito(saldoCDeCostos.getSumCredito().subtract(movimiento.getCredito()));

			BigDecimal total = BigDecimal.ZERO;

			total = total.add(saldoCDeCostos.getSumDebito());
			total = total.subtract(saldoCDeCostos.getSumCredito());

			if(total.signum() < 0) {
				saldoCDeCostos.setTotalCredito(total.abs());
			}else if(total.signum() > 0) {
				saldoCDeCostos.setTotalDebito(total.abs());
			}
			
		}
		String cadena = saldo.getId().getCuenta().getCodCuenta();
		Cuenta cuenta = daoCuenta.getOne(saldo.getId().getCuenta().getCuentaSuperior());

		do {

			IdSaldo id = IdSaldo.builder().
					cuenta(cuenta).
					mes(mes).
					build();

			SaldoCuenta saldoCuentaSuperior = daoSaldoCuenta.findById(id).
					orElse(SaldoCuenta.builder().
							id(id).
							sumCredito(BigDecimal.ZERO).
							sumDebito(BigDecimal.ZERO).
							totalCredito(BigDecimal.ZERO).
							totalDebito(BigDecimal.ZERO).
							build());

			saldoCuentaSuperior.setSumDebito(saldoCuentaSuperior.getSumDebito().add(movimiento.getDebito()));
			saldoCuentaSuperior.setSumDebito(saldoCuentaSuperior.getSumCredito().add(movimiento.getCredito()));

			BigDecimal total = BigDecimal.ZERO;

			total = total.add(saldoCuentaSuperior.getSumDebito());
			total = total.subtract(saldoCuentaSuperior.getSumCredito());

			if(total.signum() < 0) {
				saldoCuentaSuperior.setTotalCredito(total);
			}else if(total.signum() > 0) {
				saldoCuentaSuperior.setTotalDebito(total);
			}

			daoSaldoCuenta.save(saldoCuentaSuperior);

			cadena = cuenta.getCuentaSuperior();
			cuenta = daoCuenta.getOne(saldo.getId().getCuenta().getCuentaSuperior());
		}while(!cadena.equals(cuenta.getCuentaSuperior()) || cadena.isBlank());
				 

	}
}

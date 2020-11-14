package com.example.demo.Controllers;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.dto.DtoSaldos;
import com.example.demo.model.dto.Remayorizacion;
import com.example.demo.model.pojos.*;
import com.example.demo.model.service.*;


@RestController
@RequestMapping("pro/especial/remayorizacion")
public class RemayorizacionController {


	@Autowired private ISaldoCuentaService saldoCuentaService;
	@Autowired private ISaldoInicialService saldoInicialService;
	@Autowired private ICuentaService cuentaService;
	/*@Autowired private ISaldoCCostosService saldoCCostosService;
	@Autowired private ISaldoIncialCCostosService saldoIncialCCostosService;
	@Autowired private ISaldoTerceroService saldoTerceroService;
	@Autowired private ISaldoInicialTerceroService saldoInicialTerceroService;*/
	@Autowired private IMovimientoService movimientosService;
	@Autowired private IMesService mesService;
	@Autowired private IAnnoService annoService;
	//@Autowired ISaldoCuentaService saldoCuentaService;


	@GetMapping("/remayorizar")
	public ResponseEntity<String> remayorizar(@RequestBody Remayorizacion remayorizacion) {

		ResponseEntity<String> respuesta;
		//= new ResponseEntity<>(HttpStatus.ACCEPTED);

		LocalDate d1 = LocalDate.of(remayorizacion.getAnioConsulta(), remayorizacion.getMesInicial(), 1);
		LocalDate d2 = LocalDate.of(remayorizacion.getAnioConsulta(), remayorizacion.getMesFinal()+1, 1);

		Vector<Mes> rangoMeses = new Vector<Mes>();

		rangoMeses = (Vector<Mes>) mesService.buscarMesesEnRango(d1, d2);	

		Optional<Anno> anno = annoService.findById(remayorizacion.getAnioConsulta());

		if(anno.isPresent() && !anno.get().isCerrado()) {

			if(rangoMeses.size() == (remayorizacion.getMesFinal()-remayorizacion.getMesInicial()+1)) {
				
				for (Mes mes : rangoMeses) {
					
					saldoCuentaService.borrarPorMes(mes);
					
					ArrayList<SaldoCuenta> saldos = new ArrayList<SaldoCuenta>();
					
					Vector<DtoSaldos> saldosAnteriores = sacarSaldosAnteriore(mes);
					Vector<Cuenta> cuentas = (Vector<Cuenta>) cuentaService.findAll();
						
					Vector<Movimiento> movimientosDelMes = (Vector<Movimiento>) movimientosService.buscarPorMes(mes);
					
					for (DtoSaldos dto : saldosAnteriores) {
						
						SaldoCuenta saldo = SaldoCuenta.builder().
								id(IdSaldo.builder().
										mes(mes).
										cuenta(dto.getCuenta()).
										build()).
								sumCredito(0).
								sumDebito(0).
								build();
						
						
						
						for(Movimiento movimiento : movimientosDelMes.stream().
								filter(m -> m.getCuenta().equals(dto.getCuenta())).
								collect(Collectors.toList())) {
							
							saldo.setSumCredito(saldo.getSumCredito()+ movimiento.getCredito());
							saldo.setSumDebito(saldo.getSumDebito()+ movimiento.getDebito());
							
							
						}
						
						saldo.setTotalCredito(saldo.getTotalCredito()+ saldo.getTotalCredito());
						saldo.setTotalDebito(saldo.getTotalDebito()+ saldo.getSumDebito());
						
						saldos.add(saldo);
					}
					
					saldoCuentaService.saveMany(saldos);
				}
				
				respuesta = new ResponseEntity<>(HttpStatus.ACCEPTED);
			
			}else {
				respuesta = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}


		}else {
			respuesta = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}


		return respuesta;
	}


	private Vector<DtoSaldos> sacarSaldosAnteriore(Mes mes){
		
		
		Vector<DtoSaldos> saldos;
		
		if(mes.getIdFiscal().getMes().equalsIgnoreCase("enero")) {
			
			saldos = (Vector<DtoSaldos>) saldoInicialService.findByAnno(mes.getIdFiscal().getAnno());
			
		}else {
			
			LocalDate d = mes.getInicio().minusDays(1);
			
			saldos = new Vector<DtoSaldos>();
			
			saldoCuentaService.
			findByMes(mesService.findByFechaFin(d).get()).
			forEach(s -> saldos.add(DtoSaldos.extraerDatosSaldos(s)));
			
		}
		
		return saldos;
	}





}

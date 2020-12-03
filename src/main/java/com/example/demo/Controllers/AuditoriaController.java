package com.example.demo.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.dto.Auditoria;
import com.example.demo.model.dto.DTODebitoDiferenteDeCredito;
import com.example.demo.model.pojos.Mes;
import com.example.demo.model.pojos.Movimiento;
import com.example.demo.model.service.AnnoService;
import com.example.demo.model.service.IMesService;
import com.example.demo.model.service.IMovimientoService;

@RestController
@RequestMapping("pro/auditoria")
public class AuditoriaController {

	@Autowired
	private IMovimientoService movimientosService;

	@Autowired
	private AnnoService serviceAnios;

	@Autowired
	private IMesService serviceMes;
	
	@PostMapping("/debitosDiferenteCreditos")	
	public List<DTODebitoDiferenteDeCredito> debitosDiferenteCreditos(@RequestBody Mes mesInicio, @RequestBody Mes mesFinal) {

		return movimientosService.debitosDiferenteCreditos(mesInicio.getInicio(), mesFinal.getFin());
	}
	
	@PostMapping("/cuentasNoAfectables")	
	public List<Movimiento> cuentasNoAfectables(@RequestBody Auditoria auditoria) {

		return movimientosService.movimientosEnCuentasNoAfectables(auditoria.getMesInicial().getInicio(), auditoria.getMesFinal().getFin());
	}
	
	@PostMapping("/movimientosSinValor")	
	public List<Movimiento> movimientosSinValor(@RequestBody Auditoria auditoria) {

		return movimientosService.movimientosSinValor(auditoria.getMesInicial().getInicio(), auditoria.getMesFinal().getFin());
	}
	
	@PostMapping("/movimientosSinTerceros")	
	public List<Movimiento> movimientosSinTerceros(@RequestBody Auditoria auditoria) {

		return movimientosService.movimientosSinTerceros(auditoria.getMesInicial().getInicio(), auditoria.getMesFinal().getFin());
	}
	
	@PostMapping("/movimientosConTercerosInnecesarios")	
	public List<Movimiento> movimientosConTercerosInnecesarios(@RequestBody Auditoria auditoria) {

		return movimientosService.movimientosConTercerosInnecesarios(auditoria.getMesInicial().getInicio(), auditoria.getMesFinal().getFin());
	}
	
	@PostMapping("/movimientosSinCentros")	
	public List<Movimiento> movimientosSinCentros(@RequestBody Auditoria auditoria) {

		return movimientosService.movimientosSinCentros(auditoria.getMesInicial().getInicio(), auditoria.getMesFinal().getFin());
	}
	
	@PostMapping("/movimientosConCentrosInnecesarios")	
	public List<Movimiento> movimientosConCentrosInnecesarios(@RequestBody Auditoria auditoria) {

		return movimientosService.movimientosConCentrosInnecesarios(auditoria.getMesInicial().getInicio(), auditoria.getMesFinal().getFin());
	}
}

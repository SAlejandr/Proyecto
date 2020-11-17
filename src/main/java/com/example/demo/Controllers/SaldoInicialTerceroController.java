package com.example.demo.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.pojos.IdSaldoInCCostos;
import com.example.demo.model.pojos.IdSaldoInTercero;
import com.example.demo.model.pojos.IdSaldoInicial;
import com.example.demo.model.pojos.SaldoInicialTercero;
import com.example.demo.model.pojos.SaldoInicialTercero;
import com.example.demo.model.service.ISaldoIncialCCostosService;
import com.example.demo.model.service.ISaldoInicialService;
import com.example.demo.model.service.ISaldoInicialTerceroService;

@RestController
@RequestMapping("pro/saldos/saldoInicialTercero")
public class SaldoInicialTerceroController {

	@Autowired private ISaldoInicialTerceroService service;
	@Autowired private ISaldoInicialService subservice;

	@PostMapping(value = "/add")
	public ResponseEntity<SaldoInicialTercero> addSaldoInicial(@RequestBody SaldoInicialTercero saldoInicial) {
		//TODO: process POST request

		ResponseEntity<SaldoInicialTercero> respuesta;


		if(subservice.findById(IdSaldoInicial.builder().
				cuenta(saldoInicial.getId().getIdSaldoIn().getCuenta()).
				anno(saldoInicial.getId().getIdSaldoIn().getAnno()).
				build()).isPresent()) {
			Optional<SaldoInicialTercero> optional = service.findById(saldoInicial.getId());

			if(!optional.isPresent())
				respuesta = new ResponseEntity<SaldoInicialTercero>(HttpStatus.CREATED);
			else
				respuesta = new ResponseEntity<SaldoInicialTercero>(HttpStatus.BAD_REQUEST);
		}else
			respuesta = new ResponseEntity<SaldoInicialTercero>(HttpStatus.BAD_REQUEST);

		return respuesta;

	}

	@PutMapping(value = "/update")
	public ResponseEntity<SaldoInicialTercero> updateSaldoInicial(@RequestBody SaldoInicialTercero saldoInicial) {
		//TODO: process PUT request

		ResponseEntity<SaldoInicialTercero> respuesta;


		if(subservice.findById(IdSaldoInicial.builder().
				cuenta(saldoInicial.getId().getIdSaldoIn().getCuenta()).
				anno(saldoInicial.getId().getIdSaldoIn().getAnno()).
				build()).isPresent()) {
			Optional<SaldoInicialTercero> optional = service.findById(saldoInicial.getId());

			if(optional.isPresent())
				respuesta = new ResponseEntity<SaldoInicialTercero>(HttpStatus.ACCEPTED);
			else
				respuesta = new ResponseEntity<SaldoInicialTercero>(HttpStatus.BAD_REQUEST);
		}else
			respuesta = new ResponseEntity<SaldoInicialTercero>(HttpStatus.BAD_REQUEST);

		return respuesta;
	}

	@DeleteMapping(value = "/delete")
	public ResponseEntity<SaldoInicialTercero> deleteSaldoInicial(@RequestBody IdSaldoInTercero id ) {
		//TODO: process DELETE request

		ResponseEntity<SaldoInicialTercero> respuesta;

		if(service.deleteById(id))
			respuesta = new ResponseEntity<SaldoInicialTercero>(HttpStatus.OK);
		else
			respuesta = new ResponseEntity<SaldoInicialTercero>(HttpStatus.BAD_REQUEST);	

		return respuesta;
	}

	@GetMapping(value = "/get")
	public ResponseEntity<SaldoInicialTercero> getSaldo(@RequestBody IdSaldoInTercero id) {

		ResponseEntity<SaldoInicialTercero> respuesta;

		Optional<SaldoInicialTercero> optional = service.findById(id);

		if(optional.isPresent())
			respuesta = new ResponseEntity<SaldoInicialTercero>(optional.get(),HttpStatus.OK);
		else
			respuesta = new ResponseEntity<SaldoInicialTercero>(HttpStatus.BAD_REQUEST);

		return respuesta;
	}


	@GetMapping(value = "/getAll")
	public List<SaldoInicialTercero> getAll(){

		return service.findAll();
	}

}

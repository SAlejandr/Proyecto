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
import com.example.demo.model.pojos.IdSaldoInicial;
import com.example.demo.model.pojos.SaldoInicialCentroDeCostos;
import com.example.demo.model.pojos.SaldoInicialCentroDeCostos;
import com.example.demo.model.service.ISaldoIncialCCostosService;
import com.example.demo.model.service.ISaldoInicialService;

@RestController
@RequestMapping("pro/saldos/saldoInicialCCostos")
public class SaldoInicialCCostosController {

	@Autowired private ISaldoIncialCCostosService service;
	@Autowired private ISaldoInicialService subservice;

	@PostMapping(value = "/add")
	public ResponseEntity<SaldoInicialCentroDeCostos> addSaldoInicial(@RequestBody SaldoInicialCentroDeCostos saldoInicial) {
		//TODO: process POST request

		ResponseEntity<SaldoInicialCentroDeCostos> respuesta;

		Optional<SaldoInicialCentroDeCostos> optional = service.findById(saldoInicial.getElId());

		if(optional.isPresent())
			respuesta = new ResponseEntity<SaldoInicialCentroDeCostos>(HttpStatus.BAD_REQUEST);
		else
			respuesta = new ResponseEntity<SaldoInicialCentroDeCostos>(HttpStatus.CREATED);

		return respuesta;

	}

	@PutMapping(value = "/update")
	public ResponseEntity<SaldoInicialCentroDeCostos> updateSaldoInicial(@RequestBody SaldoInicialCentroDeCostos saldoInicial) {
		//TODO: process PUT request

		ResponseEntity<SaldoInicialCentroDeCostos> respuesta;

		Optional<SaldoInicialCentroDeCostos> optional = service.findById(saldoInicial.getElId());

		if(!optional.isPresent())
			respuesta = new ResponseEntity<SaldoInicialCentroDeCostos>(HttpStatus.BAD_REQUEST);
		else
			respuesta = new ResponseEntity<SaldoInicialCentroDeCostos>(HttpStatus.ACCEPTED);

		return respuesta;
	}

	@DeleteMapping(value = "/delete")
	public ResponseEntity<SaldoInicialCentroDeCostos> deleteSaldoInicial(@RequestBody IdSaldoInCCostos id ) {
		//TODO: process DELETE request

		ResponseEntity<SaldoInicialCentroDeCostos> respuesta;

		if(service.deleteById(id))
			respuesta = new ResponseEntity<SaldoInicialCentroDeCostos>(HttpStatus.CREATED);
		else
			respuesta = new ResponseEntity<SaldoInicialCentroDeCostos>(HttpStatus.BAD_REQUEST);	

		return respuesta;
	}

	@GetMapping(value = "/get")
	public ResponseEntity<SaldoInicialCentroDeCostos> getSaldo(@RequestBody IdSaldoInCCostos id) {
		
		ResponseEntity<SaldoInicialCentroDeCostos> respuesta;

		Optional<SaldoInicialCentroDeCostos> optional = service.findById(id);

		if(optional.isPresent())
			respuesta = new ResponseEntity<SaldoInicialCentroDeCostos>(optional.get(),HttpStatus.OK);
		else
			respuesta = new ResponseEntity<SaldoInicialCentroDeCostos>(HttpStatus.BAD_REQUEST);

		return respuesta;
	}


	@GetMapping(value = "/getAll")
	public List<SaldoInicialCentroDeCostos> getAll(){
		
		return service.findAll();
	}
}

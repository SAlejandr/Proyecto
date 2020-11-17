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

import com.example.demo.model.pojos.IdSaldoCCostos;
import com.example.demo.model.pojos.SaldoCentroDeCostos;
import com.example.demo.model.service.ISaldoCCostosService;
import com.example.demo.model.service.SaldoCCostosService;

public class SaldoCCostosController {
	@Autowired private ISaldoCCostosService service;

	@PostMapping(value = "/add")
	public ResponseEntity<SaldoCentroDeCostos> addSaldoCCostos(@RequestBody SaldoCentroDeCostos saldo) {
		//TODO: process POST request

		ResponseEntity<SaldoCentroDeCostos> respuesta;

		Optional<SaldoCentroDeCostos> optional = service.findById(saldo.getElID());

		if(optional.isPresent())
			respuesta = new ResponseEntity<SaldoCentroDeCostos>(HttpStatus.BAD_REQUEST);
		else
			respuesta = new ResponseEntity<SaldoCentroDeCostos>(HttpStatus.CREATED);

		return respuesta;
	}

	@PutMapping(value = "/update")
	public ResponseEntity<SaldoCentroDeCostos> updateSaldoCCostos(@RequestBody SaldoCentroDeCostos saldo) {
		ResponseEntity<SaldoCentroDeCostos> respuesta;

		Optional<SaldoCentroDeCostos> optional = service.findById(saldo.getElID());

		if(!optional.isPresent())
			respuesta = new ResponseEntity<SaldoCentroDeCostos>(HttpStatus.BAD_REQUEST);
		else
			respuesta = new ResponseEntity<SaldoCentroDeCostos>(HttpStatus.ACCEPTED);

		return respuesta;
	}

	@GetMapping(value = "/get")
	public ResponseEntity<SaldoCentroDeCostos> getSaldo(@RequestBody IdSaldoCCostos id){

		ResponseEntity<SaldoCentroDeCostos> respuesta;

		Optional<SaldoCentroDeCostos> optional = service.findById(id);

		if(!optional.isPresent())
			respuesta = new ResponseEntity<SaldoCentroDeCostos>(HttpStatus.BAD_REQUEST);
		else
			respuesta = new ResponseEntity<SaldoCentroDeCostos>(optional.get(),HttpStatus.OK);

		return respuesta;

	}

	@GetMapping(value = "/getAll")
	public List<SaldoCentroDeCostos> getAll(){
		
		return service.findAll();
	}
	
	@DeleteMapping(value = "/delete/")
	public ResponseEntity<SaldoCentroDeCostos> deleteSaldoCCostos(@RequestBody IdSaldoCCostos id ) {
		//TODO: process DELETE request
		
		ResponseEntity<SaldoCentroDeCostos> respuesta;
		
		if(service.deleteById(id))
			respuesta = new ResponseEntity<SaldoCentroDeCostos>(HttpStatus.ACCEPTED);
		else
			respuesta = new ResponseEntity<SaldoCentroDeCostos>(HttpStatus.NOT_FOUND);
		
		return respuesta;
	}
}

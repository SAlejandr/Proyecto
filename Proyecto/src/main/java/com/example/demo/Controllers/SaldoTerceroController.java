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

import com.example.demo.model.pojos.IdSaldo;
import com.example.demo.model.pojos.IdSaldoTercero;
import com.example.demo.model.pojos.SaldoTercero;
import com.example.demo.model.service.ISaldoTerceroService;
import com.example.demo.model.service.SaldoTerceroService;
import com.example.demo.model.service.SaldoTerceroService;

@RestController
@RequestMapping("pro/saldos/saldoTercero")
public class SaldoTerceroController {
	

	@Autowired private ISaldoTerceroService service;

	@PostMapping(value = "/add")
	public ResponseEntity<SaldoTercero> addSaldoTercero(@RequestBody SaldoTercero saldo) {
		//TODO: process POST request

		ResponseEntity<SaldoTercero> respuesta;

		Optional<SaldoTercero> optional = service.findById(saldo.getId());

		if(optional.isPresent())
			respuesta = new ResponseEntity<SaldoTercero>(HttpStatus.BAD_REQUEST);
		else
			respuesta = new ResponseEntity<SaldoTercero>(HttpStatus.CREATED);

		return respuesta;
	}

	@PutMapping(value = "/update")
	public ResponseEntity<SaldoTercero> updateSaldoTercero(@RequestBody SaldoTercero saldo) {
		ResponseEntity<SaldoTercero> respuesta;

		Optional<SaldoTercero> optional = service.findById(saldo.getId());

		if(!optional.isPresent())
			respuesta = new ResponseEntity<SaldoTercero>(HttpStatus.BAD_REQUEST);
		else
			respuesta = new ResponseEntity<SaldoTercero>(HttpStatus.ACCEPTED);

		return respuesta;
	}

	@GetMapping(value = "/get")
	public ResponseEntity<SaldoTercero> getSaldoTercero(@RequestBody IdSaldoTercero id){

		ResponseEntity<SaldoTercero> respuesta;

		Optional<SaldoTercero> optional = service.findById(id);

		if(!optional.isPresent())
			respuesta = new ResponseEntity<SaldoTercero>(HttpStatus.BAD_REQUEST);
		else
			respuesta = new ResponseEntity<SaldoTercero>(optional.get(),HttpStatus.OK);

		return respuesta;

	}

	@GetMapping(value = "/getAll")
	public List<SaldoTercero> getAll(){
		
		return service.findAll();
	}
	
	@DeleteMapping(value = "/delete/")
	public ResponseEntity<SaldoTercero> deleteSaldoTercero(@RequestBody IdSaldoTercero id ) {
		//TODO: process DELETE request
		
		ResponseEntity<SaldoTercero> respuesta;
		
		if(service.deleteById(id))
			respuesta = new ResponseEntity<SaldoTercero>(HttpStatus.ACCEPTED);
		else
			respuesta = new ResponseEntity<SaldoTercero>(HttpStatus.NOT_FOUND);
		
		return respuesta;
	}
}

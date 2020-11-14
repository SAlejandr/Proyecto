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
import com.example.demo.model.pojos.SaldoCuenta;
import com.example.demo.model.service.ISaldoCuentaService;
import com.example.demo.model.service.SaldoCuentaService;

@RestController
@RequestMapping("pro/saldos/saldoCuenta")
public class SaldoCuentaController {

	@Autowired private ISaldoCuentaService service;

	@PostMapping(value = "/add")
	public ResponseEntity<SaldoCuenta> addSaldo(@RequestBody SaldoCuenta saldo) {
		//TODO: process POST request

		ResponseEntity<SaldoCuenta> respuesta;

		Optional<SaldoCuenta> optional = service.findById(saldo.getId());

		if(optional.isPresent())
			respuesta = new ResponseEntity<SaldoCuenta>(HttpStatus.BAD_REQUEST);
		else
			respuesta = new ResponseEntity<SaldoCuenta>(HttpStatus.CREATED);

		return respuesta;
	}

	@PutMapping(value = "/update")
	public ResponseEntity<SaldoCuenta> updateSaldo(@RequestBody SaldoCuenta saldo) {
		ResponseEntity<SaldoCuenta> respuesta;

		Optional<SaldoCuenta> optional = service.findById(saldo.getId());

		if(!optional.isPresent())
			respuesta = new ResponseEntity<SaldoCuenta>(HttpStatus.BAD_REQUEST);
		else
			respuesta = new ResponseEntity<SaldoCuenta>(HttpStatus.ACCEPTED);

		return respuesta;
	}

	@GetMapping(value = "/get")
	public ResponseEntity<SaldoCuenta> getSaldo(@RequestBody IdSaldo id){

		ResponseEntity<SaldoCuenta> respuesta;

		Optional<SaldoCuenta> optional = service.findById(id);

		if(!optional.isPresent())
			respuesta = new ResponseEntity<SaldoCuenta>(HttpStatus.BAD_REQUEST);
		else
			respuesta = new ResponseEntity<SaldoCuenta>(optional.get(),HttpStatus.OK);

		return respuesta;

	}

	@GetMapping(value = "/getAll")
	public List<SaldoCuenta> getAll(){
		
		return service.findAll();
	}
	
	@DeleteMapping(value = "/delete/")
	public ResponseEntity<SaldoCuenta> deleteSaldo(@RequestBody IdSaldo id ) {
		//TODO: process DELETE request
		
		ResponseEntity<SaldoCuenta> respuesta;
		
		if(service.deleteById(id))
			respuesta = new ResponseEntity<SaldoCuenta>(HttpStatus.ACCEPTED);
		else
			respuesta = new ResponseEntity<SaldoCuenta>(HttpStatus.NOT_FOUND);
		
		return respuesta;
	}

}

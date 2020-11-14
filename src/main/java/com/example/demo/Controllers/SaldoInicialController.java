package com.example.demo.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.pojos.IdSaldoInicial;
import com.example.demo.model.pojos.SaldoInicialCuenta;
import com.example.demo.model.service.ISaldoIncialCCostosService;
import com.example.demo.model.service.ISaldoInicialService;

@RestController
@RequestMapping("pro/saldos/saldoInicial")
public class SaldoInicialController {

	@Autowired private ISaldoInicialService service;

	@PostMapping(value = "/add")
	public ResponseEntity<SaldoInicialCuenta> addSaldoInicial(@RequestBody SaldoInicialCuenta saldoInicial) {
		//TODO: process POST request

		ResponseEntity<SaldoInicialCuenta> respuesta;

		Optional<SaldoInicialCuenta> optional = service.findById(saldoInicial.getId());

		if(optional.isPresent())
			respuesta = new ResponseEntity<SaldoInicialCuenta>(HttpStatus.BAD_REQUEST);
		else
			respuesta = new ResponseEntity<SaldoInicialCuenta>(HttpStatus.CREATED);

		return respuesta;

	}

	@PutMapping(value = "/update")
	public ResponseEntity<SaldoInicialCuenta> updateSaldoInicial(@RequestBody SaldoInicialCuenta saldoInicial) {
		//TODO: process PUT request

		ResponseEntity<SaldoInicialCuenta> respuesta;

		Optional<SaldoInicialCuenta> optional = service.findById(saldoInicial.getId());

		if(!optional.isPresent())
			respuesta = new ResponseEntity<SaldoInicialCuenta>(HttpStatus.BAD_REQUEST);
		else
			respuesta = new ResponseEntity<SaldoInicialCuenta>(HttpStatus.ACCEPTED);

		return respuesta;
	}

	@DeleteMapping(value = "/delete")
	public ResponseEntity<SaldoInicialCuenta> deleteSaldoInicial(@RequestBody IdSaldoInicial id ) {
		//TODO: process DELETE request

		ResponseEntity<SaldoInicialCuenta> respuesta;

		if(service.deleteById(id))
			respuesta = new ResponseEntity<SaldoInicialCuenta>(HttpStatus.CREATED);
		else
			respuesta = new ResponseEntity<SaldoInicialCuenta>(HttpStatus.BAD_REQUEST);	

		return respuesta;
	}

	@GetMapping(value = "/get")
	public ResponseEntity<SaldoInicialCuenta> getSaldo(@RequestBody IdSaldoInicial id) {
		
		ResponseEntity<SaldoInicialCuenta> respuesta;

		Optional<SaldoInicialCuenta> optional = service.findById(id);

		if(optional.isPresent())
			respuesta = new ResponseEntity<SaldoInicialCuenta>(optional.get(),HttpStatus.OK);
		else
			respuesta = new ResponseEntity<SaldoInicialCuenta>(HttpStatus.BAD_REQUEST);

		return respuesta;
	}


	@GetMapping(value = "/getAll")
	public List<SaldoInicialCuenta> getAll(){
		
		return service.findAll();
	}

}

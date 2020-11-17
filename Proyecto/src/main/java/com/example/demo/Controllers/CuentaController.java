package com.example.demo.Controllers;

import java.util.List;import java.util.stream.Collector;
import java.util.stream.Collectors;

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

import com.example.demo.model.pojos.Cuenta;
import com.example.demo.model.service.ICuentaService;

@RestController
@RequestMapping("pro/cuentas")
public class CuentaController {
	
	@Autowired private ICuentaService service;
	
	@PostMapping("/add")
	public ResponseEntity<Cuenta> postMethodName(@RequestBody Cuenta cuenta) {
		//TODO: process POST request		
		HttpStatus status;
		if(!service.findById(cuenta.getCodCuenta()).isPresent()) {
			service.save(cuenta);
			status = HttpStatus.ACCEPTED;
		}else {
			status =HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<Cuenta>(status);
	}


	@PutMapping("/update")
	public ResponseEntity<Cuenta> putMethodName(@RequestBody Cuenta cuenta) {
		//TODO: process PUT request
		HttpStatus status;
		if(service.findById(cuenta.getCodCuenta()).isPresent()) {
			service.save(cuenta);
			status = HttpStatus.ACCEPTED;
		}else {
			status =HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<Cuenta>(status);
	}

	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<Cuenta> deleteMethodName(@RequestParam Integer id) {
		//TODO: process DELETE request
		
		ResponseEntity<Cuenta> response;
		
		if(service.deleteById(id))
			response = new ResponseEntity<Cuenta>(HttpStatus.OK);
		else 
			response = new ResponseEntity<Cuenta>(HttpStatus.BAD_REQUEST);
		
		return response;
	}

	
	@GetMapping("/get")
	public ResponseEntity<Cuenta> get(@RequestParam int id) {
		
		Cuenta c = service.findById(id).orElse(Cuenta.builder().codCuenta(id).build());
		
		return new ResponseEntity<Cuenta>(c, HttpStatus.OK);
	}

	@GetMapping("/getAll/0")
	public List<Cuenta> getAll() {
		return service.findAll();
	}

	@GetMapping("/getAll/1")
	public List<Cuenta> getMovibles() {
		return service.findAll().stream().filter(c -> c.isMovimientos()).collect(Collectors.toList());
	}

	@GetMapping("/getAll/2")
	public List<Cuenta> getMoviblesConTerceros() {
		return service.findAll().stream().filter(c -> c.isMovimientos() && c.isTerceros()).collect(Collectors.toList());
	}

	@GetMapping("/getAll/3")
	public List<Cuenta> getMovivlesConCCostos() {
		return service.findAll().stream().filter(c -> c.isMovimientos() && c.isCcostos()).collect(Collectors.toList());
	}

	
	
}

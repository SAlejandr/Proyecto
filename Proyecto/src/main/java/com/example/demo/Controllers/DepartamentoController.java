package com.example.demo.Controllers;

import java.util.List;

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

import com.example.demo.model.pojos.Departamento;
import com.example.demo.model.service.IDepartamentoService;

@RestController
@RequestMapping("pro/departamento")
public class DepartamentoController {

	@Autowired private IDepartamentoService service;

	@PostMapping(value = "/add")
	public ResponseEntity<Departamento> add(@RequestBody Departamento entity) {
		//TODO: process POST request
		ResponseEntity<Departamento> respuesta;

		if(!service.findById(entity.getCodDepartamento()).isPresent()) { 
			respuesta = new ResponseEntity<Departamento>(HttpStatus.CREATED);
			service.save(entity);
		}else
			respuesta = new ResponseEntity<Departamento>(HttpStatus.BAD_REQUEST);


		return respuesta;
	}

	@PutMapping(value = "/update")
	public ResponseEntity<Departamento> update(@RequestBody Departamento entity) {
		//TODO: process PUT request
		ResponseEntity<Departamento> respuesta;

		if(service.findById(entity.getCodDepartamento()).isPresent()) { 
			respuesta = new ResponseEntity<Departamento>(HttpStatus.ACCEPTED);
			service.save(entity);
		}else
			respuesta = new ResponseEntity<Departamento>(HttpStatus.BAD_REQUEST);


		return respuesta;
	}

	@GetMapping(value = "/get")
	public ResponseEntity<Departamento> get(@RequestParam Integer param) {
		
		ResponseEntity<Departamento> respuesta;
		if(service.findById(param).isPresent()) {
			respuesta = new ResponseEntity<Departamento>(service.findById(param).get(), HttpStatus.ACCEPTED);
			
		}else {
			respuesta = new ResponseEntity<Departamento>(HttpStatus.BAD_REQUEST);
		}
		
		return respuesta;
	}

	@GetMapping(value = "/get/all")
	public List<Departamento> getMethodName() {
		return service.findAll();
	}

	@DeleteMapping(value = "/delete")
	public ResponseEntity<Departamento> deleteMethodName(@RequestParam Integer id) {
		//TODO: process DELETE request
		
		HttpStatus status = service.deleteById(id)?HttpStatus.ACCEPTED:HttpStatus.BAD_REQUEST;
		
		return new ResponseEntity<Departamento>(status);
	}

}

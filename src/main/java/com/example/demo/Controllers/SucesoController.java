package com.example.demo.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.pojos.Suceso;
import com.example.demo.model.service.ISucesoService;

@RestController
@RequestMapping("pro/suceso")
public class SucesoController {

	@Autowired private ISucesoService service;

	@PostMapping(value = "/add")
	public ResponseEntity<Suceso> add(@RequestBody Suceso entity) {
		//TODO: process POST request
		ResponseEntity<Suceso> respuesta;

		if(!service.findById(entity.getCodSuceso()).isPresent()) { 
			respuesta = new ResponseEntity<Suceso>(HttpStatus.CREATED);
			service.save(entity);
		}else
			respuesta = new ResponseEntity<Suceso>(HttpStatus.BAD_REQUEST);


		return respuesta;
	}

	@PutMapping(value = "/update")
	public ResponseEntity<Suceso> update(@RequestBody Suceso entity) {
		//TODO: process PUT request
		ResponseEntity<Suceso> respuesta;

		if(service.findById(entity.getCodSuceso()).isPresent()) { 
			respuesta = new ResponseEntity<Suceso>(HttpStatus.ACCEPTED);
			service.save(entity);
		}else
			respuesta = new ResponseEntity<Suceso>(HttpStatus.BAD_REQUEST);


		return respuesta;
	}

	@GetMapping(value = "/get")
	public ResponseEntity<Suceso> get(@RequestParam String param) {
		
		ResponseEntity<Suceso> respuesta;
		if(service.findById(param).isPresent()) {
			respuesta = new ResponseEntity<Suceso>(service.findById(param).get(), HttpStatus.ACCEPTED);
			
		}else {
			respuesta = new ResponseEntity<Suceso>(HttpStatus.BAD_REQUEST);
		}
		
		return respuesta;
	}

	@GetMapping(value = "/get/all")
	public List<Suceso> getMethodName() {
		return service.findAll();
	}

	@DeleteMapping(value = "/delete")
	public ResponseEntity<Suceso> deleteMethodName(@RequestParam String id) {
		//TODO: process DELETE request
		
		HttpStatus status = service.deleteById(id)?HttpStatus.ACCEPTED:HttpStatus.BAD_REQUEST;
		
		return new ResponseEntity<Suceso>(status);
	}

}

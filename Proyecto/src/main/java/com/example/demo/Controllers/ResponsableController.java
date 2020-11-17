package com.example.demo.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.pojos.Responsable;
import com.example.demo.model.service.IResponsableService;

@RestController
@RequestMapping("pro/responsable")
public class ResponsableController {

	@Autowired private IResponsableService service;
	
	@PostMapping(value = "/add")
	public ResponseEntity<Responsable> addResponsable(@RequestBody Responsable responsable) {
		//TODO: process POST request
		
		ResponseEntity<Responsable> respuesta;
		Optional<Responsable> optional = service.findById(responsable.getCodRes());
		
		if(optional.isPresent()) 
			respuesta = new ResponseEntity<Responsable>(HttpStatus.BAD_REQUEST);
		else {
			service.save(responsable);
			respuesta = new ResponseEntity<Responsable>(HttpStatus.ACCEPTED);
		}
		
		return respuesta;
	}
	
	@PutMapping(value = "/update")
	public ResponseEntity<Responsable> updateResponsable(@RequestBody Responsable responsable) {
		//TODO: process POST request
		
		ResponseEntity<Responsable> respuesta;
		Optional<Responsable> optional = service.findById(responsable.getCodRes());
		
		if(!optional.isPresent()) 
			respuesta = new ResponseEntity<Responsable>(HttpStatus.BAD_REQUEST);
		else {
			service.save(responsable);
			respuesta = new ResponseEntity<Responsable>(HttpStatus.ACCEPTED);
		}
		
		return respuesta;
	}
	
	@GetMapping(value = "/get")
	public ResponseEntity<Responsable> getResponsable(@RequestParam int id) {
		
		ResponseEntity<Responsable> respuesta;
		Optional<Responsable> optional = service.findById(id);
		
		if(optional.isPresent()) 
			respuesta =new ResponseEntity<Responsable>(optional.get(), HttpStatus.ACCEPTED);
		else 
			respuesta = new ResponseEntity<Responsable>(HttpStatus.BAD_REQUEST);
		
		return respuesta;
	}
	
	@GetMapping(value = "/getAll")
	public List<Responsable> getlAll() {
		return service.findAll();
	}

	
	
	
}

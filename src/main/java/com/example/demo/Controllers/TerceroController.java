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

import com.example.demo.model.pojos.Tercero;
import com.example.demo.model.service.ICiudadService;
import com.example.demo.model.service.ITerceroService;

@RestController
@RequestMapping("pro/tercero")
public class TerceroController {

	@Autowired private ITerceroService service;
	
	@PostMapping(value = "/add")
	public ResponseEntity<Tercero> addTercero(@RequestBody Tercero tercero) {
		//TODO: process POST request
		
		ResponseEntity<Tercero> respuesta;
	
		Optional<Tercero> optional = service.findById(tercero.getId());
		
		if(optional.isPresent())
			respuesta = new ResponseEntity<Tercero>(HttpStatus.BAD_REQUEST);
		else {
			service.save(tercero);
			respuesta = new ResponseEntity<Tercero>(HttpStatus.ACCEPTED);
		}

		return respuesta;
	}

	@PutMapping(value = "/update")
	public ResponseEntity<Tercero> updateTercero(@RequestBody Tercero tercero) {
		//TODO: process PUT request
		
		ResponseEntity<Tercero> respuesta;
		
		Optional<Tercero> optional = service.findById(tercero.getId());
		
		if(!optional.isPresent())
			respuesta = new ResponseEntity<Tercero>(HttpStatus.BAD_REQUEST);
		else {
			service.save(tercero);
			respuesta = new ResponseEntity<Tercero>(HttpStatus.ACCEPTED);
		}

		return respuesta;
	}

	@DeleteMapping(value = "/delete")
	public ResponseEntity<Tercero> deleteTercero(@RequestParam String id  ) {
		//TODO: process DELETE request
		
		ResponseEntity<Tercero> respuesta;
		
		if(service.deleteById(id))
			respuesta = new ResponseEntity<Tercero>(HttpStatus.OK);
		else
			respuesta = new ResponseEntity<Tercero>(HttpStatus.BAD_REQUEST);
		
		return respuesta;
	}

	@GetMapping(value = "/get")
	public ResponseEntity<Tercero> getTercero(@RequestParam String id) {
		
		ResponseEntity<Tercero> respuesta;
		
		Optional<Tercero> optional = service.findById(id);
		
		if(optional.isPresent()) 
			respuesta = new ResponseEntity<Tercero>(optional.get(), HttpStatus.OK);
		else
			respuesta = new ResponseEntity<Tercero>(HttpStatus.NOT_FOUND);
		
		return respuesta;
	}
	
	@GetMapping(value = "/getAll")
	public List<Tercero> getAll() {
		return service.findAll();
	}


	
}

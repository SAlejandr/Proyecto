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

import com.example.demo.model.pojos.Estado;
import com.example.demo.model.service.IEstadoService;

@RestController
@RequestMapping("pro/estado")
public class EstadoController {

	@Autowired IEstadoService service;

	@GetMapping(value = "/getAll")
	public List<Estado> findAll() {
		return service.findAll();
	}

	@GetMapping(value = "/get")
	public ResponseEntity<Estado> findById(@RequestParam String id) {

		ResponseEntity<Estado> respuesta;

		Optional<Estado> optional = service.findById(id);

		if(optional.isPresent())
			respuesta = new ResponseEntity<Estado>(optional.get(), HttpStatus.OK);
		else
			respuesta = new ResponseEntity<Estado>(HttpStatus.BAD_REQUEST);

		return respuesta;
	}

	@PostMapping(value = "/add")
	public ResponseEntity<Estado> add(@RequestBody Estado estado) {
		//TODO: process POST request

		ResponseEntity<Estado> respuesta;

		Optional<Estado> optional = service.findById(estado.getCodEstado());

		if(!optional.isPresent()) {
			respuesta = new ResponseEntity<Estado>( HttpStatus.ACCEPTED);
			service.save(estado);
		}else
			respuesta = new ResponseEntity<Estado>(HttpStatus.BAD_REQUEST);

		return respuesta;
	}

	@PutMapping(value = "/update")
	public ResponseEntity<Estado> update(@RequestBody Estado estado) {
		//TODO: process PUT request

		ResponseEntity<Estado> respuesta;

		Optional<Estado> optional = service.findById(estado.getCodEstado());

		if(optional.isPresent()) {
			respuesta = new ResponseEntity<Estado>( HttpStatus.OK);
			service.save(estado);
		}else
			respuesta = new ResponseEntity<Estado>(HttpStatus.BAD_REQUEST);

		return respuesta;
	}

	@DeleteMapping(value = "/delete")
	public ResponseEntity<Estado> delete(@RequestParam String id){
		
		ResponseEntity<Estado> respuesta;
		
		if(service.deleteById(id))
			respuesta = new ResponseEntity<Estado>(HttpStatus.OK);
		else 
			respuesta = new ResponseEntity<Estado>(HttpStatus.BAD_REQUEST);
		
		return respuesta;
	}
}

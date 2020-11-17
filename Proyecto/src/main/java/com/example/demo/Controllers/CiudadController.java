package com.example.demo.Controllers;

import java.util.List;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.pojos.Ciudad;
import com.example.demo.model.pojos.Departamento;
import com.example.demo.model.pojos.IdCiudad;
import com.example.demo.model.pojos.Usuario;
import com.example.demo.model.service.ICiudadService;
import com.example.demo.model.service.IDepartamentoService;

@RestController
@RequestMapping("pro/ciudad")
public class CiudadController {

	@Autowired
	private ICiudadService service;
	@Autowired
	private IDepartamentoService elService;

	/*
	 * ResponseEntity<Ciudad> respuesta; HttpStatus status = HttpStatus.CREATED;
	 * 
	 * 
	 * 
	 * respuesta = new ResponseEntity<Ciudad>(status); return respuesta;
	 */

	@PostMapping("/add")
	public ResponseEntity<Ciudad> annadir(@RequestBody Ciudad c/*, 
			@AuthenticationPrincipal Usuario user*/) {
		ResponseEntity<Ciudad> respuesta;
		HttpStatus status = HttpStatus.CREATED;

		if (!service.findById(c.getId()).isPresent())
			service.save(c);
		else
			status = HttpStatus.BAD_REQUEST;

		respuesta = new ResponseEntity<Ciudad>(status);
		return respuesta;
	}

	@PutMapping("/update")
	public ResponseEntity<Ciudad> modificar(@RequestBody Ciudad c) {

		ResponseEntity<Ciudad> respuesta;
		HttpStatus status = HttpStatus.ACCEPTED;

		if (service.findById(c.getId()).isPresent())
			service.save(c);
		else
			status = HttpStatus.BAD_REQUEST;

		respuesta = new ResponseEntity<Ciudad>(status);
		return respuesta;
	}

	@GetMapping("/get")
	public ResponseEntity<Ciudad> consultarPorId(@RequestBody IdCiudad id) {

		ResponseEntity<Ciudad> respuesta;
		HttpStatus status = HttpStatus.ACCEPTED;
		Ciudad c;
		if (!service.findById(id).isPresent()) {
			status = HttpStatus.OK;

		} else
			status = HttpStatus.BAD_REQUEST;
		c = service.findById(id).orElse(Ciudad.builder().id(id).build());
		
		respuesta = new ResponseEntity<Ciudad>(c,status);
		return respuesta;
	}

	@GetMapping("/get/all")
	public List<Ciudad> pedirTodos(/*@AuthenticationPrincipal Usuario user*/) {

		List<Ciudad> ciudades = service.findAll();

		return ciudades;
	}

	@GetMapping("/get/allDept")
	public List<Departamento> pedirCominidades() {

		List<Departamento> comunidades = elService.findAll();

		return comunidades;
	}

	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteCustomerbyId(@RequestBody IdCiudad id) {

		ResponseEntity<String> response;

		if (service.deleteById(id))
			response = new ResponseEntity<>(HttpStatus.OK);
		else
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);

		return response;
	}
}

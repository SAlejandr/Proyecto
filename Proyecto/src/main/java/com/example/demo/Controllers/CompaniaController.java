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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.pojos.Compania;
import com.example.demo.model.service.ICompaniaService;

@RestController
@RequestMapping("pro/compania")
public class CompaniaController {

	@Autowired private ICompaniaService service;

	@PostMapping("/add")
	public ResponseEntity<Compania> add(@RequestBody Compania c) {

		ResponseEntity<Compania> respuesta;
		HttpStatus status;

		if(!service.findById(c.getNit()).isPresent()) {
			
			status = HttpStatus.ACCEPTED;
			service.save(c);
		}else {
			
			status =HttpStatus.BAD_REQUEST;
		}
		
		respuesta = new ResponseEntity<Compania>(status);

		return respuesta;
	}

	@PutMapping("/update")
	public ResponseEntity<Compania> update(@RequestBody Compania c) {

		ResponseEntity<Compania> respuesta;
		HttpStatus status;

		if(service.findById(c.getNit()).isPresent()) {
			
			status = HttpStatus.ACCEPTED;
		}else {
			
			status =HttpStatus.BAD_REQUEST;
		}
		
		respuesta = new ResponseEntity<Compania>(status);

		return respuesta;
	}

	@GetMapping("/get")
	public ResponseEntity<Compania> getById(@RequestParam String id) {

		ResponseEntity<Compania> respuesta;
		
		Optional<Compania> com = service.findById(id);
		
		Compania co = com.orElse(Compania.builder().nit(id).build());
		
		respuesta =new ResponseEntity<Compania>(co, HttpStatus.ACCEPTED);
		
		return respuesta;
	}
	
	@GetMapping("/get/all")
	public List<Compania> getAll() {


		return service.findAll();
	}
	
	@DeleteMapping("delete")
	public ResponseEntity<Compania> deleteById(@RequestParam String id) {

		ResponseEntity<Compania> respuesta;

		if(service.deleteById(id))
			respuesta = new ResponseEntity<Compania>(HttpStatus.ACCEPTED);
		else
			respuesta = new ResponseEntity<Compania>(HttpStatus.BAD_REQUEST);
		

		return respuesta;
	}


}

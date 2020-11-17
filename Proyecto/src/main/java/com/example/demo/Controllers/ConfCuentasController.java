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

import com.example.demo.model.pojos.ConfiguracionCuentas;
import com.example.demo.model.service.ICompaniaService;
import com.example.demo.model.service.IConfiCuentasService;

@RestController
@RequestMapping("pro/confCuenta")
public class ConfCuentasController {
	
	@Autowired private IConfiCuentasService service;

	@PostMapping("/add")
	public ResponseEntity<ConfiguracionCuentas> add(@RequestBody ConfiguracionCuentas c) {

		ResponseEntity<ConfiguracionCuentas> respuesta;
		HttpStatus status;

		if(!service.findById(c.getCodConfig()).isPresent()) {
			
			status = HttpStatus.ACCEPTED;
		}else {
			
			status =HttpStatus.BAD_REQUEST;
		}
		
		respuesta = new ResponseEntity<ConfiguracionCuentas>(status);

		return respuesta;
	}

	@PutMapping("/update")
	public ResponseEntity<ConfiguracionCuentas> update(@RequestBody ConfiguracionCuentas c) {

		ResponseEntity<ConfiguracionCuentas> respuesta;
		HttpStatus status;

		if(service.findById(c.getCodConfig()).isPresent()) {
			
			status = HttpStatus.ACCEPTED;
		}else {
			
			status =HttpStatus.BAD_REQUEST;
		}
		
		respuesta = new ResponseEntity<ConfiguracionCuentas>(status);

		return respuesta;
	}

	@GetMapping("/get")
	public ResponseEntity<ConfiguracionCuentas> getById(@RequestParam String id) {

		ResponseEntity<ConfiguracionCuentas> respuesta; 
		
		ConfiguracionCuentas conf = service.findById(id).orElse(ConfiguracionCuentas.builder().codConfig(id).build());
		
		
		respuesta = new ResponseEntity<ConfiguracionCuentas>(conf ,HttpStatus.ACCEPTED);

		return respuesta;
	}
	
	@GetMapping("/get/all")
	public List<ConfiguracionCuentas> getAll() {

		return service.findAll();
	}
	
	@DeleteMapping("delete")
	public ResponseEntity<ConfiguracionCuentas> deleteById(String id) {

		ResponseEntity<ConfiguracionCuentas> respuesta;


		if(service.deleteById(id))
			respuesta = new ResponseEntity<ConfiguracionCuentas>(HttpStatus.OK);
		else
			respuesta = new ResponseEntity<ConfiguracionCuentas>(HttpStatus.BAD_REQUEST);

		return respuesta;
	}

}

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

import com.example.demo.model.pojos.ConfiguracionDePlanDeCuentas;
import com.example.demo.model.service.IConfPlanDeCuentasService;

@RestController
@RequestMapping("pro/confPlanDeCuentas")
public class ConfPlanDeCuentasController {

	@Autowired private IConfPlanDeCuentasService service;


	@PostMapping("/add")
	public ResponseEntity<ConfiguracionDePlanDeCuentas> add(@RequestBody ConfiguracionDePlanDeCuentas conf){

		ResponseEntity<ConfiguracionDePlanDeCuentas> respuesta;

		if(!service.findById(conf.getNivel()).isPresent()) {

			service.save(conf);
			respuesta = new ResponseEntity<ConfiguracionDePlanDeCuentas>(HttpStatus.ACCEPTED);
		}else {

			respuesta = new ResponseEntity<ConfiguracionDePlanDeCuentas>(HttpStatus.BAD_REQUEST);
		}


		return respuesta;
	}


	@PutMapping("/update")
	public ResponseEntity<ConfiguracionDePlanDeCuentas> update(@RequestBody ConfiguracionDePlanDeCuentas conf) {
		//TODO: process PUT request
		ResponseEntity<ConfiguracionDePlanDeCuentas> respuesta;

		if(service.findById(conf.getNivel()).isPresent()) {

			service.save(conf);
			respuesta = new ResponseEntity<ConfiguracionDePlanDeCuentas>(HttpStatus.ACCEPTED);
		}else {

			respuesta = new ResponseEntity<ConfiguracionDePlanDeCuentas>(HttpStatus.BAD_REQUEST);
		}


		return respuesta;
	}

	@GetMapping("/getAll")
	public List<ConfiguracionDePlanDeCuentas> getAll() {
		
		return service.findAll();
	}

	@GetMapping("/get")
	public ResponseEntity<ConfiguracionDePlanDeCuentas> get(@RequestParam byte nivel) {
		
		Optional<ConfiguracionDePlanDeCuentas> op = service.findById(nivel);
		
		ConfiguracionDePlanDeCuentas c = op.orElse(ConfiguracionDePlanDeCuentas.builder().nivel(nivel).build());
		
		return new ResponseEntity<ConfiguracionDePlanDeCuentas>(c, HttpStatus.OK);
	}

	
	@DeleteMapping("/delete")
	public ResponseEntity<ConfiguracionDePlanDeCuentas> delete(@PathVariable byte nivel ) {
		//TODO: process DELETE request
		
		HttpStatus status = service.deleteById(nivel)?HttpStatus.ACCEPTED:HttpStatus.BAD_REQUEST;
		
		
		return new ResponseEntity<ConfiguracionDePlanDeCuentas>(status);
	}

	
}

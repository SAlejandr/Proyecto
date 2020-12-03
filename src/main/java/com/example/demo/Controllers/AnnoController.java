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

import com.example.demo.model.pojos.Anno;
import com.example.demo.model.service.IAnnoService;

@RestController
@RequestMapping("pro/anno")
public class AnnoController {


	@Autowired private IAnnoService service;
	/*
	 * ResponseEntity<Anno> respuesta; 
	 * HttpStatus status = HttpStatus.CREATED;
	 * 
	 * 
	 * 
	 * respuesta = new ResponseEntity<Anno>(status); 
	 * 
	 * return respuesta;
	 */
	@PostMapping("/add")
	public ResponseEntity<Anno> annadirAnno(@RequestBody Anno a){

		ResponseEntity<Anno> respuesta; 

		HttpStatus status;
		if(!service.findById(a.getElAnno()).isPresent()) {
			status = HttpStatus.CREATED;

			service.save(a);

		}else {
			status = HttpStatus.BAD_REQUEST;
		}

		respuesta = new ResponseEntity<Anno>(status); 

		return respuesta;
	}

	@PutMapping("/update")
	public ResponseEntity<Anno> actualizarAnno(@RequestBody Anno a){
		ResponseEntity<Anno> respuesta; 

		HttpStatus status;
		if(service.findById(a.getElAnno())!=null) {
			status = HttpStatus.ACCEPTED;

			service.save(a);

		}else {
			status = HttpStatus.BAD_REQUEST;
		}

		respuesta = new ResponseEntity<Anno>(status); 

		return respuesta;

	}

	@GetMapping("/get")
	public ResponseEntity<Anno> getAnno(@RequestParam int id){

		ResponseEntity<Anno> respuesta;
		HttpStatus status = HttpStatus.OK;
		
		Anno a;
		
		
		status = HttpStatus.OK;
		
		a = service.findById(id).orElse(Anno.builder().elAnno(id).build());
		
		respuesta = new ResponseEntity<Anno>(a,status);
		
		return respuesta;
	}
	

	
	@GetMapping(value = "/inactivateAll")						//*******************************************
	public String inactivarTodos(){
		String respuesta = service.inactivarTodos();
		return respuesta;
	}
	
	
	@GetMapping("/get/all")
	public List<Anno> getAll(){
		
		return service.findAll();
		
	}
	
	@GetMapping("/get/activo")
	public Anno getActivo() {
		
		return service.findByCerrado(false).stream().findFirst().orElse(new Anno());
	}
	
	
	@DeleteMapping("/delete")
	public ResponseEntity<Anno> deleteById(@RequestParam int id){
		
		ResponseEntity<Anno> respuesta;
		
		HttpStatus status;
		
		if(service.findById(id).isPresent()) {
			
			status = HttpStatus.OK;
			service.deleteById(id);
		}else {
			
			status = HttpStatus.BAD_REQUEST;
		}
		
		respuesta = new ResponseEntity<Anno>(status);
		
		return respuesta;
	}

}

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

import com.example.demo.model.pojos.CentroDeCosto;
import com.example.demo.model.pojos.Ciudad;
import com.example.demo.model.service.ICentroDeCostosService;

@RestController
@RequestMapping("pro/centroDeCosto")
public class CentroDeCostosController {


	@Autowired private ICentroDeCostosService service;
	/*
	 * ResponseEntity<CentroDeCosto> respuesta; 
	 * HttpStatus status = HttpStatus.CREATED;
	 * 
	 * 
	 * 
	 * respuesta = new ResponseEntity<CentroDeCosto>(status); 
	 * 
	 * return respuesta;
	 */
	@PostMapping("/add")
	public ResponseEntity<CentroDeCosto> annadirCentroDeCosto(@RequestBody CentroDeCosto a){

		ResponseEntity<CentroDeCosto> respuesta; 

		HttpStatus status;
		if(!service.findById(a.getCodCentro()).isPresent()) {
			status = HttpStatus.CREATED;

			service.save(a);

		}else {
			status = HttpStatus.BAD_REQUEST;
		}

		respuesta = new ResponseEntity<CentroDeCosto>(status); 

		return respuesta;
	}

	@PutMapping("/update")
	public ResponseEntity<CentroDeCosto> actualizarCentroDeCosto(@RequestBody CentroDeCosto a){
		ResponseEntity<CentroDeCosto> respuesta; 

		HttpStatus status;
		if(service.findById(a.getCodCentro()).isPresent()) {
			status = HttpStatus.ACCEPTED;

			service.save(a);

		}else {
			status = HttpStatus.BAD_REQUEST;
		}

		respuesta = new ResponseEntity<CentroDeCosto>(status); 

		return respuesta;

	}

	@GetMapping("/get")
	public ResponseEntity<CentroDeCosto> getCentroDeCosto(@RequestParam int id){

		ResponseEntity<CentroDeCosto> respuesta;
		HttpStatus status = HttpStatus.OK;
		
		CentroDeCosto a;
		
		
		status = HttpStatus.OK;
		
		a = service.findById(id).orElse(CentroDeCosto.builder().codCentro(id).build());
		
		respuesta = new ResponseEntity<CentroDeCosto>(a,status);
		
		return respuesta;
	}
	
	@GetMapping("/get/all")
	public List<CentroDeCosto> getAll(){
		
		return service.findAll();
		
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<CentroDeCosto> deleteById(@RequestParam int id){
		
		ResponseEntity<CentroDeCosto> respuesta;
		
		HttpStatus status;
		
		if(service.findById(id).isPresent()) {
			
			status = HttpStatus.OK;
			service.deleteById(id);
		}else {
			
			status = HttpStatus.BAD_REQUEST;
		}
		
		respuesta = new ResponseEntity<CentroDeCosto>(status);
		
		return respuesta;
	}

}

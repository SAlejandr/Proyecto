package com.example.demo.Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

import com.example.demo.model.pojos.LogAcceso;
import com.example.demo.model.pojos.Mes;
import com.example.demo.model.pojos.Movimiento;
import com.example.demo.model.pojos.Ciudad;
import com.example.demo.model.service.ILogService;

@RestController
@RequestMapping("pro/logAcceso")
public class LogAccesoController {


	@Autowired private ILogService service;
	/*
	 * ResponseEntity<LogAcceso> respuesta; 
	 * HttpStatus status = HttpStatus.CREATED;
	 * 
	 * 
	 * 
	 * respuesta = new ResponseEntity<LogAcceso>(status); 
	 * 
	 * return respuesta;
	 */
	@PostMapping("/add")
	public ResponseEntity<LogAcceso> annadirLogAcceso(@RequestBody LogAcceso a){

		ResponseEntity<LogAcceso> respuesta; 

		HttpStatus status;
		if(!service.findById(a.getConsecutivo()).isPresent()) {
			status = HttpStatus.CREATED;

			service.save(a);

		}else {
			status = HttpStatus.BAD_REQUEST;
		}

		respuesta = new ResponseEntity<LogAcceso>(status); 

		return respuesta;
	}

	@PutMapping("/update")
	public ResponseEntity<LogAcceso> actualizarLogAcceso(@RequestBody LogAcceso a){
		ResponseEntity<LogAcceso> respuesta; 

		HttpStatus status;
		if(service.findById(a.getConsecutivo()).isPresent()) {
			status = HttpStatus.ACCEPTED;

			service.save(a);

		}else {
			status = HttpStatus.BAD_REQUEST;
		}

		respuesta = new ResponseEntity<LogAcceso>(status); 

		return respuesta;

	}

	@GetMapping("/get")
	public ResponseEntity<LogAcceso> getLogAcceso(@RequestParam long id){

		ResponseEntity<LogAcceso> respuesta;
		HttpStatus status = HttpStatus.OK;
		
		LogAcceso a;
		
		
		status = HttpStatus.OK;
		
		a = (LogAcceso) service.findById(id).orElse(LogAcceso.builder().consecutivo(id).build());
		
		respuesta = new ResponseEntity<LogAcceso>(a,status);
		
		return respuesta;
	}
	

	
	@GetMapping("/get/all")
	public List<LogAcceso> getAll(){
		
		ArrayList<LogAcceso> lista = new ArrayList<LogAcceso>();
		service.findAll().stream().filter(l -> l instanceof LogAcceso).forEach(l -> lista.add((LogAcceso) l));
		return (List<LogAcceso>)lista;
		
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<LogAcceso> deleteById(@RequestParam int id){
		
		ResponseEntity<LogAcceso> respuesta;
		
		HttpStatus status;
		
		if(service.findById(id).isPresent()) {
			
			status = HttpStatus.OK;
			service.deleteById(id);
		}else {
			
			status = HttpStatus.BAD_REQUEST;
		}
		
		respuesta = new ResponseEntity<LogAcceso>(status);
		
		return respuesta;
	}

	

}

package com.example.demo.Controllers;

import java.util.ArrayList;
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

import com.example.demo.model.pojos.LogSuceso;
import com.example.demo.model.service.ILogService;

@RestController
@RequestMapping("pro/logSuceso")
public class LogSucesoController {


	@Autowired private ILogService service;
	/*
	 * ResponseEntity<LogSuceso> respuesta; 
	 * HttpStatus status = HttpStatus.CREATED;
	 * 
	 * 
	 * 
	 * respuesta = new ResponseEntity<LogSuceso>(status); 
	 * 
	 * return respuesta;
	 */
	@PostMapping("/add")
	public ResponseEntity<LogSuceso> annadirLogSuceso(@RequestBody LogSuceso a){

		ResponseEntity<LogSuceso> respuesta; 

		HttpStatus status;
		if(!service.findById(a.getConsecutivo()).isPresent()) {
			status = HttpStatus.CREATED;

			service.save(a);

		}else {
			status = HttpStatus.BAD_REQUEST;
		}

		respuesta = new ResponseEntity<LogSuceso>(status); 

		return respuesta;
	}

	@PutMapping("/update")
	public ResponseEntity<LogSuceso> actualizarLogSuceso(@RequestBody LogSuceso a){
		ResponseEntity<LogSuceso> respuesta; 

		HttpStatus status;
		if(service.findById(a.getConsecutivo()).isPresent()) {
			status = HttpStatus.ACCEPTED;

			service.save(a);

		}else {
			status = HttpStatus.BAD_REQUEST;
		}

		respuesta = new ResponseEntity<LogSuceso>(status); 

		return respuesta;

	}

	@GetMapping("/get")
	public ResponseEntity<LogSuceso> getLogSuceso(@RequestParam long id){

		ResponseEntity<LogSuceso> respuesta;
		HttpStatus status = HttpStatus.OK;
		
		LogSuceso a;
		
		
		status = HttpStatus.OK;
		
		a = (LogSuceso) service.findById(id).orElse(LogSuceso.builder().consecutivo(id).build());
		
		respuesta = new ResponseEntity<LogSuceso>(a,status);
		
		return respuesta;
	}
	

	
	@GetMapping("/get/all")
	public List<LogSuceso> getAll(){
		
		ArrayList<LogSuceso> lista = new ArrayList<LogSuceso>();
		service.findAll().stream().filter(l -> l instanceof LogSuceso).forEach(l -> lista.add((LogSuceso) l));
		return (List<LogSuceso>)lista;
		
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<LogSuceso> deleteById(@RequestParam int id){
		
		ResponseEntity<LogSuceso> respuesta;
		
		HttpStatus status;
		
		if(service.findById(id).isPresent()) {
			
			status = HttpStatus.OK;
			service.deleteById(id);
		}else {
			
			status = HttpStatus.BAD_REQUEST;
		}
		
		respuesta = new ResponseEntity<LogSuceso>(status);
		
		return respuesta;
	}

	

}

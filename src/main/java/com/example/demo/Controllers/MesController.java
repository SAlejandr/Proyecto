package com.example.demo.Controllers;

import java.util.ArrayList;
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

import com.example.demo.model.pojos.Anno;
import com.example.demo.model.pojos.Mes;
import com.example.demo.model.pojos.MesFiscalId;
import com.example.demo.model.service.IAnnoService;
import com.example.demo.model.service.IMesService;

@RestController
@RequestMapping("pro/mes")
public class MesController {

	@Autowired private IMesService service;
	@Autowired private IAnnoService annoService;


	@PostMapping(value = "/add")
	public ResponseEntity<Mes> addMes(@RequestBody Mes mes) {
		//TODO: process POST request

		ResponseEntity<Mes> respuesta;

		Optional<Mes> optional = service.findById(mes.getIdFiscal());

		if(optional.isPresent()) {
			respuesta = new ResponseEntity<Mes>(HttpStatus.BAD_REQUEST);
		}else {
			service.save(mes);
			respuesta = new ResponseEntity<Mes>(HttpStatus.CREATED);
		}

		return respuesta;
	}

	@PutMapping(value = "/update")
	public ResponseEntity<Mes> updateMes(@RequestBody Mes mes) {
		//TODO: process PUT request

		ResponseEntity<Mes> respuesta;

		Optional<Mes> optional = service.findById(mes.getIdFiscal());

		if(!optional.isPresent()) {
			respuesta = new ResponseEntity<Mes>(HttpStatus.BAD_REQUEST);
		}else {
			service.save(mes);
			respuesta = new ResponseEntity<Mes>(HttpStatus.CREATED);
		}

		return respuesta;
	}
	
	@GetMapping(value = "/inactivateAll")						//*******************************************
	public String inactivarTodos(){
		String respuesta = service.inactivarTodos();
		return respuesta;
	}

	@PostMapping(value = "/get")													//*******************************************
	public ResponseEntity<Mes> getMes(@RequestBody MesFiscalId id) {
		
		ResponseEntity<Mes> respuesta;
		
		Optional<Mes> optional = service.findById(id);
		
		if(optional.isPresent())
			respuesta = new ResponseEntity<Mes>(optional.get(), HttpStatus.OK);
		else
			respuesta = new ResponseEntity<Mes>(HttpStatus.NOT_FOUND);
		
		return (ResponseEntity<Mes>)respuesta;
		
	}

	@GetMapping(value = "/getAll")
	public List<Mes> getAllMeses() {
		return service.findAll();
	}
	
	@GetMapping(value = "/getAllDeAnnoActivo")
	public List<Mes> getAllMesDeAnnoActivos(){
		
		Optional<Anno> anno = annoService.findByCerrado(false).stream().findFirst();
		
		return service.buscarMesesEnRango(anno.get().getInicioAnno(), anno.get().getFinAnno());
	}

	@DeleteMapping(value = "/delete")
	public ResponseEntity<Mes> deleteMes(@RequestBody MesFiscalId id) {
		//TODO: process DELETE request
		
		ResponseEntity<Mes> respuesta;
		
		if(service.deleteById(id))
			respuesta = new ResponseEntity<Mes>(HttpStatus.OK);
		else
			respuesta = new ResponseEntity<Mes>(HttpStatus.BAD_REQUEST);
		
		return respuesta;
		
	}

	
}

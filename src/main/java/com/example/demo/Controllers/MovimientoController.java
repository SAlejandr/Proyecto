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

import com.example.demo.model.pojos.Documento;
import com.example.demo.model.pojos.IdDocumento;
import com.example.demo.model.pojos.IdMovimiento;
import com.example.demo.model.pojos.Mes;
import com.example.demo.model.pojos.Movimiento;
import com.example.demo.model.pojos.TipoDocumento;
import com.example.demo.model.service.IDocumentoService;
import com.example.demo.model.service.IMovimientoService;
import com.example.demo.model.service.ITipoDocumentoService;
import com.example.demo.model.service.TipoDocumentoService;


@RestController
@RequestMapping("pro/movimiento")
public class MovimientoController {

	@Autowired private IMovimientoService service;
	@Autowired private IDocumentoService documentoService;
	@Autowired private ITipoDocumentoService tipoDocumentoService;

	@PostMapping(value = "/add")
	public ResponseEntity<Movimiento> addMovimiento(@RequestBody Movimiento movimiento) {
		//TODO: process POST request
		
		ResponseEntity<Movimiento> respuesta;
		HttpStatus status;
		Optional<Movimiento> optional = service.findById(movimiento.getId());
		
		if(optional.isPresent())
			status = HttpStatus.BAD_REQUEST;
		else {
			status = HttpStatus.CREATED;
			service.save(movimiento);
			
			
		}
		
		respuesta = new ResponseEntity<Movimiento>(status);
		return respuesta;
		
	}

	@PutMapping(value = "/update")
	public ResponseEntity<Movimiento> update(@RequestBody Movimiento movimiento) {
		//TODO: process PUT request
		
		ResponseEntity<Movimiento> respuesta;
		HttpStatus status;
		Optional<Movimiento> optional = service.findById(movimiento.getId());
		
		if(!optional.isPresent()) 
			status = HttpStatus.BAD_REQUEST;
		else {
			status = HttpStatus.ACCEPTED;
			service.save(movimiento);
		}
		
		respuesta = new ResponseEntity<Movimiento>(status);
		return respuesta;
	}
	
	@GetMapping(value = "/getByMes")
	public List<Movimiento> getByMes(@RequestBody Mes mes) {
		return service.buscarPorMes(mes);
	}
	
	@GetMapping(value = "/getByDocumento")
	public List<Movimiento> getByDocumento(@RequestParam(name = "n") String numDoc, @RequestParam(name = "tDoc") String tipoDoc){
		
		IdDocumento id = IdDocumento.builder().
				numDocumento(Long.parseLong(numDoc)).
				tipoDocumento(TipoDocumento.builder().
						tipoDoc(tipoDoc).
						build()).
				build();
		
		Optional<Documento> documento = documentoService.findById(id);
		
		System.err.println(documento.get());
		
		ArrayList<Movimiento> movimientos = new ArrayList<Movimiento>();
		
		movimientos.addAll(service.buscarPorDocumento(documento.get()));
		return movimientos;
	}
	
	@GetMapping(value = "/get")
	public ResponseEntity<Movimiento> getMovimiento(@RequestBody IdMovimiento id) {
		
		ResponseEntity<Movimiento> respuesta;
		HttpStatus status;
		Optional<Movimiento> optional = service.findById(id);
		
		if(optional.isPresent()) {
			status = HttpStatus.OK;
			respuesta = new ResponseEntity<Movimiento>(optional.get(), status);
		}else {
			status = HttpStatus.NOT_FOUND;
			respuesta = new ResponseEntity<Movimiento>(status);
		}
		
		return respuesta;
	}

	@DeleteMapping(value = "/delete")
	public ResponseEntity<Movimiento> deleteMovimiento(@RequestBody IdMovimiento id ) {
		//TODO: process DELETE request
		
		ResponseEntity<Movimiento> respuesta;
		
		if(service.deleteById(id))
			respuesta = new ResponseEntity<Movimiento>(HttpStatus.OK);
		else 
			respuesta = new ResponseEntity<Movimiento>(HttpStatus.BAD_REQUEST);
		
		return respuesta;
			
	}


}

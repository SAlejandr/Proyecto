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

import com.example.demo.model.pojos.Documento;
import com.example.demo.model.pojos.IdDocumento;
import com.example.demo.model.pojos.Mes;
import com.example.demo.model.pojos.Movimiento;
import com.example.demo.model.service.IDocumentoService;
import com.example.demo.model.service.IMovimientoService;

@RestController
@RequestMapping("pro/documento")
public class DocumentoController {

	@Autowired private IDocumentoService service;
	@Autowired private IMovimientoService subService;


	@GetMapping("/get")
	private ResponseEntity<Documento> getDocumento(@RequestBody IdDocumento id ){

		ResponseEntity<Documento> respuesta = new ResponseEntity<Documento>(HttpStatus.BAD_REQUEST);
		Optional<Documento> optional = service.findById(id);
		if(optional.isPresent())
			respuesta = new ResponseEntity<Documento>(optional.get(), HttpStatus.OK);

		return respuesta;

	}

	@GetMapping(value = "/getAll")
	public List<Documento> getAllDocumentos() {
		return service.findAll();
	}

	@PutMapping(value = "/getMovimientos")
	public List<Movimiento> getMethodName(@RequestBody(required = true) Documento documento) {
		return subService.buscarPorDocumento(documento);
	}


	@PostMapping(value = "/add")
	public ResponseEntity<Documento> addDocumento(@RequestBody Documento documento) {
		//TODO: process POST request
		ResponseEntity<Documento> respuesta = new ResponseEntity<Documento>(HttpStatus.BAD_REQUEST);

		IdDocumento id = documento.getIdDocumento();
		Optional<Documento> optional = service.findById(id);

		if(!optional.isPresent()) {
			respuesta = new ResponseEntity<Documento>(HttpStatus.OK);
			service.save(documento);
		}

		return respuesta;
	}

	@PutMapping(value = "/update")
	public ResponseEntity<Documento> updateDocuemto(@RequestBody Documento documento) {
		//TODO: process PUT request
		ResponseEntity<Documento> respuesta = new ResponseEntity<Documento>(HttpStatus.BAD_REQUEST);

		System.out.println("Haciendo actualizacion");
		IdDocumento id = documento.getIdDocumento();
		Optional<Documento> optional = service.findById(id);

		if(optional.isPresent()){
			respuesta = new ResponseEntity<Documento>(HttpStatus.OK);
			service.save(documento);
		}

		return respuesta;
	}

	@DeleteMapping(value = "/delete")
	public ResponseEntity<Documento> deleteDocumento(@RequestBody Documento documento ) {
		//TODO: process DELETE request
		ResponseEntity<Documento> respuesta;

		IdDocumento id = documento.getIdDocumento();

		if(service.deleteById(id))
			respuesta = new ResponseEntity<Documento>(HttpStatus.OK);
		else
			respuesta = new ResponseEntity<Documento>(HttpStatus.NOT_FOUND);

		return respuesta;
	}


}

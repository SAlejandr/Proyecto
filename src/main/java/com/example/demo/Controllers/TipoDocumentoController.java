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

import com.example.demo.model.pojos.TipoDocumento;
import com.example.demo.model.service.ITipoDocumentoService;
import com.example.demo.model.service.TipoDocumentoService;

@RestController
@RequestMapping("pro/tipoDocumento")
public class TipoDocumentoController {

	@Autowired private ITipoDocumentoService service; 

	@PostMapping(value = "/add")
	public ResponseEntity<TipoDocumento> addTipoDocumento(@RequestBody TipoDocumento tipo) {
		//TODO: process POST request

		ResponseEntity<TipoDocumento> respuesta;
		Optional<TipoDocumento> optional = service.findById(tipo.getTipoDoc());

		if(optional.isPresent())
			respuesta = new ResponseEntity<TipoDocumento>(HttpStatus.BAD_REQUEST);
		else {
			service.save(tipo);
			respuesta = new ResponseEntity<TipoDocumento>(HttpStatus.ACCEPTED);
		}

		return respuesta;
	}

	@PutMapping(value = "/update")
	public ResponseEntity<TipoDocumento> updateTipoDocumento(@RequestBody TipoDocumento tipo) {
		//TODO: process PUT request

		ResponseEntity<TipoDocumento> respuesta;
		Optional<TipoDocumento> optional = service.findById(tipo.getTipoDoc());

		if(optional.isPresent())
			respuesta = new ResponseEntity<TipoDocumento>(HttpStatus.BAD_REQUEST);
		else {
			service.save(tipo);
			respuesta = new ResponseEntity<TipoDocumento>(HttpStatus.ACCEPTED);
		}

		return respuesta;

	}

	@DeleteMapping(value = "/delete")
	public ResponseEntity<TipoDocumento> deleteTipo(@RequestParam int id ) {
		//TODO: process DELETE request

		ResponseEntity<TipoDocumento> respuesta;
		
		if(service.deleteById(id))
			respuesta = new ResponseEntity<TipoDocumento>(HttpStatus.ACCEPTED);
		else
			respuesta = new ResponseEntity<TipoDocumento>(HttpStatus.BAD_REQUEST);
		
		return respuesta;
		
	}

	@GetMapping(value = "/get")
	public ResponseEntity<TipoDocumento> getTipo(@RequestParam int id) {
		
		ResponseEntity<TipoDocumento> respuesta;
		Optional<TipoDocumento> optional = service.findById(id);
		
		if(optional.isPresent())
			respuesta = new ResponseEntity<TipoDocumento>(optional.get(), HttpStatus.OK);
		else
			respuesta = new ResponseEntity<TipoDocumento>(HttpStatus.BAD_REQUEST);
		
		return respuesta;
	}

	@GetMapping(value = "/getAll")
	public List<TipoDocumento> getAll() {
		return service.findAll();
	}

}

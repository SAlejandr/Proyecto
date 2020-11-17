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

import com.example.demo.model.pojos.Usuario;
import com.example.demo.model.service.IUsuarioService;

@RestController
@RequestMapping("pro/usuario")
public class UsuarioController {

	@Autowired private IUsuarioService service;
	
	@PostMapping(value = "/add")
	public ResponseEntity<Usuario> addUsuario(@RequestBody Usuario usuario) {
		//TODO: process POST request
		
		ResponseEntity<Usuario> respuesta;
	
		Optional<Usuario> optional = service.findById(usuario.getId());
		
		if(optional.isPresent())
			respuesta = new ResponseEntity<Usuario>(HttpStatus.BAD_REQUEST);
		else {
			service.save(usuario);
			respuesta = new ResponseEntity<Usuario>(HttpStatus.ACCEPTED);
		}

		return respuesta;
	}

	@PutMapping(value = "/update")
	public ResponseEntity<Usuario> updateUsuario(@RequestBody Usuario usuario) {
		//TODO: process PUT request
		
		ResponseEntity<Usuario> respuesta;
		
		Optional<Usuario> optional = service.findById(usuario.getId());
		
		if(!optional.isPresent())
			respuesta = new ResponseEntity<Usuario>(HttpStatus.BAD_REQUEST);
		else {
			service.save(usuario);
			respuesta = new ResponseEntity<Usuario>(HttpStatus.ACCEPTED);
		}

		return respuesta;
	}

	@DeleteMapping(value = "/delete")
	public ResponseEntity<Usuario> deleteUsuario(@RequestParam int id  ) {
		//TODO: process DELETE request
		
		ResponseEntity<Usuario> respuesta;
		
		if(service.deleteById(id))
			respuesta = new ResponseEntity<Usuario>(HttpStatus.OK);
		else
			respuesta = new ResponseEntity<Usuario>(HttpStatus.BAD_REQUEST);
		
		return respuesta;
	}

	@GetMapping(value = "/get")
	public ResponseEntity<Usuario> getUsuario(@RequestParam String username) {
		
		ResponseEntity<Usuario> respuesta;
		
		Optional<Usuario> optional = service.findByUsername(username);
		
		if(optional.isPresent()) 
			respuesta = new ResponseEntity<Usuario>(optional.get(), HttpStatus.OK);
		else
			respuesta = new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
		
		return respuesta;
	}
	
	@GetMapping(value = "/getAll")
	public List<Usuario> getAll() {
		return service.findAll();
	}


	
}

package com.example.demo.Controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.dto.GetUserDTO;
import com.example.demo.model.dto.LoginUserDTO;
import com.example.demo.model.pojos.Usuario;
import com.example.demo.model.service.IUsuarioService;

@RestController
@RequestMapping("pro")
public class LoginController {

	@Autowired IUsuarioService service;
	@PostMapping("/login")
	public ResponseEntity<GetUserDTO> login(@RequestBody LoginUserDTO user){
		
		String username = user.getUsername();
		
		Optional<Usuario> usuario = service.findByUsername(username);
		ResponseEntity<GetUserDTO> respuesta;
		
		System.err.println(usuario.get().toString());
		
		if(usuario.isPresent())
			respuesta = new ResponseEntity<GetUserDTO>(new GetUserDTO(usuario.get()), HttpStatus.OK);
		else {
			respuesta = new ResponseEntity<GetUserDTO>(HttpStatus.FORBIDDEN);
		}
		return respuesta;
		
	}
}

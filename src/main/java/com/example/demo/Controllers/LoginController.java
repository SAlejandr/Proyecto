package com.example.demo.Controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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
import com.example.demo.model.pojos.Log;
import com.example.demo.model.pojos.LogAcceso;
import com.example.demo.model.pojos.Usuario;
import com.example.demo.model.service.ILogService;
import com.example.demo.model.service.IUsuarioService;

@RestController
@RequestMapping("pro")
public class LoginController {

	@Autowired IUsuarioService service;
	@Autowired ILogService logService;
	@PostMapping("/login")
	public ResponseEntity<GetUserDTO> login(@RequestBody LoginUserDTO user){
		
		String username = user.getUsername();
		
		Optional<Usuario> usuario = Optional.of(service.findByUsername(username));
		ResponseEntity<GetUserDTO> respuesta;
		
		System.err.println(usuario.get().toString());
		
		if(usuario.isPresent()) {
			
			respuesta = new ResponseEntity<GetUserDTO>(new GetUserDTO(usuario.get()), HttpStatus.OK);
			LogAcceso log = LogAcceso.builder().instanteDeAcceso(LocalDateTime.now()).usuario(usuario.get()).build();
		}else {
			respuesta = new ResponseEntity<GetUserDTO>(HttpStatus.FORBIDDEN);
		}
		return respuesta;
		
	}
	
	@PostMapping("/logout")
	public ResponseEntity<GetUserDTO> logout(@RequestBody GetUserDTO user){
		
		String username = user.getUsername();
		
		Optional<Usuario> usuario = Optional.of(service.findByUsername(username));
		ResponseEntity<GetUserDTO> respuesta;
		
		if(usuario.isPresent()) {
			
			List<Log> logs  = logService.findAll().stream().filter(l -> l instanceof LogAcceso).filter(l-> l.getUsuario().equals(usuario.get())).collect(Collectors.toList()); ;
			ArrayList<LogAcceso> logAccesos = new ArrayList<>();
			
			logs.stream().forEach(l -> logAccesos.add((LogAcceso) l));
			Comparator<LogAcceso> comparator = Comparator.comparing(LogAcceso::getInstanteDeAcceso).reversed();
			logAccesos.sort(comparator);
			LogAcceso la = logAccesos.get(0);
			la.setInstanteDeSalida(LocalDateTime.now());
			logService.save(la);
			respuesta = new ResponseEntity<GetUserDTO>(HttpStatus.OK);
			
			
		}else {
			respuesta = new ResponseEntity<GetUserDTO>(HttpStatus.FORBIDDEN);
		}
		return respuesta;
		
	}
}

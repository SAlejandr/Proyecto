package com.example.demo.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.dto.GetUserDTO;
import com.example.demo.model.dto.LoginDTO;
import com.example.demo.model.pojos.Usuario;
import com.example.demo.model.service.ILogService;
import com.example.demo.model.service.IUsuarioService;

@RestController
@RequestMapping("/")
public class HomeController {
	
	@Autowired IUsuarioService service;
	@Autowired ILogService logService;

	/*public void mostrarDatos() {
	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	String currentPrincipalName = authentication.getName();
	System.out.println("Nombre usuario: " + currentPrincipalName);
	UserDetails userDetails = (UserDetails) authentication.getPrincipal();
	System.out.println("User has authorities: " + userDetails.getAuthorities());
	
	}*/
	
	
	
	@PostMapping("login")
	public ResponseEntity<GetUserDTO> getLogin(@RequestBody LoginDTO login) {
		
		System.err.println("HE RECIBIDO : " + login.getUsername() +" - " + login.getPassword());
		ResponseEntity<GetUserDTO> respuesta = new ResponseEntity<GetUserDTO>(HttpStatus.BAD_REQUEST); 
		
		Usuario usuario = service.findByUsername(login.getUsername());
		
		BCryptPasswordEncoder b = new BCryptPasswordEncoder();
		System.out.println(b.matches(login.getPassword(),usuario.getPassword())+ " "+login.getPassword()+ " " +usuario.getPassword());
		System.out.println(b.matches("$2a$10$0cn426yH8FQmRMP2vqbdjOnvmjNkD3gt3I8zJYBH9nBGZfDonxtnS","$2a$10$bMy3UiBy/YGEbhAnbOoqsuU9.t4bmXzJXxdHAr6/1qLabI/1u5uru")+ " "+login.getPassword()+ " " +usuario.getPassword());
		System.out.println(b.matches("123","$2a$10$bMy3UiBy/YGEbhAnbOoqsuU9.t4bmXzJXxdHAr6/1qLabI/1u5uru")+ " "+login.getPassword()+ " " +usuario.getPassword());
		
		if(b.matches(login.getPassword(),usuario.getPassword())) {
			GetUserDTO user = new GetUserDTO(usuario);
			System.out.println(user);
			respuesta = new ResponseEntity<GetUserDTO>(user, HttpStatus.ACCEPTED);
		}
		return respuesta;
	}
	
	
}

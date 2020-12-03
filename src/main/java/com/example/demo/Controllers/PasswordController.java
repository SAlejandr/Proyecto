package com.example.demo.Controllers;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.dto.UpdateUserPassDTO;
import com.example.demo.model.pojos.Usuario;
import com.example.demo.model.service.ISaldoCuentaService;
import com.example.demo.model.service.IUsuarioService;
import com.example.demo.util.EncriptarPassword;

@RestController
@RequestMapping("pro/administracion/cambiarPassword")
public class PasswordController {
	@Autowired private IUsuarioService usuarioService;
	
	@PutMapping("/cambio")
	public ResponseEntity<UpdateUserPassDTO> validarPassword(@RequestBody UpdateUserPassDTO upUser) {

		System.out.println("passwords.password1 : "+ upUser.getPasswordNew());
		System.out.println("passwords.password2 : "+ upUser.getPasswordNewR());
		
		ResponseEntity<UpdateUserPassDTO> respuesta = new ResponseEntity<UpdateUserPassDTO>(HttpStatus.BAD_REQUEST);
		
		Usuario user = usuarioService.findByUsername(upUser.getUsername());
		System.err.println(user.toString());
		
		if(user != null) {
			if(upUser.getPasswordNew().equals(upUser.getPasswordNewR())) {
				user.setPassword(EncriptarPassword.encriptarPassword(upUser.getPasswordNew()));
				System.err.println(user.toString());
				usuarioService.save(user);
				respuesta = new ResponseEntity<UpdateUserPassDTO>(HttpStatus.ACCEPTED);
			}
		}
		
		return respuesta;

	}

}

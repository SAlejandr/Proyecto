package com.example.demo.model.dto;

import java.io.Serializable;
import java.util.List;

import com.example.demo.model.pojos.Rol;
import com.example.demo.model.pojos.Usuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetUserDTO implements Serializable{
	
	private int id;
	private String username;
	private String cargo;
	private String nombre;
	private List<Rol> roles;

	public GetUserDTO(Usuario user) {
		
		this(user.getId(),user.getUsername(), user.getCargo(), user.getNombre(), user.getRoles());
	}
}

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
public class LoginUserDTO implements Serializable{

	private String username, pass;
	
	
}

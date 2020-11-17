package com.example.demo.model.pojos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder


@Entity
@EntityListeners(AuditingEntityListener.class)
public class Usuario implements Serializable{
	
	@Id 
	@GeneratedValue
	private int id;
		
	private String nombre;
	
	private String cargo;
	
	@Column(unique = true)
	private String username;

	private String password;
	
	@Enumerated(EnumType.STRING)
	private EstadosDeUsuarios estado;
	
	private LocalDate fechaCreacion;
	

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name="id_usuario")
	private List<Rol> roles;
	

}

package com.example.demo.model.pojos;

import java.io.Serializable;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@Entity
public class Tercero implements Serializable {

	@Id
	@Include
	private String id;

	@Enumerated(EnumType.STRING)
	private Tipo_Identificacion identificacion;
	
	private String nombre;

	@Enumerated(EnumType.STRING)
	private NaturalezaJuridica naturaleza;

	private String direccion;

	private String telefono1;
	private String telefono2;
	private String email;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns({@JoinColumn(name = "ciudad", referencedColumnName = "codCiudad"),
		@JoinColumn(name = "departamento", referencedColumnName = "departamento")})
	
	private Ciudad ciudad;
	

}

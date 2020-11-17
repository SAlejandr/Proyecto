package com.example.demo.model.pojos;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.NoArgsConstructor;

@Data
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class ConfiguracionCuentas implements Serializable {

	@Id
	@Include
	private String codConfig;

	private String nombreConfig;

	@Enumerated(EnumType.STRING)
	private TipoDeCuentas tipoCuenta;

	@Enumerated(EnumType.STRING)
	private NaturalezaCuenta naturaleza;

}

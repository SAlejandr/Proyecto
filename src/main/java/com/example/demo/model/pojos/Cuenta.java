package com.example.demo.model.pojos;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@Entity
public class Cuenta implements Serializable {

	@Id
	@Include
	private String codCuenta;

	@Column(name = "nom_cuenta")
	private String nombre;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "claseCuenta")
	private ConfiguracionCuentas claseCuenta;

	private boolean movimientos;
	private boolean terceros;
	private boolean ccostos;
	@Enumerated(EnumType.STRING)
	private NaturalezaCuenta natCuenta;
	private String cuentaSuperior;

}

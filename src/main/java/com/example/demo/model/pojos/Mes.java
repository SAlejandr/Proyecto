package com.example.demo.model.pojos;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "mes_fiscal")
public class Mes implements Serializable {

	@EmbeddedId
	@Include
	private MesFiscalId idFiscal;
	
	@Column(length=30, unique = true)
	private String nombre;

	private LocalDate inicio;

	private LocalDate fin;

	@Enumerated(EnumType.STRING)
	private EstadosDeMes estado;

}

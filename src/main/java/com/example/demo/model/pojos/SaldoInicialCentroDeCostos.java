package com.example.demo.model.pojos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder

@Entity
public class SaldoInicialCentroDeCostos implements Serializable{

	@EmbeddedId
	private IdSaldoInCCostos elId;
	
	@Column(precision = 2)
	private double debito;
	@Column(precision = 2)
	private double credito;
}

package com.example.demo.model.pojos;

import javax.persistence.Column;
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
public class SaldoCentroDeCostos {
	
	@EmbeddedId
	private IdSaldoCCostos elID;

	@Column(precision = 2)
	private double sumDebito;
	@Column(precision = 2)
	private double sumCredito;
	
	@Column(precision = 2)
	private double totalDebito;
	@Column(precision = 2)
	private double totalCredito; 
	
}

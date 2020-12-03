package com.example.demo.model.pojos;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode.Include;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder

@Entity
public class SaldoCentroDeCostos implements Serializable {
	
	@EmbeddedId
	@Include
	private IdSaldoCCostos elID;

	@Column(precision = 15, scale =2)
	private BigDecimal sumDebito;
	@Column(precision = 15, scale =2)
	private BigDecimal sumCredito;
	
	@Column(precision = 15, scale =2)
	private BigDecimal totalDebito;
	@Column(precision = 15, scale =2)
	private BigDecimal totalCredito; 
}

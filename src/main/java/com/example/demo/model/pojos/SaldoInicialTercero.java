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

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder

@Entity
public class SaldoInicialTercero implements Serializable{

	@EmbeddedId
	private IdSaldoInTercero id;
	
	@Column(precision = 15, scale = 2)
	private BigDecimal debito;
	@Column(precision = 15, scale = 2)
	private BigDecimal credito;
}

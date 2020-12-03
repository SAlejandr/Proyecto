package com.example.demo.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.example.demo.model.pojos.Documento;
import com.example.demo.model.pojos.IdDocumento;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class DTODebitoDiferenteDeCredito implements Serializable{

	@Include
	private String tipoDoc;
	
	private long numDoc;
	
	private BigDecimal sumDebito;
	
	private BigDecimal sumCredito;
	
}

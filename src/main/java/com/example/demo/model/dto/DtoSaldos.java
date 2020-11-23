package com.example.demo.model.dto;

import java.math.BigDecimal;

import com.example.demo.model.pojos.Cuenta;
import com.example.demo.model.pojos.SaldoCuenta;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DtoSaldos {
	
	private Cuenta cuenta;
	
	private BigDecimal debito;
	
	private BigDecimal credito;
	
	public static DtoSaldos extraerDatosSaldos(SaldoCuenta s) {
		
		
		return new DtoSaldos(s.getId().getCuenta(), s.getTotalDebito(), s.getTotalCredito());
		
	}
	
}

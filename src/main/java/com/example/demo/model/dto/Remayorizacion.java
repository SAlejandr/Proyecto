package com.example.demo.model.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Remayorizacion {

	private Integer mesInicial= LocalDate.now().getMonthValue();
	private Integer mesFinal= LocalDate.now().getMonthValue();
	private Integer anioConsulta= LocalDate.now().getYear();
	
	
	
}

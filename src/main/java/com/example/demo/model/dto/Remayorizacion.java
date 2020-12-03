package com.example.demo.model.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Remayorizacion {
	private Integer mesInicial= LocalDate.now().getMonthValue();
	private Integer mesFinal= LocalDate.now().getMonthValue();
	private Integer annoConsulta= LocalDate.now().getYear();

}

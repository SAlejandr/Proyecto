package com.example.demo.model.dto;

import com.example.demo.model.pojos.Mes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Auditoria {

	private Mes mesInicial, mesFinal;
	
}

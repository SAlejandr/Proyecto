package com.example.demo.model.pojos;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.NoArgsConstructor;

@Data
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "anno_fiscal")
public class Anno implements Serializable {

	@Id
	@Include
	private int elAnno;

	private LocalDate inicioAnno;
	private LocalDate finAnno;

	private boolean cerrado;
}

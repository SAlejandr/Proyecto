package com.example.demo.model.pojos;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.Vector;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.NoArgsConstructor;

@Builder
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Departamento implements Serializable {

	@Id
	@Include
	private int codDepartamento;

	private String nombre;
	
	
	
}

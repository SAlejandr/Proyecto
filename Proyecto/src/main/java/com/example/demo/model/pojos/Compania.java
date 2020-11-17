package com.example.demo.model.pojos;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Compania implements Serializable{

	@Id
	private String nit;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@MapsId
	@JoinColumn(name = "nit")
	private Tercero terceroPropio;

	private String nombre, direccion, telefono, ciudad, fax, rLegal, contador, revFiscal;

	private boolean cCostos;

}

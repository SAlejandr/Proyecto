package com.example.demo.model.pojos;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode (onlyExplicitlyIncluded = true)

@Entity
public class Documento {

	@EmbeddedId
	@Include
	private IdDocumento idDocumento;

	private LocalDate fecha;

	@Column(name = "valor_total", precision = 2)
	private float valorTotal;

	private String concepto;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "estado")
	private Estado estado;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "userResponsable")
	private Usuario userResponsable;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "responsable")
	private Responsable responsable;
	


}

package com.example.demo.model.pojos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Embeddable
public class IdDocumento implements Serializable {

	@ManyToOne
	@JoinColumn(name = "tipoDoc")
	private TipoDocumento tipoDocumento;

	@Column(name = "numDoc")
	private long numDocumento;

}

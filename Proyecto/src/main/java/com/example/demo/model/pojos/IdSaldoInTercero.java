package com.example.demo.model.pojos;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode


@Embeddable
public class IdSaldoInTercero implements Serializable{

	@Embedded
	private IdSaldoInicial idSaldoIn;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER )
	@JoinColumn(name="tercero")
	private Tercero tercero;
}

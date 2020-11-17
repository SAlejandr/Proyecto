package com.example.demo.model.pojos;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode

@Embeddable
public class MesFiscalId implements Serializable {

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "anno")
	private Anno anno;

	private String mes;

}

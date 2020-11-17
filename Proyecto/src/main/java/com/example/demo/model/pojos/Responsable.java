package com.example.demo.model.pojos;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Responsable implements Serializable {

	@Id
	private int codRes;

	private String nombre;
}

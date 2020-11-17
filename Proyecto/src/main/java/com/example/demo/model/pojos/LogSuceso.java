package com.example.demo.model.pojos;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)

@Entity
public class LogSuceso extends Log  implements Serializable{
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "codSuceso")
	private Suceso suceso;
	
	@ManyToOne
	@JoinColumn(name = "codTabla")
	private Tabla tabla;
	
	private LocalDateTime instante;
	
}

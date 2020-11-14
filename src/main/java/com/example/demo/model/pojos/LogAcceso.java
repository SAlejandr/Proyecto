package com.example.demo.model.pojos;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@SuperBuilder

@Entity
public class LogAcceso extends Log  implements Serializable {

	private LocalDateTime instanteDeAcceso;

	private LocalDateTime instanteDeSalida;
	
}

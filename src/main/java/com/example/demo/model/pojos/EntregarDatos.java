package com.example.demo.model.pojos;

import java.lang.reflect.Field;
import java.util.Vector;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EntregarDatos<T> {
    
	public Vector<String> darDatos(T t) {

		Vector<String> datos = new Vector<String>();

		for(Field f : t.getClass().getDeclaredFields()) {

			datos.add(f.getName());

		}

		return datos;

	}

	public Vector<String> darTipoDatos(T t){

		Vector<String> tiposDatos = new Vector<>();


		for (Field f : t.getClass().getDeclaredFields()) {

			tiposDatos.add(f.getType().getSimpleName());

		}


		return tiposDatos;

	}
}

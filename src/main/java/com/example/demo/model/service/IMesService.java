package com.example.demo.model.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.example.demo.model.pojos.Mes;
import com.example.demo.model.pojos.MesFiscalId;

public interface IMesService {

	public void save(Mes mes);

	public List<Mes> findAll();
	
	public Optional<Mes> buscarPorNombre(String nombre);
	
	public List<Mes> buscarMesesEnRango(LocalDate d1, LocalDate d2);

	public Optional<Mes> findById(MesFiscalId id);
	
	public Optional<Mes> findByFechaFin(LocalDate d);

	public boolean deleteById(MesFiscalId id);
	
	public String inactivarTodos();							


}

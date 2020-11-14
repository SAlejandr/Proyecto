package com.example.demo.model.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.pojos.Departamento;

public interface IDepartamentoService {

	public void save(Departamento comunidad);

	public List<Departamento> findAll();

	public Optional<Departamento> findById(int id);

	public boolean deleteById(int id);

}

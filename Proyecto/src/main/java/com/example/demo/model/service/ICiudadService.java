package com.example.demo.model.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.pojos.Ciudad;
import com.example.demo.model.pojos.IdCiudad;

public interface ICiudadService {

	public void save(Ciudad ciudad);

	public List<Ciudad> findAll();

	public Optional<Ciudad> findById(IdCiudad id);

	public boolean deleteById(IdCiudad id);
}

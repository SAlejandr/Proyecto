package com.example.demo.model.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.pojos.Suceso;

public interface ISucesoService {

	public void save(Suceso comunidad);

	public List<Suceso> findAll();

	public Optional<Suceso> findById(String id);

	public boolean deleteById(String id);

}

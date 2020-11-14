package com.example.demo.model.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.pojos.Estado;

public interface IEstadoService {
	
	public Optional<Estado> findById(byte id);
	
	public List<Estado> findAll();
	
	public void save(Estado estado);
	
	public boolean deleteById(byte id);

}

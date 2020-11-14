package com.example.demo.model.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.pojos.Compania;

public interface ICompaniaService {

	
	public void save(Compania compania);
	
	public Optional<Compania> findById(String id);
	
	public List<Compania> findAll();
	
	public boolean deleteById(String id);
}

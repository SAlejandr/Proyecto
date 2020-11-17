package com.example.demo.model.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.pojos.Responsable;

public interface IResponsableService {

	public void save(Responsable responsable);

	public List<Responsable> findAll();

	public Optional<Responsable> findById(int id);

	public boolean deleteById(int id);

}

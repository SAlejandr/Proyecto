package com.example.demo.model.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.pojos.Tercero;

public interface ITerceroService {

	public void save(Tercero tercero);

	public List<Tercero> findAll();

	public Optional<Tercero> findById(String id);

	public boolean deleteById(String id);

}

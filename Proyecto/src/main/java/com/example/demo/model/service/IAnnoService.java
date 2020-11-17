package com.example.demo.model.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.pojos.Anno;

public interface IAnnoService {

	public void save(Anno anno);

	public List<Anno> findAll();

	public Optional<Anno> findById(int id);

	public boolean deleteById(int id);

}

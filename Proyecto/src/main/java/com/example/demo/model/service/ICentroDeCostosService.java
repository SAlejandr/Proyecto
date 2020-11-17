package com.example.demo.model.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.pojos.CentroDeCosto;

public interface ICentroDeCostosService {

	public void save(CentroDeCosto centroDeCostos);

	public List<CentroDeCosto> findAll();

	public Optional<CentroDeCosto> findById(int id);

	public boolean deleteById(int id);

}

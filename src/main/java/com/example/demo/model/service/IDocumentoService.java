 package com.example.demo.model.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.pojos.Documento;
import com.example.demo.model.pojos.IdDocumento;
import com.example.demo.model.pojos.Mes;

public interface IDocumentoService {

	public void save(Documento documento);

	public List<Documento> findAll();

	public Optional<Documento> findById(IdDocumento id);

	public boolean deleteById(IdDocumento id);

	public List<Documento> buscarPorMes(Mes mes);

}

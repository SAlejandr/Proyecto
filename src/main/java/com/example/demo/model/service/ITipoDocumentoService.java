package com.example.demo.model.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.pojos.TipoDocumento;

public interface ITipoDocumentoService {

	public void save(TipoDocumento tipoDocumento);

	public List<TipoDocumento> findAll();

	public Optional<TipoDocumento> findById(int id);

	public boolean deleteById(int id);

}

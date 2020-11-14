package com.example.demo.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.pojos.TipoDocumento;
import com.example.demo.model.repository.TipoDocumentoRepository;

@Service
public class TipoDocumentoService implements ITipoDocumentoService{

	@Autowired TipoDocumentoRepository dao;
	
	@Override
	public void save(TipoDocumento tipoDocumento) {
		// TODO Auto-generated method stub
		
		if(dao.findById(tipoDocumento.getTipoDoc()).isEmpty())
			dao.save(tipoDocumento);
		
	}

	@Override
	public List<TipoDocumento> findAll() {
		// TODO Auto-generated method stub
		return (List<TipoDocumento>) dao.findAll();
	}

	@Override
	public Optional<TipoDocumento> findById(int id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	@Override
	public boolean deleteById(int id) {
		// TODO Auto-generated method stub
		
		boolean bool = dao.existsById(id);
		
		if(bool)
			dao.deleteById(id);
		
		return bool;
	}

}

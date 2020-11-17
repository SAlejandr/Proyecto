package com.example.demo.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.pojos.Documento;
import com.example.demo.model.pojos.IdDocumento;
import com.example.demo.model.pojos.Mes;
import com.example.demo.model.repository.DocumentoRepository;
import com.example.demo.model.repository.TipoDocumentoRepository;

@Service
public class DocumentoService implements IDocumentoService{


	@Autowired DocumentoRepository dao;
	@Autowired TipoDocumentoRepository subDAO;


	@Override
	public void save(Documento documento) {
		// TODO Auto-generated method stub
		
		dao.save(documento);
	}

	@Override
	public List<Documento> findAll() {
		// TODO Auto-generated method stub
		return (List<Documento>) dao.findAll();
	}

	@Override
	public Optional<Documento> findById(IdDocumento id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	@Override
	public boolean deleteById(IdDocumento id) {
		// TODO Auto-generated method stub
		
		boolean exito = false;
		
		if(!findById(id).isEmpty()) {
			
			dao.deleteById(id);
			
			exito = true;
		}
			
		return exito;
	}

	@Override
	public List<Documento> buscarPorMes(Mes mes) {
		// TODO Auto-generated method stub
		return dao.findByFechaBetween(mes.getInicio(), mes.getFin());
	}

}

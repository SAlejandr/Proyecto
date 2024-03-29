package com.example.demo.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.pojos.Responsable;
import com.example.demo.model.repository.ResponsableRepository;

@Service
public class ResponsableService implements IResponsableService{

	@Autowired private ResponsableRepository dao;
	
	@Override
	public void save(Responsable responsable) {
		// TODO Auto-generated method stub
		dao.save(responsable);
	}

	@Override
	public List<Responsable> findAll() {
		// TODO Auto-generated method stub
		return (List<Responsable>) dao.findAll();
	}

	@Override
	public Optional<Responsable> findById(int id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	@Override
	public boolean deleteById(int id) {
		// TODO Auto-generated method stub
		boolean exito = false;
		
		if(dao.existsById(id)) {
			
			exito = true;
			dao.deleteById(id);
		}
		
		return exito;
	}

}

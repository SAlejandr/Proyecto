package com.example.demo.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.pojos.Log;
import com.example.demo.model.repository.LogRepository;

@Service
public class LogService implements ILogService{

	@Autowired private LogRepository dao;
	
	@Override
	public void save(Log log) {
		// TODO Auto-generated method stub
		
		dao.save(log);
	}

	@Override
	public Optional<Log> findById(long consecutivo) {
		// TODO Auto-generated method stub
		return dao.findById(consecutivo);
	}

	@Override
	public List<Log> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public boolean deleteById(long consecutivo) {
		// TODO Auto-generated method stub
		
		boolean exito = false;
		
		if(dao.existsById(consecutivo)) {
			exito = true;
			dao.deleteById(consecutivo);
		}
		return exito;
	}

}

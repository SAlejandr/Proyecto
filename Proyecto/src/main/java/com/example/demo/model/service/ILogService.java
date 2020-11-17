package com.example.demo.model.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.pojos.Log;

public interface ILogService {

	
	public void save(Log log);
	
	public Optional<Log> findById(long consecutivo);
	
	public List<Log> findAll();
	
	public boolean deleteById(long consecutivo);
}

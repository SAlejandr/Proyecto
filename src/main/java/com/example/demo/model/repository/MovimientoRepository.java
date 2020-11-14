package com.example.demo.model.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.pojos.IdMovimiento;
import com.example.demo.model.pojos.Movimiento;

@Repository
public interface MovimientoRepository extends CrudRepository<Movimiento, IdMovimiento> {

	public List<Movimiento> findByFechaBetween(LocalDate d1, LocalDate d2);
	
}

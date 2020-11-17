package com.example.demo.model.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.pojos.Documento;
import com.example.demo.model.pojos.IdMovimiento;
import com.example.demo.model.pojos.Movimiento;

@Repository
public interface MovimientoRepository extends CrudRepository<Movimiento, IdMovimiento> {

	public List<Movimiento> findByFechaBetween(LocalDate d1, LocalDate d2);
	
	@Query("SELECT m FROM Movimiento m WHERE m.id.documento = ?1")
	public List<Movimiento> findByDocumento(Documento documento);
	
}

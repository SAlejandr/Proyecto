package com.example.demo.model.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.pojos.Mes;
import com.example.demo.model.pojos.MesFiscalId;

@Repository
public interface MesRepository extends JpaRepository<Mes, MesFiscalId> {

	public List<Mes> findByInicioGreaterThanEqualAndFinLessThan(LocalDate d1, LocalDate d2);
	
	public Optional<Mes> findByFin(LocalDate fin);
	
	@Transactional
	@Modifying
	@Query("UPDATE Mes m SET m.estado = 'CERRADO'")					//************************************
	public void inactivarTodos();
	
}

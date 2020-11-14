package com.example.demo.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.pojos.IdSaldoCCostos;
import com.example.demo.model.pojos.Mes;
import com.example.demo.model.pojos.SaldoCentroDeCostos;

@Repository
public interface SaldoCCostosRepository extends JpaRepository<SaldoCentroDeCostos, IdSaldoCCostos> {

	@Transactional
	@Modifying
	@Query("DELETE FROM SaldoCentroDeCostos s where s.id.mes = ?1")
	public void deleteByMes(Mes mes);
	
	@Query("SELECT s FROM SaldoCentroDeCostos s WHERE s.id.mes = ?1")
	public List<SaldoCentroDeCostos> findByMes(Mes mes);
}

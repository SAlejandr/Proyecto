package com.example.demo.model.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.pojos.IdSaldoTercero;
import com.example.demo.model.pojos.Mes;
import com.example.demo.model.pojos.SaldoTercero;

@Repository
public interface SaldoTerceroRepository extends JpaRepository<SaldoTercero, IdSaldoTercero> {

	@Transactional
	@Modifying
	@Query("DELETE FROM SaldoTercero s where s.id.mes = ?1")
	public void deleteByMes(Mes mes);
	
	@Query("SELECT s FROM SaldoTercero s where s.id.mes = ?1")
	public List<SaldoTercero> findByMes(Mes mes);
}

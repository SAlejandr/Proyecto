package com.example.demo.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.pojos.IdSaldo;
import com.example.demo.model.pojos.Mes;
import com.example.demo.model.pojos.SaldoCuenta;

@Repository
public interface SaldoRepository extends JpaRepository<SaldoCuenta, IdSaldo> {

	@Transactional
	@Modifying
	@Query("DELETE FROM SaldoCuenta s where s.id.mes = ?1")
	public void deleteByMes(Mes mes);
	
	@Query("SELECT s FROM SaldoCuenta s where s.id.mes = ?1")
	public List<SaldoCuenta> findByMes(Mes mes);
	
	
}

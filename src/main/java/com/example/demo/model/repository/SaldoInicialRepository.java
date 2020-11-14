package com.example.demo.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.dto.DtoSaldos;
import com.example.demo.model.pojos.Anno;
import com.example.demo.model.pojos.IdSaldoInicial;
import com.example.demo.model.pojos.SaldoInicialCuenta;

@Repository
public interface SaldoInicialRepository extends JpaRepository<SaldoInicialCuenta, IdSaldoInicial> {

	
	@Query("SELECT s.id.cuenta as cuenta, s.debito as debito, s.credito as credito FROM SaldoInicialCuenta s where s.id.anno = ?1")
	public List<DtoSaldos> findDataByYear(Anno anno);
	
}

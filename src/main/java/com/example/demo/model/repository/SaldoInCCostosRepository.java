package com.example.demo.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.pojos.IdSaldoInCCostos;
import com.example.demo.model.pojos.SaldoInicialCentroDeCostos;

@Repository
public interface SaldoInCCostosRepository extends JpaRepository<SaldoInicialCentroDeCostos, IdSaldoInCCostos> {

}

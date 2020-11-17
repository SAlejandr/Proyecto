package com.example.demo.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.pojos.IdSaldoInTercero;
import com.example.demo.model.pojos.SaldoInicialTercero;

@Repository
public interface SaldoInTerceroRepository extends JpaRepository<SaldoInicialTercero, IdSaldoInTercero> {

}

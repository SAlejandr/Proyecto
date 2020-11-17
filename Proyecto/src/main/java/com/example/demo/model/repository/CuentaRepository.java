package com.example.demo.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.pojos.Cuenta;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Integer> {

	public List<Cuenta> findByMovimientos(boolean movimiento);
	
	public List<Cuenta> findByMovimientosAndCcostos(boolean movimiento, boolean ccostos);
	public List<Cuenta> findByMovimientosAndTerceros(boolean movimiento, boolean terceros);
}

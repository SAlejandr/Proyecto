package com.example.demo.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.pojos.Departamento;

@Repository
public interface DepartamentoRepository extends CrudRepository<Departamento, Integer> {

}

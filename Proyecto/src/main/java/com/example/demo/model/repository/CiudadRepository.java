package com.example.demo.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.pojos.Ciudad;
import com.example.demo.model.pojos.IdCiudad;

@Repository
public interface CiudadRepository extends CrudRepository<Ciudad, IdCiudad> {

}

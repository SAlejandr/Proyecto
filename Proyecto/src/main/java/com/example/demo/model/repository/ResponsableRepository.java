package com.example.demo.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.pojos.Responsable;

@Repository
public interface ResponsableRepository extends CrudRepository<Responsable, Integer> {

}

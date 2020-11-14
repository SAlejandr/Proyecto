package com.example.demo.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.pojos.Compania;

@Repository
public interface CompaniaRepository extends CrudRepository<Compania, String> {

}

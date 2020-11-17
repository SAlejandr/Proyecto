package com.example.demo.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.pojos.Suceso;

public interface SucesoRepository extends JpaRepository<Suceso, Integer> {

}

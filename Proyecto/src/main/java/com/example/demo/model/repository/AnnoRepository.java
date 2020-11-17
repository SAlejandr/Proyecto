package com.example.demo.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.pojos.Anno;

@Repository
public interface AnnoRepository extends JpaRepository<Anno, Integer> {

	public List<Anno> findByCerrado(Boolean cerrado);
}

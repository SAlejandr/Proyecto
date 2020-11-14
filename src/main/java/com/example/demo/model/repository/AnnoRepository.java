package com.example.demo.model.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.pojos.Anno;

@Repository
public interface AnnoRepository extends JpaRepository<Anno, Integer> {

	public List<Anno> findByCerrado(Boolean cerrado);
	
	@Transactional
	@Modifying
	@Query("UPDATE Anno a SET a.cerrado = true")
	public void inactivarTodos();
}

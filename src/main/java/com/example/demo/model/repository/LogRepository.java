package com.example.demo.model.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.pojos.Log;
import com.example.demo.model.pojos.LogAcceso;
import com.example.demo.model.pojos.Movimiento;

@Repository
public interface LogRepository extends JpaRepository<Log, Long> {
		
}

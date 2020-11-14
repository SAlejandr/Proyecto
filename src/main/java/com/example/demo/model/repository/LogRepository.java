package com.example.demo.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.pojos.Log;

@Repository
public interface LogRepository extends JpaRepository<Log, Long> {

}

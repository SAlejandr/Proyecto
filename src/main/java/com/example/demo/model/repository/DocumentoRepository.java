package com.example.demo.model.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.pojos.Documento;
import com.example.demo.model.pojos.IdDocumento;

@Repository
public interface DocumentoRepository extends CrudRepository<Documento, IdDocumento> {

	List<Documento> findByFechaBetween(LocalDate d1, LocalDate d2);
}

package com.example.demo.model.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.dto.DTODebitoDiferenteDeCredito;
import com.example.demo.model.pojos.Documento;
import com.example.demo.model.pojos.IdMovimiento;
import com.example.demo.model.pojos.Movimiento;

@Repository
public interface MovimientoRepository extends CrudRepository<Movimiento, IdMovimiento> {

	public List<Movimiento> findByFechaBetween(LocalDate d1, LocalDate d2);
	
	@Query("SELECT m FROM Movimiento m WHERE m.id.documento = ?1")
	public List<Movimiento> findByDocumento(Documento documento);
	
	@Query("Select movimiento from Movimiento movimiento where movimiento.cuenta.movimientos = false and movimiento.fecha between ?1 and ?2")
	public List<Movimiento> movimientosEnCuentasNoAfectables(LocalDate fechaInicio, LocalDate fechaFinal);
	
	@Query("Select movimiento from Movimiento movimiento where movimiento.debito=0.00 and movimiento.credito=0.00 and movimiento.fecha between ?1 and ?2")
	public List<Movimiento> movimientosSinValor(LocalDate fechaInicio, LocalDate fechaFin);
	
	@Query(value="Select distinct mov.tipo_doc, mov.num_doc, coalesce(sum(mov.dedito),0) as sumDebito, coalesce(sum(mov.credito),0) as sumCredito from Movimiento mov "
			+ "where sumDebito.compareTo(sumCredito)=1 and mov.fecha between ?1 and ?2 group by mov.tipo_doc, mov.num_doc", nativeQuery=true)
	public List<DTODebitoDiferenteDeCredito> debitosDiferenteCreditos(LocalDate fechaInicio, LocalDate fechaFin);
	
	@Query("Select movimiento from Movimiento movimiento where movimiento.fecha between ?1 and ?2 "
			+ "and (movimiento.tercero.id = '.' or movimiento.tercero.id = '') "
			+ "and movimiento.cuenta.terceros = '1'")
	public List<Movimiento> movimientosSinTerceros(LocalDate fechaInicio, LocalDate fechaFin);

	@Query("Select movimiento from Movimiento movimiento where movimiento.fecha between ?1 and ?2 "
			+ "and (movimiento.tercero.id != '.' and movimiento.tercero.id != '') "
			+ "and movimiento.cuenta.terceros = '0'")
	public List<Movimiento> movimientosConTercerosInnecesarios(LocalDate fechaInicio, LocalDate fechaFin);

	
	@Query("Select movimiento from Movimiento movimiento where movimiento.fecha between ?1 and ?2 "
			+ "and movimiento.cCostos.codCentro = '0' "
			+ "and movimiento.cuenta.ccostos = true")
	public List<Movimiento> movimientosSinCentros(LocalDate fechaInicio, LocalDate fechaFin);
	
	@Query("Select movimiento from Movimiento movimiento where movimiento.fecha between ?1 and ?2 "
			+ "and movimiento.cCostos.codCentro != '0' "
			+ "and movimiento.cuenta.ccostos = false")
	public List<Movimiento> movimientosConCentrosInnecesarios(LocalDate fechaInicio, LocalDate fechaFin);
}

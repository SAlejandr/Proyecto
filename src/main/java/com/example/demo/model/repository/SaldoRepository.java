package com.example.demo.model.repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.pojos.IdSaldo;
import com.example.demo.model.pojos.Mes;
import com.example.demo.model.pojos.SaldoCuenta;

@Repository
public interface SaldoRepository extends JpaRepository<SaldoCuenta, IdSaldo> {

	@Transactional
	@Modifying
	@Query("DELETE FROM SaldoCuenta s where s.id.mes = ?1")
	public void deleteByMes(Mes mes);
	
	@Query("SELECT s FROM SaldoCuenta s where s.id.mes = ?1")
	public List<SaldoCuenta> findByMes(Mes mes);
	
	@Query("SELECT s FROM SaldoCuenta s WHERE length(s.id.cuenta.codCuenta) = ?1 and s.id.mes = ?2 order by s.id.cuenta.codCuenta desc")
	public List<SaldoCuenta> findByLongitudDeCuenta(int longitud, Mes mes);
	
	
	@Transactional
	@Modifying		
	@Query(value = "insert into saldo_cuenta  "
			+ "SELECT distinct coalesce(sum(res.sum_credito),0) as sum_credito, coalesce(SUM(res.sum_debito),0) as sum_debito,  " 
			+ "            CASE WHEN (coalesce(sum(res.sum_credito),0) - coalesce(SUM(res.sum_debito),0) + coalesce(sum(res.total_credito),0) - coalesce(SUM(res.total_debito),0)) > 0 "  
			+ "				 THEN (coalesce(sum(res.sum_credito),0) - coalesce(SUM(res.sum_debito),0) + coalesce(sum(res.total_credito),0) - coalesce(SUM(res.total_debito),0)) "  
			+ "                 ELSE 0 "  
			+ "            END  as total_credito, "  
			+ "			CASE WHEN (coalesce(sum(res.sum_debito),0) - coalesce(SUM(res.sum_credito),0) + coalesce(sum(res.total_debito),0) - coalesce(SUM(res.total_credito),0)) > 0 "  
			+ "				 THEN (coalesce(sum(res.sum_debito),0) - coalesce(SUM(res.sum_credito),0) + coalesce(sum(res.total_debito),0) - coalesce(SUM(res.total_credito),0)) "  
			+ "                 ELSE  0 "  
			+  "            END  as total_debito, "  
			+ "			res.cuenta as cuenta, ?1 as anno , ?2 as mes "  
			+ " "
			+ "FROM  ((SELECT distinct 0 as sum_credito, 0 as sum_debito, " 
			+ "					coalesce(sum(si.credito),1) as total_credito, coalesce(SUM(si.debito),2) as total_debito, "  
			+ "					si.cuenta as cuenta, ?1 as anno , ?2 as mes "  
			+ "					FROM  saldo_inicial_cuenta as si "
			+ "					WHERE si.anno = ?1 "  
			+ "					GROUP BY si.cuenta "  
			+ "					ORDER BY si.cuenta) "
			+ "union "
			+ "(SELECT distinct coalesce(sum(m.credito),0) as sum_credito, coalesce(SUM(m.debito),0) as sum_debito, "  
			+ "					0 as total_credito, 0 as total_debito, c.cod_cuenta as cuenta, ?1 as anno , ?2 as mes  "  
			+ "		FROM cuenta c left join movimiento m on c.movimientos = '1' and c.cod_cuenta = m.cuenta and  m.fecha between ?3 and ?4 "  
			+ "		GROUP BY m.cuenta,anno,mes "  
			+ "		ORDER BY m.cuenta,anno,mes)) as res "  
			+ " GROUP BY res.cuenta, res.anno, res.mes", nativeQuery = true)
	public void remayorizarMoviblesEnero(int anno, int mes, LocalDate inicioMes, LocalDate finMes);
	
	@Transactional
	@Modifying	
	@Query(value = "insert into saldo_cuenta "
			+ "			SELECT distinct coalesce(sum(res.sum_credito),0) as sum_credito, coalesce(SUM(res.sum_debito),0) as sum_debito, "  
			+ "			            CASE WHEN (coalesce(sum(res.sum_credito),0) - coalesce(SUM(res.sum_debito),0) + coalesce(sum(res.total_credito),0) - coalesce(SUM(res.total_debito),0)) > 0  "  
			+ "							 THEN (coalesce(sum(res.sum_credito),0) - coalesce(SUM(res.sum_debito),0) + coalesce(sum(res.total_credito),0) - coalesce(SUM(res.total_debito),0)) " 
			+ "			                 ELSE 0 " 
			+ "			            END  as total_credito, "  
			+ "						CASE WHEN (coalesce(sum(res.sum_debito),0) - coalesce(sum(res.sum_credito),0) + coalesce(sum(res.total_debito),0) - coalesce(SUM(res.total_credito),0)) > 0 "  
			+ "							 THEN (coalesce(sum(res.sum_debito),0) - coalesce(sum(res.sum_credito),0) + coalesce(sum(res.total_debito),0) - coalesce(SUM(res.total_credito),0)) "  
			+ "			                 ELSE  0 "  
			+ "			            END  as total_debito, "  
			+ "						res.cuenta as cuenta,  ?1 as anno , ?2 as mes "  
			+ " "
			+ "			FROM  ((SELECT distinct coalesce(sum(m.credito),0) as sum_credito, coalesce(SUM(m.debito),0) as sum_debito, "  
			+ "								0 as total_credito, 0 as total_debito,  "  
			+ "								c.cod_cuenta as cuenta,  ?1 as anno , ?2 as mes  "  
			+ "					FROM cuenta c left join movimiento m on c.movimientos = '1' and c.cod_cuenta = m.cuenta and  m.fecha between ?3 and ?4 "  
			+ "					GROUP BY m.cuenta,anno,mes " 
			+ "					ORDER BY m.cuenta,anno,mes) "  
			+ "					union "  
			+ "					(SELECT distinct 0 as sum_credito, 0 as sum_debito, "
			+ "								 coalesce(sum(st.total_credito),0) as total_credito, coalesce(sum(st.total_debito),0) as total_debito, " 
			+ "								 st.cuenta as cuenta, ?1 as anno, ?2 as mes  " 
			+ "					FROM  saldo_cuenta st where anno = ?1 and mes = (?2 - 1) " 
			+ "					GROUP BY st.cuenta,anno,mes  "  
			+ "					ORDER BY st.cuenta,anno,mes)) as res "  
			+ "			 GROUP BY res.cuenta, anno, mes", nativeQuery = true)
	public void remayorizarMoviblesRestoAnno(int anno, int mes, LocalDate inicioMes, LocalDate finMes);	
	


	@Query(value = "SELECT s.cuenta, s.anno, s.mes, s.sum_debito, s.sum_credito, s.total_debito, s.total_credito"
			+ " FROM saldo_Cuenta s "
			+ " WHERE s.anno = ?1 "
			+ "			and s.mes = ?2 "
			+ "			and length(s.cuenta) = ?3"
			+ " order by s.cuenta desc", nativeQuery = true)
	public List<Object[]> remayorizacion2(int anno, int mes, int largo);
	
	
	@Transactional
	@Modifying
	@Query(value=" DELETE FROM SaldoCuenta s "
				+ "  WHERE s.sumDebito=0.0 and s.sumCredito=0.0 and s.totalDebito=0.0 and s.totalCredito=0.0")
	public void borrarRegistrosCeros();
}

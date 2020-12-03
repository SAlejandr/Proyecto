use proyecto;

SET SQL_MODE = '';

SET SQL_SAFE_UPDATES = 0;
 
DELETE FROM proyecto.saldo_centro_de_costos
WHERE anno = 2020 and mes = 1;

 -- insert into saldo_centro_de_costos
SELECT distinct coalesce(sum(m.credito),0) as sum_credito, 
						coalesce(SUM(m.debito),0) as sum_debito, 
                        							CASE WHEN(si.debito is null) 
									THEN CASE WHEN (c.nat_cuenta='DEBITO') 
											  THEN  0 
											  ELSE  coalesce(sum(m.credito) -sum(m.debito),0) 
											  END  
									ELSE CASE WHEN (c.nat_cuenta='DEBITO') 
											  THEN  0 
											  ELSE  coalesce(si.credito + coalesce(SUM(m.credito),0) - coalesce(sum(m.debito),0),0) 
											  END 
								END as total_credito, 
			 				CASE WHEN (si.debito is null) 
									THEN CASE WHEN (c.nat_cuenta='DEBITO') 
											  THEN  coalesce(sum(m.debito) - SUM(m.credito),0) 
											  ELSE  0 
											  END 
									ELSE  CASE WHEN (c.nat_cuenta='DEBITO') 
											   THEN coalesce(si.debito + coalesce(sum(m.debito),0) - coalesce(sum(m.credito),0),0) 
											   ELSE 0 
											   END 
								END as total_debito,
						c.cod_cuenta as cuenta, 
						coalesce(m.c_costos,0) as centro,
			         2020 as anno , 
			         1 as mes 


			 FROM cuenta c left join movimiento m on  c.cod_cuenta = m.cuenta 
								 left join saldo_inicial_centro_de_costos si on si.cuenta = c.cod_cuenta and si.c_costos = m.c_costos
			WHERE c.ccostos = 1  and m.fecha  between '2020-01-01' and '2020-01-31' 
			 group by c.cod_cuenta,m.c_costos,anno,mes 
			order by c.cod_cuenta,m.c_costos,anno,mes;
            
DELETE FROM proyecto.saldos_centro
where sum_credito = 0 and sum_debito = 0 and total_credito = 0 and total_debito = 0;
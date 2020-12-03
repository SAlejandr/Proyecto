 insert into saldos_tercero
SELECT distinct coalesce(sum(m.credito),0) as suma_credito, 
						coalesce(SUM(m.debito),0) as suma_debito, 
						coalesce(m.cod_tercero,'.') as tercero, 
						c.cod_cuenta as cuenta, 
			         2020 as anio , 
			         1 as mes, 
							CASE WHEN(si.debito is null) 
									THEN CASE WHEN (c.naturaleza='0') 
											  THEN  0 
											  ELSE  coalesce(sum(m.credito) -sum(m.debito),0) 
											  END  
									ELSE CASE WHEN (c.naturaleza='0') 
											  THEN  0 
											  ELSE  coalesce(si.credito + coalesce(SUM(m.credito),0) - coalesce(sum(m.debito),0),0) 
											  END 
								END as saldo_credito, 
			 				CASE WHEN (si.debito is null) 
									THEN CASE WHEN (c.naturaleza='0') 
											  THEN  coalesce(sum(m.debito) - SUM(m.credito),0) 
											  ELSE  0 
											  END 
									ELSE  CASE WHEN (c.naturaleza='0') 
											   THEN coalesce(si.debito + coalesce(sum(m.debito),0) - coalesce(sum(m.credito),0),0) 
											   ELSE 0 
											   END 
								END as saldo_debito 

			 FROM cuentas c left join movimientos m on  c.cod_cuenta = m.cod_cuenta 
								 left join saldos_iniciales_tercero si on si.cod_cuenta = c.cod_cuenta and si.cod_tercero = m.cod_tercero
			WHERE c.terceros = 'S'  and m.fecha  between '2020-01-01' and '2020-01-31' 
			 group by c.cod_cuenta,m.cod_tercero,anio 
			order by c.cod_cuenta,m.cod_tercero,anio;
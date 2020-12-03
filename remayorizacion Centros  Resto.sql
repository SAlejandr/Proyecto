use conta;

SET SQL_SAFE_UPDATES = 0;
 
DELETE FROM conta.saldos_tercero
WHERE anio = 2020 and mes = 2;

INSERT INTO saldos_tercero   
			SELECT distinct coalesce(sum(res.suma_credito),0) as suma_credito, coalesce(SUM(res.suma_debito),0) as suma_debito,  
						res.cuenta as cuenta, coalesce(res.tercero,'.') as tercero, 2020 as anio , 2 as mes,   
			            CASE WHEN (coalesce(sum(res.suma_credito),0) - coalesce(SUM(res.suma_debito),0) + coalesce(sum(res.saldo_credito),0) - coalesce(SUM(res.saldo_debito),0)) > 0  
							 THEN (coalesce(sum(res.suma_credito),0) - coalesce(SUM(res.suma_debito),0) + coalesce(sum(res.saldo_credito),0) - coalesce(SUM(res.saldo_debito),0))   
			                 ELSE 0 
			            END  as saldo_credito,   
						CASE WHEN (coalesce(sum(res.suma_debito),0) - coalesce(sum(res.suma_credito),0) + coalesce(sum(res.saldo_debito),0) - coalesce(SUM(res.saldo_credito),0)) > 0   
							 THEN (coalesce(sum(res.suma_debito),0) - coalesce(sum(res.suma_credito),0) + coalesce(sum(res.saldo_debito),0) - coalesce(SUM(res.saldo_credito),0))   
			                 ELSE  0   
			            END  as saldo_debito   
			  
			FROM  ((SELECT distinct coalesce(sum(m.credito),0) as suma_credito, coalesce(SUM(m.debito),0) as suma_debito,    
								c.cod_cuenta as cuenta, coalesce(m.cod_tercero,'.') as tercero, 2020 as anio , 2 as mes, 0 as saldo_credito, 0 as saldo_debito   
					FROM cuentas c left join movimientos m on c.terceros = 'S' and c.cod_cuenta = m.cod_cuenta and  m.fecha between '2020-02-01' and '2020-02-29'   
					GROUP BY m.cod_cuenta,m.cod_tercero,anio,mes   
					ORDER BY m.cod_cuenta,m.cod_tercero,anio,mes)   
					union  
					(SELECT distinct 0 as suma_credito, 0 as suma_debito, st.cod_cuenta as cuenta, coalesce(st.cod_tercero,'.') as tercero,  
								2020 as anio , 2 as mes, coalesce(sum(st.saldo_credito),0) as saldo_credito, coalesce(sum(st.saldo_debito),0) as saldo_debito   
					FROM  saldos_tercero st where anio = 2020 and mes = 1   
					GROUP BY st.cod_cuenta,st.cod_tercero,anio,mes   
					ORDER BY st.cod_cuenta,st.cod_tercero,anio,mes)) as res   
			 GROUP BY res.cuenta, res.tercero, anio, mes;
 
 DELETE FROM conta.saldos_centro
where suma_credito = 0 and suma_debito = 0 and saldo_credito = 0 and saldo_debito = 0;

SELECT s.cuenta as Cuenta, c.nom_cuenta as NombreCuenta,   coalesce(si.debito,0) as SI_Debito, coalesce(si.credito,0) as SI_Credito,  coalesce(s.sum_debito,0.00) as Debito, coalesce(s.sum_credito,0.00) as Credito, 
		CASE WHEN coalesce(s.total_debito,0) - coalesce(s.total_credito,0) > 0
				THEN coalesce(s.total_debito,0) - coalesce(s.total_credito,0) 
				ELSE 0
				END as SF_Dedito,
        CASE WHEN coalesce(s.total_credito,0) - coalesce(s.total_debito,0) > 0
				THEN coalesce(s.total_credito,0) - coalesce(s.total_debito,0) 
                ELSE 0
                END as SF_Credito
 FROM proyecto.cuenta c left join proyecto.saldo_cuenta s on s.mes = 1 and s.anno=2020 
					left join proyecto.saldo_inicial_cuenta as si on si.cuenta=s.cuenta and si.anno = 2020 
WHERE s.cuenta= c.cod_cuenta 
ORDER BY Cuenta;
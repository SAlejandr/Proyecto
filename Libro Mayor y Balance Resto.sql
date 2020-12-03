SELECT s.cuenta AS Cuenta, c.nom_cuenta AS NombreCuenta,   COALESCE(SI.total_debito,0) AS SI_Debito, COALESCE(SI.total_credito,0) AS SI_Credito,  COALESCE(s.sum_debito,0) AS Debito, COALESCE(s.sum_credito,0) AS Credito, 
		CASE WHEN COALESCE(s.total_debito,0) - COALESCE(s.total_credito,0) > 0
				THEN COALESCE(s.total_debito,0) - COALESCE(s.total_credito,0) 
				ELSE 0
				END AS SF_Dedito,
        CASE WHEN COALESCE(s.total_credito,0) - COALESCE(s.total_debito,0) > 0
				THEN COALESCE(s.total_credito,0) - COALESCE(s.total_debito,0) 
                ELSE 0
                END AS SF_Credito
 FROM proyecto.cuenta c LEFT JOIN proyecto.saldo_cuenta s ON s.mes = 2 AND s.anno=2020 
					LEFT JOIN proyecto.saldo_cuenta AS si ON si.cuenta=s.cuenta AND si.anno = 2020 AND si.mes = 1
 WHERE s.cuenta= c.cod_cuenta AND LENGTH(s.cuenta)=4;
ORDER BY Cuenta;
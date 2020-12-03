SELECT DISTINCT m.nombre, s.cuenta,c.nom_cuenta, sic.debito, sic.credito, s.sum_debito, s.sum_credito, s.total_debito, s.total_credito
FROM saldo_cuenta s INNER JOIN saldo_inicial_cuenta sic 
ON s.cuenta =sic.cuenta AND  s.anno =sic.anno
INNER JOIN  cuenta c
ON s.cuenta = c.cod_cuenta
INNER JOIN mes_fiscal m 
ON m.anno = s.anno AND m.mes= s.mes
WHERE s.mes = 1 AND s.anno = 2020
ORDER BY s.cuenta ASC;
SELECT m.cuenta, c.nom_cuenta, m.tipo_doc, td.nombre_documento,SUM(debito), SUM(credito)
FROM movimiento AS m 
	INNER JOIN cuenta AS c
		ON m.cuenta = c.cod_cuenta
	INNER JOIN tipo_documento td
		ON td.tipo_doc = m.tipo_doc
WHERE fecha BETWEEN '2020-01-01' AND '2020-01-31'
GROUP BY m.cuenta, m.tipo_doc
ORDER BY m.cuenta, m.tipo_doc
LIMIT 100000
;

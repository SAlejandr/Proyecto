SELECT s.cod_cuenta as Cuenta, c.nombre as NombreCuenta,   ifnull(si.debito,0) as SI_Debito, ifnull(si.credito,0) as SI_Credito,  ifnull(s.suma_debito,0) as Debito, ifnull(s.suma_credito,0) as Credito, ifnull(s.saldo_debito,0) as SF_Dedito, ifnull(s.saldo_credito,0) as SF_Credito
 FROM conta.cuentas c left join conta.saldos_cuenta s on s.mes = 1 and s.anio=2020 
					left join conta.saldos_iniciales_cuenta as si on si.cod_cuenta=s.cod_cuenta 
 WHERE s.cod_cuenta= c.cod_cuenta 
ORDER BY Cuenta;
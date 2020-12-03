SELECT saldos_cuenta.cod_cuenta,
		cuentas.nombre, 
        saldos_iniciales_cuenta.debito,
        saldos_iniciales_cuenta.credito,
        sum(saldos_cuenta.suma_debito), 
        sum(saldos_cuenta.suma_credito), 
        sum(saldos_cuenta.saldo_debito), 
		sum(saldos_cuenta.saldo_credito)
 FROM cuentas, saldos_cuenta left join saldos_iniciales_cuenta on saldos_iniciales_cuenta.cod_cuenta = saldos_cuenta.cod_cuenta
 where saldos_cuenta.anio = 2020 and mes =1 and saldos_cuenta.cod_cuenta = cuentas.cod_cuenta
 group by saldos_cuenta.cod_cuenta;
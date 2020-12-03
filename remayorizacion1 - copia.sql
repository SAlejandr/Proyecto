select distinct sum(movimientos.credito), sum(movimientos.debito), cuentas.cod_cuenta,  2020 as anio, 1 as mes, sum(saldos_iniciales_cuenta.credito), sum(saldos_iniciales_cuenta.debito)
				
from cuentas left join movimientos on cuentas.cod_cuenta = movimientos.cod_cuenta and movimientos.fecha >= '2020-01-01' and movimientos.fecha <= '2020-01-31'
			 left join saldos_iniciales_cuenta on saldos_iniciales_cuenta.cod_cuenta= cuentas.cod_cuenta
where cuentas.movimiento='S'
group by cuentas.cod_cuenta
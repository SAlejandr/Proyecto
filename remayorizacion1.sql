USE conta;
select distinct ifnull(SUM(movimientos.credito),0),
				ifnull(sum(movimientos.debito),0), 
				cuentas.cod_cuenta,  
                2020 as anio, 
                1 as mes, 
                IF(IFNULL(saldos_iniciales_cuenta.credito,0)=0,0,IFNULL(saldos_iniciales_cuenta.credito,0)-ifnull(sum(movimientos.debito),0)+ifnull(SUM(movimientos.credito),0)), 
                IF(IFNULL(saldos_iniciales_cuenta.debito,0)=0,0,IFNULL(saldos_iniciales_cuenta.debito,0)+ifnull(sum(movimientos.debito),0)-ifnull(SUM(movimientos.credito),0))
				
from cuentas left join movimientos on cuentas.cod_cuenta = movimientos.cod_cuenta and movimientos.fecha >= '2020-01-01' and movimientos.fecha <= '2020-01-31'
			 left join saldos_iniciales_cuenta on saldos_iniciales_cuenta.cod_cuenta= cuentas.cod_cuenta
             
-- where cuentas.movimiento='S'
group by cuentas.cod_cuenta,anio,mes;
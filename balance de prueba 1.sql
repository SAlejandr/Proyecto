USE conta;
select distinct ifnull(SUM(m.credito),0) as sumCredito,
				ifnull(sum(m.debito),0) as sumDebito, 
				c.cod_cuenta as codCuenta,  
                2020 as anio, 
                1 as mes, 
				if(isnull(si.debito),(if(c.naturaleza='0', ifnull(m.debito - m.credito,0), 0)), (if(c.naturaleza='0', ifnull(si.debito + m.debito - m.credito,0), 0))) as SF_Debito,
				if(isnull(si.debito),(if(c.naturaleza='0', 0 , ifnull(m.credito - m.debito,0))), (if(c.naturaleza='0', 0, ifnull(si.credito + m.credito - m.debito,0)))) as SF_Credito
                
from cuentas c left join movimientos m on c.movimiento='S' and c.cod_cuenta = m.cod_cuenta and m.fecha >= '2020-01-01' and m.fecha <= '2020-01-31'
			 left join saldos_iniciales_cuenta si on si.cod_cuenta= c.cod_cuenta
group by c.cod_cuenta,anio,mes;
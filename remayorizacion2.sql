USE conta; 
SET SQL_SAFE_UPDATES = 0;
DELETE FROM saldos_cuenta
		WHERE anio = 2020 and mes = 1; 
        
insert into saldos_cuenta
select distinct ifnull(SUM(m.credito),0) as sumCredito,
				ifnull(sum(m.debito),0) as sumDebito,  
				c.cod_cuenta as codCuenta,  
                2020 as anio, 
                1 as mes, 
				if(isnull(si.debito),if(c.naturaleza='0', 0 , ifnull(sum(m.credito) -sum(m.debito),0)), if(c.naturaleza='0', 0 , ifnull(si.credito + ifnull(SUM(m.credito),0) - ifnull(sum(m.debito),0),0))) as SF_Credito,
				if(isnull(si.debito),if(c.naturaleza='0', ifnull(sum(m.debito) - SUM(m.credito),0), 0), if(c.naturaleza='0', ifnull(si.debito + ifnull(sum(m.debito),0) - ifnull(sum(m.credito),0),0), 0)) as SF_Debito

                
from cuentas c left join movimientos m on c.movimiento='S' and c.cod_cuenta = m.cod_cuenta and m.fecha >= '2020-01-01' and m.fecha <= '2020-01-31'
			 left join saldos_iniciales_cuenta si on si.cod_cuenta= c.cod_cuenta
group by c.cod_cuenta,anio,mes;

delete from saldos_cuenta s
WHERE ifnull(s.suma_debito,0)=0.0 and ifnull(s.suma_credito,0)=0.0 and ifnull(s.saldo_debito,0)=0.0 and ifnull(s.saldo_credito,0)=0.0;
SET SQL_SAFE_UPDATES = 1;
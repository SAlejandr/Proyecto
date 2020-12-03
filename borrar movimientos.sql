SET SQL_SAFE_UPDATES = 0;
delete FROM conta.movimientos
where fecha >= '2020-01-01' and fecha <= '2020-01-31';
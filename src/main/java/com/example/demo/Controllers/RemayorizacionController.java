package com.example.demo.Controllers;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.dto.DTOSaldoCuenta;
import com.example.demo.model.dto.Remayorizacion;
import com.example.demo.model.pojos.Anno;
import com.example.demo.model.pojos.ConfiguracionDePlanDeCuentas;
import com.example.demo.model.pojos.Cuenta;
import com.example.demo.model.pojos.Mes;
import com.example.demo.model.pojos.MesFiscalId;
import com.example.demo.model.pojos.SaldoCuenta;
import com.example.demo.model.service.IAnnoService;
import com.example.demo.model.service.IConfPlanDeCuentasService;
import com.example.demo.model.service.ICuentaService;
import com.example.demo.model.service.IMesService;
import com.example.demo.model.service.IMovimientoService;
import com.example.demo.model.service.ISaldoCCostosService;
import com.example.demo.model.service.ISaldoCuentaService;
import com.example.demo.model.service.ISaldoTerceroService;
import com.example.demo.model.service.SaldoCuentaService;

@RestController
@RequestMapping("pro/procesos/remayorizacion")
public class RemayorizacionController {

	@Autowired private ISaldoCuentaService saldoCuentaService;
	@Autowired private ISaldoCCostosService saldoCentrosService;
	@Autowired private ISaldoTerceroService saldoTerceroService;

	@Autowired private IConfPlanDeCuentasService confPlanDeCuentasService;
	@Autowired private ICuentaService cuentaService;
	@Autowired private IAnnoService annoService;
	@Autowired private IMesService mesService;
	@Autowired private IMovimientoService movimientoService;

	@PostMapping(value = "/generar")
	public ResponseEntity<Remayorizacion> remayorizar(@RequestBody Remayorizacion remayorizacion){

		ResponseEntity<Remayorizacion> respuesta = new ResponseEntity<Remayorizacion>(HttpStatus.BAD_REQUEST);
		//Se obtienen las variables que guiarán el proceso de remayorización
		int mesInicial= remayorizacion.getMesInicial();
		int mesFinal= remayorizacion.getMesFinal();
		int annoConsulta= remayorizacion.getAnnoConsulta();

		Optional<Anno> opAnno = annoService.findById(annoConsulta);

		//Verificamos que el año exista y esté activo
		if(opAnno.isPresent() && !opAnno.get().isCerrado()) {


			int mesActual = mesInicial;

			
			//Ciclo para ir procesando cada mes del rango solicitado
			while(mesActual <= mesFinal) {
				
				MesFiscalId idMes = new MesFiscalId(opAnno.get(), mesActual);


				Mes elMes = mesService.findById(idMes).get();
				
				//Verificamos que el mes exista. Si no se avisa al usuario que debe crearlo
				if(elMes !=null) {

					//Mes elMes = mesAux.get();
					
					if(mesActual ==1) {
						saldoCuentaService.borrarPorMes(elMes);
						//saldoCentrosService.borrarPorMes(elMes);
						//saldoTerceroService.borrarPorMes(elMes);

						saldoCuentaService.remayorizarMoviblesEnero(elMes.getIdFiscal().getAnno().getElAnno(), elMes.getIdFiscal().getMes(), elMes.getInicio(), elMes.getFin());
						//saldoCentrosService.remayorizarTercerosEnero(elMes.getIdFiscal().getAnno().getElAnno(), elMes.getIdFiscal().getMes(), elMes.getInicio(), elMes.getFin());
						//saldoTerceroService.remayorizarCentrosEnero(elMes.getIdFiscal().getAnno().getElAnno(), elMes.getIdFiscal().getMes(), elMes.getInicio(), elMes.getFin());
					}else {
						System.out.println("ENTRE DONDE NO DEBIA");
						saldoCuentaService.borrarPorMes(elMes);
						//saldoCentrosService.borrarPorMes(elMes);
						//saldoTerceroService.borrarPorMes(elMes);

						saldoCuentaService.remayorizarMoviblesRestoAnno(elMes.getIdFiscal().getAnno().getElAnno(), elMes.getIdFiscal().getMes(), elMes.getInicio(), elMes.getFin());
						//saldoCentrosService.remayorizarTercerosEnero(elMes.getIdFiscal().getAnno().getElAnno(), elMes.getIdFiscal().getMes(), elMes.getInicio(), elMes.getFin());
						//saldoTerceroService.remayorizarCentrosEnero(elMes.getIdFiscal().getAnno().getElAnno(), elMes.getIdFiscal().getMes(), elMes.getInicio(), elMes.getFin());
					}

					ArrayList<ConfiguracionDePlanDeCuentas> niveles = (ArrayList<ConfiguracionDePlanDeCuentas>) confPlanDeCuentasService.findAll();

					DTOSaldoCuenta acumulador= new DTOSaldoCuenta();

					DTOSaldoCuenta saldoCuentaAnterior = new DTOSaldoCuenta();
					
					//Ciclo que acumula los saldos de las cuentas de un nivel dado en sus cuentas de nivel superior
					for (int i = niveles.size()-1; i > 0; i--) {
						
						System.out.println("!!!!!!!!!!!!!!!!!!!!!!El nivel es "+ niveles.get(i).getNivel() +" !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

						int longitud =0;
						
						for (int j = 0; j <= i; j++) {
							longitud+= niveles.get(j).getLongitud();
						}
						
						
						List<DTOSaldoCuenta> saldos = saldoCuentaService.remayorizacion2(elMes.getIdFiscal().getAnno().getElAnno(), elMes.getIdFiscal().getMes()
								, longitud)
								.stream()
								.map(result -> new DTOSaldoCuenta(((String) result[0]), (Integer) result[1], (Integer) result[2],
										(BigDecimal) result[3], (BigDecimal) result[4], (BigDecimal) result[5], (BigDecimal) result[6]))
								.collect(Collectors.toList());
						//List
						
						System.out.println("656565655656566556656556   AQUI ESTA LA CLAVE  -  EN LA LISTA DE CUENTAS QUE SE TYRAE 56565656565655656565566565656");
						System.err.println("//");
						saldos.stream().forEach(System.out::println);
						System.err.println("\\\\");
			
						int nivelAnterior = i -1;
						String cuentaAnterior="";
						String cuentaActual = "";
						String	raizAnterior = "";
						String raizActual = "";
						BigDecimal acumuladoSumaDebito = new BigDecimal(0);
						BigDecimal acumuladoSumaCredito =new BigDecimal(0);
						BigDecimal acumuladoSaldoDebito = new BigDecimal(0);
						BigDecimal acumuladoSaldoCredito = new BigDecimal(0);

System.out.println("ESTOY POR ENTRAR  AL FOR");
						for (DTOSaldoCuenta saldoCuenta : saldos) {//444444444444444444444444444444444444444444444444  PROCESO QUE SE REALIZA PARA CADA CUENTA  4444444444444444444444444444444444444444444444444444444
System.out.println("ACABO DE ENTRAR AL FOR");

							cuentaActual = saldoCuenta.getCod_cuenta();

							Cuenta cuenta = cuentaService.findById(cuentaActual).get();
							
							raizActual = cuenta.getCuentaSuperior();

							if ( raizAnterior.equals(raizActual) || raizAnterior.equals("") )  {
								System.out.println("44654654654654654654654646464     COMIENZO NIVEL O CUENTAS HERMANAS   -   ACUMULAR     4545454545445454545454545454545");
								if (raizAnterior.equals(""))
									raizAnterior = raizActual;

								//CREAR PROCESO PARA VERIFICAR QUE LA CUENTA RAIZ EXISTA
								//ACUMULAR
								acumuladoSumaDebito = acumuladoSumaDebito.add(saldoCuenta.getSuma_debito());
								acumuladoSumaCredito = acumuladoSumaCredito.add(saldoCuenta.getSuma_credito());
								acumuladoSaldoDebito = acumuladoSaldoDebito.add(saldoCuenta.getSaldo_debito());
								acumuladoSaldoCredito = acumuladoSaldoCredito.add(saldoCuenta.getSaldo_credito());

								//ANALIZO TRES ESCENARIOS
								//Si el largo de la cuenta anterior y actual son iguales, y y la Cuenta actual es igual a la raiz anterior, Guardar en la base de datos
							} else if (!raizAnterior.equals(raizActual) && raizAnterior != "" ){
								System.out.println("44654654654654654654654646464     CUENTAS HERMANAS PERO NO COMIENZO NIVEL   -   ASENTAR   4545454545445454545454545454545");



								//AQUI TENGO QUE CONOCER LA NATURALEZA DE LA CUENTA PARA CALCULAR EL SALDO DE LA CUENTA

								String naturaleza = cuentaService.findById(raizAnterior).get().getNatCuenta().toString();

								if (naturaleza.equals("DEBITO")) {
									if (acumuladoSaldoDebito.compareTo(acumuladoSaldoCredito) == 1) {
										acumuladoSaldoDebito = acumuladoSaldoDebito.subtract(acumuladoSaldoCredito);
										acumuladoSaldoCredito = new BigDecimal(0);
									}
									else {
										acumuladoSaldoDebito = new BigDecimal(0);
										acumuladoSaldoCredito = acumuladoSaldoCredito.subtract(acumuladoSaldoDebito);
									}

								}
								else {
									if (acumuladoSaldoDebito.compareTo(acumuladoSaldoCredito) == -1) {
										acumuladoSaldoDebito = new BigDecimal(0);
										acumuladoSaldoCredito = acumuladoSaldoCredito.subtract(acumuladoSaldoDebito);
									}
									else {
										acumuladoSaldoDebito = acumuladoSaldoDebito.subtract(acumuladoSaldoCredito);
										acumuladoSaldoCredito = new BigDecimal(0);
									}
								}

								//AQUI DEBO ACTUALIZAR LA BD CON EL NUEVO REGISTRO PARA LA CUENTA MAYOR A ACTUALIZAR

								acumulador = new DTOSaldoCuenta(raizAnterior, opAnno.get().getElAnno(), mesActual, acumuladoSumaDebito,acumuladoSumaCredito,acumuladoSaldoDebito,acumuladoSaldoCredito);
								SaldoCuenta nuevoSaldoCuenta = acumulador.convertir();

								saldoCuentaService.save(nuevoSaldoCuenta);
								System.err.println("????????????????????Insertado???????????????????????");
								System.err.println(nuevoSaldoCuenta);

								//System.out.println("raizActual : " + raizActual);
								//System.out.println("raizAnterior : " + raizAnterior);

								//CREAR PROCESO PARA VERIFICAR QUE LA CUENTA RAIZ EXISTA
								//ACUMULAR
								acumuladoSumaDebito = saldoCuenta.getSuma_debito();
								acumuladoSumaCredito = saldoCuenta.getSuma_credito();
								acumuladoSaldoDebito = saldoCuenta.getSaldo_debito();
								acumuladoSaldoCredito =saldoCuenta.getSaldo_credito();

								System.out.println("Cuenta Actual   : " + cuentaActual);
								System.out.println("raizActual : " + raizActual);
								System.out.println("raizAnterior : " + raizAnterior);

								System.out.println("Suma  Debito   : " + acumuladoSumaDebito);
								System.out.println("Suma  Credito  : " + acumuladoSumaCredito);
								System.out.println("Saldo Debito   : " + acumuladoSaldoDebito);
								System.out.println("Saldo Credito  : " + acumuladoSaldoCredito);

							} 

							//System.out.println("++++++++++++++++++++++++++++   VOY A COPIAR SALDOS    +++++++++++++++++++++++++++");
							cuentaAnterior=cuentaActual;
							System.err.println("////////////////////////////////VOY A COPIAR LA RAIZ ANTERIOR \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\");
							raizAnterior = raizActual;
							saldoCuentaAnterior = saldoCuenta;

						}//444444444444444444444444444444444444444444444444444  FIN DEL PROCESO QUE SE REALIZA PARA CADA CUENTA 4444444444444444444444444444444444444444444444444444444444444444444444444444444444

					System.out.println("hhhhhhhhhhhhhhhhh     VOY A GUARDAR LA ULTIMA CUENTA POR NIVEL   HHHHHHHHHHHHHHHHHHHHHHH");
						
						acumulador = new DTOSaldoCuenta(raizAnterior,idMes.getAnno().getElAnno(), idMes.getMes(),acumuladoSumaDebito,acumuladoSumaCredito,acumuladoSaldoDebito,acumuladoSaldoCredito);
						SaldoCuenta nuevoSaldoCuenta = acumulador.convertir();
						saldoCuentaService.save(nuevoSaldoCuenta);
						System.out.println("hhhhhhhhhhhhhhhhh     YA HE GUARDADO EL ULTIMO REGISTRO DE CADA NIVEL  HHHHHHHHHHHHHHHHHHHHHHH");

					}

					respuesta = new ResponseEntity<Remayorizacion>(HttpStatus.OK);
				}else {

					System.out.println("El mes no existe");
					respuesta = new ResponseEntity<Remayorizacion>(HttpStatus.NOT_FOUND);
					break;

				}
				mesActual++;
			}


		}else {

			System.out.println("El anno no existe o esta cerrado");
			
		}
		
		saldoCuentaService.borrarRegistrosCeros();
		//saldoTercerosService.borrarRegistrosCeros();
		//saldoCentrosService.borrarRegistrosCeros();

		return respuesta;
	}

}

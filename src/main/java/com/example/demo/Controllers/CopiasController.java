package com.example.demo.Controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;

@RestController
@RequestMapping("pro/Copias")
public class CopiasController {

	boolean hacerCopia = false;

	String rutaMySqlDump = "C:\\Archivos de programa\\MySQL\\MySQL Server 8.0\\bin\\mysqldump.exe";
	String rutaMySql = "C:\\Archivos de programa\\MySQL\\MySQL Server 8.0\\bin\\mysql.exe";
	String contrasenia ="sergio";
	String usuario = "root";
	String dataBase = "proyecto";





	//mysqldump.exe --opt --password=sergio --user=root --host=localhost --protocol=tcp --port=3307  --skip-triggers  --no-create-db --no-create-info  --databases conta --result-file="c:\copias\hacerCopiaPrueba.sql"

	@PutMapping("/generar")
	public ResponseEntity<String> hacerCopia(@RequestBody String rutaFile){
		//String rutaFile = "c:\\copias\\" + ruta + ".sql";
		System.out.println("La fecha recibida es : " + rutaFile);
		
		ResponseEntity<String> respuesta;
		try{
			//String cad = "\"" + rutaMySqlDump + "\" --opt --password=" + contrasenia + " --user=" + usuario + " " + dataBase + " > \"" + rutaFile +"\"\n";
			String cad = "MySqlDump.exe" + " --opt --password=" + contrasenia + " --user=" + usuario + " --host=localhost --protocol=tcp --port=3307  --create-options --add-drop-table --quote-names ---column-statistics --lock-tables --dump-date --disable-keys --tz-utc "
					+ " --add-locks --extendend-insert " + dataBase + " --result-file=\""  + rutaFile +"\"\n";


			System.out.println(cad);
			File fcopi = new File("copia_seguridad.bat");
			FileWriter fw = new FileWriter(fcopi);
			fw.write(cad, 0, cad.length());
			fw.close();
			Runtime.getRuntime().exec("copia_Seguridad.bat");
			
			respuesta = new ResponseEntity<String>(HttpStatus.ACCEPTED);

		}catch(Exception ex){
			respuesta = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);

			ex.printStackTrace();
		}
		return respuesta;
	}


	@PutMapping("/restaurar")
	public ResponseEntity<String> restaurarCopia(@RequestBody String ruta){
		ResponseEntity<String> respuesta;
		String rutaFile = "c:\\copias\\"+ruta;
		
		System.out.println("LA CADENA RECIBIDA : " + rutaFile);

		
		try{
			//String cad = "\"" + rutaMySql + "\" --opt --password=" + contrasenia + " --user=" + usuario + " " + dataBase + " < \"" + rutaFile +"\"\n";
			String cad = "MySqlDump.exe" + " --opt --password=" + contrasenia + " --user=" + usuario + " --host=localhost --protocol=tcp --port=3307  " + dataBase + " < \"" + rutaFile +"\"\n";

			System.out.println(cad);

			File fcopi = new File("copia_seguridad.bat");
			FileWriter fw = new FileWriter(fcopi);
			fw.write(cad, 0, cad.length());
			fw.close();
			Runtime.getRuntime().exec("copia_Seguridad.bat");
			respuesta = new ResponseEntity<String>(HttpStatus.ACCEPTED);
			System.out.println(respuesta);

		}catch(Exception ex){
			respuesta = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
			ex.printStackTrace();
		}
		return respuesta;
	}
}

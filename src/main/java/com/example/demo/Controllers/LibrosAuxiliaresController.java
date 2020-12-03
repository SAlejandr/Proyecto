package com.example.demo.Controllers;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.pojos.Mes;
import com.example.demo.model.pojos.MesFiscalId;
import com.example.demo.model.service.IMesService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import net.sf.jasperreports.engine.JasperReport;

@RestController
@RequestMapping("pro/reportes/librosAuxiliares")
public class LibrosAuxiliaresController {
	@Autowired private IMesService mesService;
	@PostMapping(value="/imprimir")
	public ResponseEntity<byte[]> imprimir(@RequestBody MesFiscalId id) {
		
		Mes mes = mesService.findById(id).get();
		crearInforme(mes.getInicio(), mes.getFin(), mes.getNombre());

		try(FileInputStream fis = new FileInputStream("LibrosAuxiliares-"+mes.getNombre()+".pdf");
				DataInputStream dis = new DataInputStream(fis)){
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("application/pdf"));
			byte[] pdf2bytes =  dis.readAllBytes();
			ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(pdf2bytes, headers, HttpStatus.OK);
		
			return response;
		}catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
	}	

	private void crearInforme(LocalDate fechaInicio, LocalDate fechaFin, String textoFecha) {
		Connection conn = null;
		String db ="jdbc:mysql://localhost:3307/proyecto?useSSL=false&serverTimezone=Europe/Madrid&allowPublicKeyRetrieval=true";
		String user = "root";
		String password = "sergio";
		try {
			Connection connection = DriverManager.getConnection(db, user, password);     		
			Map parametros = new HashMap();

			parametros.put("dia_inicio_mes", Date.from(fechaInicio.atStartOfDay(ZoneId.systemDefault()).toInstant()));
			parametros.put("dia_fin_mes", Date.from(fechaFin.atStartOfDay(ZoneId.systemDefault()).toInstant()));
			parametros.put("textoFecha", textoFecha);

			
			JasperReport report;
			report = JasperCompileManager.compileReport("LibrosAuxiliares.jrxml");

			System.out.println("*********************  YA COMPILE   ***********************************");

			JasperPrint print = JasperFillManager.fillReport(report, parametros, connection);
			System.out.println("*********************  LLENE EL DOCUMENTO   ***********************************");

			JasperExportManager.exportReportToPdfFile(print,"LibrosAuxiliares-"+textoFecha+".pdf");
			System.out.println("*********************  YA EXPORTE A PDF  ***********************************");

			//JasperViewer viewer = new JasperViewer(print);
			//viewer.setVisible(true);
			System.out.println("*********************  YA HE LANZADO EL JASPERVIEWER   ***********************************");


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private byte[] copiarPDF() throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/pdf"));
		String filename = "LibrosAuxiliares.pdf";

		headers.add("content-disposition", "attachment;filename=" + filename);

		//headers.setContentDispositionFormData(filename, filename);
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		Path path = Paths.get("LibrosAuxiliares.pdf");
		System.out.println(path.toString());
		byte[] pdf1Bytes = Files.readAllBytes(path);
		return pdf1Bytes;
	}

}

package com.jpmarino.jplanetas.data;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.jpmarino.jplanetas.modelo.Clima;
import com.jpmarino.jplanetas.modelo.SistemaSolar;

/**
* Job que genera y carga en la base registros climáticos para 10 años.
* 
* @author Juan Pablo Marino
* @version 1.0
*/
public class ClimaJob {
	final static Logger logger = Logger.getLogger(ClimaJob.class);
	
	/**
	 * Método que calcula el clima de diez años y los almacena en la base de datos.
	 * Previo a la generación de dichos registros, borra la base de datos.
	 *
	 */
	public static void generar10años() {
		
		logger.info("Limpiando datos...");
		ClimaDAO.borrarBase();
		logger.info("Generando datos...");
		
		int años = 10;
		int diasEnUnAño = 365;

		long initTime = System.currentTimeMillis();

		List<RegistroClimatico> registrosClimaticos = new ArrayList<RegistroClimatico>();
		for (int dia = 1; dia <= años * diasEnUnAño; ++dia) {
			logger.info("dia = " + dia);
			Clima clima = SistemaSolar.getSistemaSolarBasico().clima(dia);
			logger.info("clima = " + clima);
			registrosClimaticos.add(new RegistroClimatico(dia, clima.toString()));
			logger.info("-----");
		}
		ClimaDAO.agregar(registrosClimaticos);
		logger.debug("Tiempo de procesamiento: " + (System.currentTimeMillis() - initTime));
	}
}

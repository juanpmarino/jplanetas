package com.jpmarino.jplanetas.web;

import org.apache.log4j.Logger;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
import com.jpmarino.jplanetas.data.ClimaDAO;
import com.jpmarino.jplanetas.data.ClimaJob;
import com.jpmarino.jplanetas.data.RegistroClimatico;

/**
* API REST para conocer el clima accediendo a los datos almacenados por el DAO.
* 
* @author Juan Pablo Marino
* @version 1.0
*/
@Api(name = "clima")
public class ClimaAPI {
	final static Logger logger = Logger.getLogger(ClimaJob.class);
	
	@ApiMethod(name = "clima", httpMethod = ApiMethod.HttpMethod.GET)
	public RegistroClimatico clima(@Named("dia") String dia) throws Exception {
		//Clima clima = SistemaSolar.getInstance().clima(new Integer(dia));
		//RegistroClimatico response = new RegistroClimatico(new Integer(dia), clima);
		logger.info("Consultando base de datos...");
		try {
			RegistroClimatico registroClimatico = ClimaDAO.clima(new Integer(dia));
			if (registroClimatico == null) {
				throw new Exception("Error: registro climático no encontrado.");
			}
			return registroClimatico;
		} catch (NumberFormatException e) {
			throw new Exception("Error: el parámetro día debe ser un valor entero.");
		}
		//return response;
	}
	
	@ApiMethod(name = "ultimo", httpMethod = ApiMethod.HttpMethod.GET) 
	public RegistroClimatico ultimo() {
		return ClimaDAO.ultimo();
	}
	
	@ApiMethod(name = "init", httpMethod = ApiMethod.HttpMethod.GET)
	public void init() {
		ClimaJob.generar10años();
	}

	@ApiMethod(name = "delete", httpMethod = ApiMethod.HttpMethod.GET)
	public void delete() {
		ClimaDAO.borrarBase();
	}
}

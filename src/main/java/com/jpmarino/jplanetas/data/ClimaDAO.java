package com.jpmarino.jplanetas.data;

import java.util.List;

import org.apache.log4j.Logger;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.VoidWork;

/**
 * Implementación de DAO de registros climáticos para acceder a datastore de
 * Google.
 * 
 * @author Juan Pablo Marino
 * @version 1.0
 */
public class ClimaDAO {
	final static Logger logger = Logger.getLogger(ClimaDAO.class);

	/**
	 * Guarda en la base de datos de Google App Engine un registro climático.
	 *
	 * @param un
	 *            registro climático
	 */
	public static void agregar(RegistroClimatico registroClimatico) {
		ObjectifyService.ofy().save().entity(registroClimatico).now();
	}

	/**
	 * Guarda en la base de datos de Google App Engine una lista de registros
	 * climáticos.
	 *
	 * @param una
	 *            lista de registros climáticos
	 */
	public static void agregar(final List<RegistroClimatico> registrosClimaticos) {
		int totalElementos = registrosClimaticos.size() - 1;
		logger.debug("totalElementos: " + totalElementos);
		int cantElementos = 20;
		int n = 0;
		for (int i = 0; i <= totalElementos; i+=cantElementos) {
			logger.debug("i: " + i);
			logger.debug("i+cantElementos: " + (i+cantElementos));
			if (i+cantElementos > totalElementos) {
				n = totalElementos + 1;
				logger.debug("n = " + totalElementos);
			} else {
				n = i + cantElementos;
				logger.debug("n = " + n);
			}
			List<RegistroClimatico> listaAux = registrosClimaticos.subList(i, n);	
			agregarBajoNivel(listaAux);
		}
	}
	
	private static void agregarBajoNivel(final List<RegistroClimatico> registrosClimaticos) {
		ObjectifyService.ofy().transact(new VoidWork() {
			public void vrun() {
				for (RegistroClimatico registroClimatico : registrosClimaticos) {
					ObjectifyService.ofy().save().entity(registroClimatico);
				}
			}
		});
	}

	/**
	 * Lista los primeros cinco registros climáticos.
	 *
	 * @return lista de los primeros registros climáticos almacenados
	 */
	public static RegistroClimatico ultimo() {
		List<RegistroClimatico> registrosClimaticos = ObjectifyService.ofy()
				.load().type(RegistroClimatico.class).order("-dia").limit(1)
				.list();
		if (registrosClimaticos == null) {
			return null;
		}
		if (registrosClimaticos.size() == 0) {
			return null;
		}
		return registrosClimaticos.iterator().next();
	}

	/**
	 * Devuelve un registro climático previamente almacenado en la base de datos
	 * para un día en particular.
	 *
	 * @param día
	 * @return registro climático
	 */
	public static RegistroClimatico clima(int dia) {
		logger.info("Buscando registro para el día: " + dia);
		List<RegistroClimatico> registrosClimaticos = ObjectifyService.ofy()
				.load().type(RegistroClimatico.class).filter("dia = ", dia)
				.list();
		logger.info("Registro encontrado.");
		for (RegistroClimatico regClimatico : registrosClimaticos) {
			if (regClimatico.dia.equals(dia)) {
				regClimatico.id = null;
				return regClimatico;
			}
		}
		logger.error("No se encontró ningún registro.");
		return null;
	}

	/**
	 * Borra todos los registros climáticos almacenados en la base de datos.
	 *
	 */
	public static void borrarBase() {
		List<RegistroClimatico> registrosClimaticos = ObjectifyService.ofy()
				.load().type(RegistroClimatico.class).list();
		ObjectifyService.ofy().delete().entities(registrosClimaticos);
	}

}

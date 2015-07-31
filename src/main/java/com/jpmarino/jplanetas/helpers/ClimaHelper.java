package com.jpmarino.jplanetas.helpers;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
* Clase helper que contiene métodos auxiliares para facilitar tareas básicas.
* 
* @author Juan Pablo Marino
* @version 1.0
*/
public class ClimaHelper {
	
	/**
	 * Redondeo de valores double.
	 *
	 * @param value de tipo double que se quiere redondear.
	 * @param places cantidad de decimales que se quieren tener en el valor a retornar.
	 * @return value redondeado en base a places.
	 */
	public static double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();

		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}
}

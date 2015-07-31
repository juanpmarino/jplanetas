package com.jpmarino.jplanetas.data;

/**
* Reporte del clima que responde primer punto del ejercicio.
* Este es generado en {@link SistemaSolar} en el método reporte.
* 
* @author Juan Pablo Marino
* @version 1.0
*/
public class ReporteClima {
	private final int periodosSequia;
	private final int periodosLluvia;
	private final int periodosOptimos;
	private final int diaLluviaMaxima;
	
	/**
	 * Constructor de un reporte del clima.
	 *
	 * @param número de períodos de sequía.
	 * @param número de períodos de lluvia.
	 * @param número de períodos óptimos.
	 * @param día en el que se registra la lluvia con mayor intensidad.
	 */
	public ReporteClima(int periodosSequia, int periodosLluvia,
			int periodosOptimos, int diaLluviaMaxima) {
		super();
		this.periodosSequia = periodosSequia;
		this.periodosLluvia = periodosLluvia;
		this.periodosOptimos = periodosOptimos;
		this.diaLluviaMaxima = diaLluviaMaxima;
	}
	
	public int getPeriodosSequia() {
		return periodosSequia;
	}
	public int getPeriodosLluvia() {
		return periodosLluvia;
	}
	public int getPeriodosOptimos() {
		return periodosOptimos;
	}
	public int getDiaLluviaMaxima() {
		return diaLluviaMaxima;
	}
	
}

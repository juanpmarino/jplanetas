package com.jpmarino.jplanetas.respuestas;

import com.jpmarino.jplanetas.data.ReporteClima;
import com.jpmarino.jplanetas.modelo.SistemaSolar;

/**
* Proceso para responder el primer punto del ejercicio.
* 
* @author Juan Pablo Marino
* @version 1.0
*/
public class RespuestaEjercicio1 {
	public static void main(String[] args) {
		int años = 10;
		int diasEnUnAño = 365;
		final int diaFinal = años * diasEnUnAño;

		ReporteClima reporte = SistemaSolar.getSistemaSolarBasico().reporte(
				diaFinal);
		System.out.println();
		System.out
				.println("1. Periodos sequia: " + reporte.getPeriodosSequia());
		System.out.println("2.a. Periodos lluvia: "
				+ reporte.getPeriodosLluvia());
		System.out.println("2.b. Dia lluvia maxima: "
				+ reporte.getDiaLluviaMaxima());
		System.out.println("3. Periodos optimos: "
				+ reporte.getPeriodosOptimos());
		System.out.println();
		System.out
				.println("Se asume como periodo a un conjunto de días consecutivos con mismo clima.");
	}
}

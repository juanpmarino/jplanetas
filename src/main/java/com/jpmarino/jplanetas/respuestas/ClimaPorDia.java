package com.jpmarino.jplanetas.respuestas;

import com.jpmarino.jplanetas.modelo.Clima;
import com.jpmarino.jplanetas.modelo.SistemaSolar;

/**
* Proceso para listar el clima de cada día en los 10 años.
* 
* @author Juan Pablo Marino
* @version 1.0
*/
public class ClimaPorDia {

	public static void main(String[] args) {
		int años = 10;
		int diasEnUnAño = 365;

		int diasSequia = 0;
		int diasLluvia = 0;
		int diasOptimos = 0;
		int diasIndeterminados = 0;
		
		System.out.println("dia,clima");
		long initTime = System.currentTimeMillis();
		for (int dia = 1; dia <= años * diasEnUnAño; ++dia) {
			System.out.print(dia);
			Clima clima = SistemaSolar.getSistemaSolarBasico().clima(dia);
			
			switch (clima) {
			case Sequia:
				++diasSequia;
				break;
			case Lluvia:
				++diasLluvia;
				break;
			case Optimo:
				++diasOptimos;
				dia = años*diasEnUnAño;
				break;
			case Indeterminado:
				++diasIndeterminados;
				break;
			}
			
			System.out.println("," + clima);
		}
		System.out.println("Tiempo procesado: "
				+ (System.currentTimeMillis() - initTime));
		System.out.println("Dias sequia: " + diasSequia);
		System.out.println("Dias lluvia: " + diasLluvia);
		System.out.println("Dias optimos: " + diasOptimos);
		System.out.println("Dias indeterminados: " + diasIndeterminados);
	}

}

package com.jpmarino.jplanetas.calculo;

import java.util.List;

import com.jpmarino.jplanetas.modelo.Clima;
import com.jpmarino.jplanetas.modelo.Ubicacion;

/**
 * Clase abstracta que representa una estrategia para calcular el clima en
 * sistemas solares. Se pretende con esta clase encapsular la lógica de cálculo
 * del clima desacoplándola del sistema solar.
 * 
 * @author Juan Pablo Marino
 * @version 1.0
 */
public abstract class ClimaStrategy {

	/**
	 * Calcula el perímetro del sistema solar en base a las ubicaciones
	 * enviadas.
	 *
	 * @param ubicaciones
	 *            lista de ubicaciones que sirven como vértices del polígono que
	 *            compone el sistema solar en un momento dado
	 * @return perímetro
	 */
	public abstract double perimetro(List<Ubicacion> ubicaciones);

	/**
	 * Calcula el clima en base a las ubicaciones de los planetas.
	 *
	 * @param ubicaciones
	 *            de los planetas en un momento dado
	 * @return clima
	 */
	public abstract Clima clima(List<Ubicacion> ubicaciones);

	/**
	 * Para tratar de evitar contar con más de una instancia de la estrategia para tres
	 * planetas se almacena internamente.
	 */
	private static ClimaStrategy tresPlanetasClimaStrategy = null;

	/**
	 * Se retorna una instancia para cálculo de clima con tres planetas.
	 *
	 * @return una estrategia para calcular el clima con tres planetas.
	 */
	public static ClimaStrategy getTresPlanetasClimaStrategy() {
		if (tresPlanetasClimaStrategy == null) {
			tresPlanetasClimaStrategy = new TresPlanetasClimaStrategy();
		}
		return tresPlanetasClimaStrategy;
	}
}

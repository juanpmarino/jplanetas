package com.jpmarino.jplanetas.modelo;

import java.awt.Point;

import math.geom2d.Vector2D;

import com.jpmarino.jplanetas.helpers.ClimaHelper;

/**
 * Esta clase encapsula una ubicación mediante un valor representativo de la
 * distancia al sol y con el grado con respecto al eje horizontal (surge de
 * multiplicar la velocidad -en grados- por la cantidad de días.
 * También expone métodos para poder contar con las coordenadas con respecto a los ejes x e y.
 * 
 * @author Juan Pablo Marino
 * @version 1.0
 */
public class Ubicacion implements Comparable<Ubicacion> {
	private double distanciaAlSol;
	private double grados;

	public Ubicacion(double distanciaAlSol, double grados) {
		this.distanciaAlSol = distanciaAlSol;
		this.grados = grados;
	}

	/**
	 * Factory method para crear una ubicación si sólo se conocen sus coordenadas.
	 *
	 * @param x es la coordenada con respecto al eje x.
	 * @param y es la coordenada con respecto al eje y.
	 * @return una ubicación.
	 */
	public static Ubicacion crearUbicacionConCoordenadas(double x, double y) {
		Vector2D v = new Vector2D(x, y);
		double r = v.norm();
		double ang = Math.toDegrees(v.angle());
		return new Ubicacion(r, ang);
	}

	public double getDistanciaAlSol() {
		return distanciaAlSol;
	}

	public void setDistanciaAlSol(int distanciaAlSol) {
		this.distanciaAlSol = distanciaAlSol;
	}

	public double getGrados() {
		return grados;
	}

	public void setGrados(double grados) {
		this.grados = grados;
	}

	public double x() {
		return ClimaHelper.round(Math.cos(Math.toRadians(grados))
				* distanciaAlSol, 2);
	}

	public double y() {
		return ClimaHelper.round(Math.sin(Math.toRadians(grados))
				* distanciaAlSol, 2);
	}

	public double distancia(Ubicacion ubicacion) {
		double x1 = this.x();
		double y1 = this.y();

		Point p1 = new Point((int) x1, (int) y1);

		double x2 = ubicacion.x();
		double y2 = ubicacion.y();

		Point p2 = new Point((int) x2, (int) y2);

		return p1.distance(p2);
	}

	@Override
	public int compareTo(Ubicacion o) {
		return (new Double(this.grados)).compareTo(o.getGrados());
	}
}

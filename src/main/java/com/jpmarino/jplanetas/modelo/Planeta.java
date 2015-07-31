package com.jpmarino.jplanetas.modelo;

/**
* Esta clase modela un planeta en el sistema.
* 
* @author Juan Pablo Marino
* @version 1.0
*/
public class Planeta {
	private String nombre;
	private double velocidad; // en grados por día
	private int distanciaAlSol;
	private Sentido sentido; // horario o antihorario

	public Planeta(String nombre, double velocidad, int distanciaAlSol,
			Sentido sentido) {
		this.nombre = nombre;
		this.velocidad = velocidad;
		this.distanciaAlSol = distanciaAlSol;
		this.sentido = sentido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(double velocidad) {
		this.velocidad = velocidad;
	}

	public int getDistanciaAlSol() {
		return distanciaAlSol;
	}

	public void setDistanciaAlSol(int distanciaAlSol) {
		this.distanciaAlSol = distanciaAlSol;
	}

	/**
	 * Calcula la posición del planeta de acuerdo a velocidad y día.
	 *
	 * @param día a tener en cuenta en cálculo de posición.
	 * @return posición (en grados) donde se encuentra el planeta en el día solicitado.
	 */
	public double posicion(int dia) {
		if (sentido.equals(Sentido.HORARIO)) {
			return (360 - (dia * velocidad) % 360);
		}
		return (dia * velocidad) % 360;
	}

	public boolean equals(Object other) {
		if (this == other)
			return true;
		if (!(other instanceof Planeta))
			return false;
		Planeta otherA = (Planeta) other;
		return this.distanciaAlSol == otherA.getDistanciaAlSol();
	}

	public int hashCode() {
		int hash = 1;
		hash = hash * 31 + new Integer(distanciaAlSol).hashCode();
		return hash;
	}

}

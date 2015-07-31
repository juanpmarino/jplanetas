package com.jpmarino.jplanetas.calculo;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import math.geom2d.Point2D;
import math.geom2d.polygon.SimplePolygon2D;

import org.apache.commons.math3.geometry.euclidean.twod.Line;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.log4j.Logger;

import com.jpmarino.jplanetas.modelo.Clima;
import com.jpmarino.jplanetas.modelo.Ubicacion;

/**
 * Estrategia para calcular el clima en sistemas solares de 3 planetas.
 * 
 * @author Juan Pablo Marino
 * @version 1.0
 */
public class TresPlanetasClimaStrategy extends ClimaStrategy {

	final static Logger logger = Logger
			.getLogger(TresPlanetasClimaStrategy.class);

	/**
	 * Calcula el clima en base a las ubicaciones de los planetas.
	 *
	 * @param ubicaciones
	 *            de los planetas en un momento dado
	 * @return clima
	 */
	public Clima clima(List<Ubicacion> ubicaciones) {
		if (this.esSequia(ubicaciones)) {
			logger.debug("Sequía.");
			return Clima.Sequia;
		}
		if (this.esOptimo(ubicaciones)) {
			logger.debug("Óptimo.");
			return Clima.Optimo;
		}
		if (this.esLluvia(ubicaciones)) {
			logger.debug("Lluvia.");
			return Clima.Lluvia;
		}
		logger.debug("Desconocido.");
		return Clima.Indeterminado;
	}

	/**
	 * Valida si hay sequía en base a las ubicaciones de los planetas.
	 *
	 * @param ubicaciones
	 *            de los planetas en un momento dado
	 * @return true si el clima es sequía, false si el clima es otro.
	 */
	public boolean esSequia(List<Ubicacion> ubicaciones) {
		Iterator<Ubicacion> it = ubicaciones.iterator();
		Ubicacion u1 = it.next();
		Vector2D v1 = new Vector2D(u1.x(), u1.y());
		Ubicacion u2 = it.next();
		Vector2D v2 = new Vector2D(u2.x(), u2.y());
		Line linea = new Line(v1, v2, 0.01);

		Ubicacion u3 = it.next();
		Vector2D v3 = new Vector2D(u3.x(), u3.y());

		Vector2D v0 = new Vector2D(0, 0);

		return linea.contains(v3) && linea.contains(v0);

	}

	/**
	 * Valida si el clima es lluvia en base a las ubicaciones de los planetas.
	 *
	 * @param ubicaciones
	 *            de los planetas en un momento dado
	 * @return true si el clima es lluvia, false si el clima es otro.
	 */
	public boolean esLluvia(List<Ubicacion> ubicaciones) {
		Collections.sort(ubicaciones);
		Iterator<Ubicacion> iterator = ubicaciones.iterator();
		Ubicacion ubicacion = iterator.next();
		double x1 = ubicacion.x();
		double y1 = ubicacion.y();
		ubicacion = iterator.next();
		double x2 = ubicacion.x();
		double y2 = ubicacion.y();
		ubicacion = iterator.next();
		double x3 = ubicacion.x();
		double y3 = ubicacion.y();

		// If you have just triangle (3 points) in plane, you can compute
		// determinant from matrix, where lines are coordinates of points (3rd
		// coordinate is 1). If determinant is > 0, points are in CCW order. If
		// not, you can swith for example last two points and you will get CCW
		// order.
		// If you have points A, B, C, then your matrix looks like:
		//
		// |xA, yA, 1|
		// |xB, yB, 1|
		// |xC, yC, 1|
		// Determinant is: xA*yB + xB*yC + xC*yA - yB*xC - yC*xA - yA*xB. Then
		// you can compare it with zero. If it's > 0, return points A, B, C, if
		// it isn't, return A, C, B.

		double determinante = x1 * y2 + x2 * y3 + x3 * y1 - y2 * x3 - y3 * x1
				- y1 * x2;

		if (determinante < 0) {
			double xAux = x2;
			double yAux = y2;
			x2 = x3;
			y2 = y3;
			x3 = xAux;
			y3 = yAux;
		}

		return esLluviaBajoNivel(x1, y1, x2, y2, x3, y3);
	}

	/**
	 * Valida si el clima es lluvia con las coordenadas de los tres puntos del
	 * triángulo formado por los planetas en un momento dado.
	 *
	 * @param coordenadas x e y de las ubicaciones
	 *            de los planetas en un momento dado
	 * @return true si el clima es lluvia, false si el clima es otro.
	 */
	public boolean esLluviaBajoNivel(double x1, double y1, double x2,
			double y2, double x3, double y3) {
		// Previamente había probado con la clase Polygon,
		// sin embargo esta clase no maneja coordenadas en double
		// sino que usa int, por lo que se pierde precisión.
		// Path2D triangle = new Path2D.Double();
		// if ((y1 == 0 && y2 == 0) || (y1 == 0 && y3 == 0)
		// || (y2 == 0 && y3 == 0)) {
		// Esto es debido al siguiente criterio del contains utilizado:
		// it lies exactly on a horizontal boundary segment and the space
		// immediately adjacent to the point in the increasing Y direction
		// is inside the boundary.
		// y1 = Math.abs(y1) * (-1);
		// y2 = Math.abs(y2) * (-1);
		// y3 = Math.abs(y3) * (-1);
		// }
		// for (int i = 1; i <= 3; ++i) {
		// if (i == 1) {
		// triangle.moveTo(x1, y1);
		// } else if (i == 2) {
		// triangle.lineTo(x2, y2);
		// } else {
		// triangle.lineTo(x3, y3);
		// triangle.closePath();
		// }
		// }
		// return triangle.contains(0.0, 0.0);
		// Vector2D p1 = new Vector2D(x1, y1);
		// Vector2D p2 = new Vector2D(x2, y2);
		// Vector2D p3 = new Vector2D(x3, y3);
		// Vector2D p0 = new Vector2D(0, 0);
		// PolygonsSet ps = new PolygonsSet(1,p1, p2, p3,p1);
		// PolygonsSet pp0 = new PolygonsSet(0.1, p0);
		// return ps.checkPoint(Vector2D.ZERO).equals(Region.Location.INSIDE);
		SimplePolygon2D triangle = new SimplePolygon2D();
		triangle.addVertex(new Point2D(x1, y1));
		triangle.addVertex(new Point2D(x2, y2));
		triangle.addVertex(new Point2D(x3, y3));
		return triangle.contains(0, 0);
	}

	/**
	 * Calcula el perímetro en base a las ubicaciones de los planetas.
	 *
	 * @param ubicaciones de los planetas en un momento dado
	 * @return perímetro
	 */
	public double perimetro(List<Ubicacion> ubicaciones) {
		Iterator<Ubicacion> iterator = ubicaciones.iterator();
		Ubicacion ubicacion1 = iterator.next();
		Ubicacion ubicacion2 = iterator.next();
		Ubicacion ubicacion3 = iterator.next();

		double perimetro = ubicacion1.distancia(ubicacion2)
				+ ubicacion1.distancia(ubicacion3)
				+ ubicacion2.distancia(ubicacion3);
		return perimetro;
	}

	/**
	 * Valida si el clima es óptimo en base a las ubicaciones de los planetas.
	 *
	 * @param ubicaciones de los planetas en un momento dado
	 * @return true si el clima es óptimo, false si el clima es otro.
	 */
	public boolean esOptimo(List<Ubicacion> ubicaciones) {
		Iterator<Ubicacion> it = ubicaciones.iterator();
		Ubicacion u1 = it.next();
		Ubicacion u2 = it.next();
		Ubicacion u3 = it.next();

		return esOptimoBajoNivel(u1.x(), u1.y(), u2.x(), u2.y(), u3.x(), u3.y());
	}

	/**
	 * Valida si el clima es óptimo haciendo uso de las coordenadas de los tres planetas.
	 *
	 * @param coordenadas x e y de los planetas
	 * @return true si el clima es óptimo, false si el clima es otro.
	 */
	public boolean esOptimoBajoNivel(double x1, double y1, double x2,
			double y2, double x3, double y3) {
		Vector2D v1 = new Vector2D(x1, y1);
		Vector2D v2 = new Vector2D(x2, y2);
		Vector2D v3 = new Vector2D(x3, y3);
		Vector2D v0 = new Vector2D(0, 0);

		Line linea = new Line(v1, v2, 0.01);

		return linea.contains(v3) && !linea.contains(v0);
	}

}

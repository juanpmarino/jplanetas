package com.jpmarino.jplanetas.modelo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.jpmarino.jplanetas.calculo.ClimaStrategy;
import com.jpmarino.jplanetas.data.ReporteClima;

/**
 * Esta clase representa a un sistema solar, conteniendo a los planetas que
 * forman parte del mismo.
 * También cuenta con un strategy para determinar el clima en un momento dado.
 * 
 * @author Juan Pablo Marino
 * @version 1.0
 */
public class SistemaSolar {
	private Set<Planeta> planetas = null;
	final static Logger logger = Logger.getLogger(SistemaSolar.class);
	private ClimaStrategy climaStrategy;

	public SistemaSolar() {
		planetas = new HashSet<Planeta>();
		this.climaStrategy = ClimaStrategy.getTresPlanetasClimaStrategy();
		// BasicConfigurator.configure();
	}

	/**
	 * Factory method para obtener un sistema solar como el descripto en el ejercicio.
	 *
	 * @return un sistema solar de tres planetas
	 */
	public static SistemaSolar getSistemaSolarBasico() {
		SistemaSolar sistemaSolar = new SistemaSolar();
		sistemaSolar.agregar(new Planeta("Ferengi", 1, 500, Sentido.HORARIO));
		sistemaSolar
				.agregar(new Planeta("Betasoide", 3, 2000, Sentido.HORARIO));
		sistemaSolar.agregar(new Planeta("Vulcano", 5, 1000,
				Sentido.ANTIHORARIO));
		return sistemaSolar;
	}

	public ReporteClima reporte(int diaFinal) {
		return this.reporte(1, diaFinal);
	}

	public Set<Planeta> getPlanetas() {
		return this.planetas;
	}

	/**
	 * Genera un reporte para un período.
	 *
	 * @param diaInicial es el día de inicio del período.
	 * @param diaFinal es el día de fin del período.
	 * @return reporte del clima para dicho período.
	 */
	public ReporteClima reporte(int diaInicial, int diaFinal) {
		logger.info("Generando reporte desde el día " + diaInicial
				+ " hasta el día " + diaFinal);
		int dia = diaInicial;
		int periodosSequia = 0;
		int periodosLluvia = 0;
		int periodosOptimos = 0;
		int diaLluviaMaxima = -1;
		double perimetroMaximo = -1;

		Clima clima = this.clima(dia);
		while (dia <= diaFinal) {

			Clima climaAnt = clima;
			while (dia <= diaFinal && climaAnt == clima) {

				if (clima.equals(Clima.Lluvia)) {
					double perimetro = this.climaStrategy.perimetro(this
							.ubicaciones(dia));
					if (perimetro > perimetroMaximo) {
						perimetroMaximo = perimetro;
						diaLluviaMaxima = dia;
						logger.debug("Perímetro máximo encontrado: "
								+ perimetro);
						logger.debug("Día del perímetro máximo encontrado: "
								+ dia);
					}
				}

				++dia;
				if (dia <= diaFinal) {
					clima = this.clima(dia);
				}
			}

			if ((climaAnt != clima) || (dia > diaFinal)) {
				switch (climaAnt) {
				case Sequia:
					++periodosSequia;
					break;
				case Lluvia:
					++periodosLluvia;
					break;
				case Optimo:
					++periodosOptimos;
					break;
				case Indeterminado:
					break;
				}
			}

		}
		return new ReporteClima(periodosSequia, periodosLluvia,
				periodosOptimos, diaLluviaMaxima);
	}

	public void agregar(Planeta planeta) {
		planetas.add(planeta);
	}

	/**
	 * Cálculo de las ubicaciones de los planetas del sistema solar en un día dado.
	 *
	 * @param dia es el día para determinar dónde está cada planeta.
	 * @return lista con las ubicaciones de los planetas en dicho día.
	 */
	public List<Ubicacion> ubicaciones(int dia) {
		List<Ubicacion> ubicaciones = new ArrayList<Ubicacion>();
		for (Planeta planeta : planetas) {
			ubicaciones.add(new Ubicacion(planeta.getDistanciaAlSol(), planeta
					.posicion(dia)));
		}
		return ubicaciones;
	}

	/**
	 * Cálculo del clima de acuerdo a las ubicaciones en un día dado.
	 * Se hace uso del strategy.
	 *
	 * @param dia es el día para determinar el clima.
	 * @return clima.
	 */
	public Clima clima(int dia) {
		List<Ubicacion> ubicaciones = this.ubicaciones(dia);
		return climaStrategy.clima(ubicaciones);
	}

}

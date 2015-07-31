package com.jpmarino.jplanetas.modelo;

import java.util.ArrayList;
import java.util.List;

import math.geom2d.Vector2D;

import com.jpmarino.jplanetas.calculo.ClimaStrategy;
import com.jpmarino.jplanetas.calculo.TresPlanetasClimaStrategy;
import com.jpmarino.jplanetas.data.ReporteClima;

import junit.framework.TestCase;

/**
* Pruebas unitarias y no tan unitarias.
* 
* @author Juan Pablo Marino
* @version 1.0
*/
public class ClimaTest extends TestCase {

	public void testPlanetasAntihorariosSequia1() {
		SistemaSolar sistemaSolar = new SistemaSolar();
		sistemaSolar
				.agregar(new Planeta("planeta1", 0, 1, Sentido.ANTIHORARIO));
		sistemaSolar
				.agregar(new Planeta("planeta2", 0, 2, Sentido.ANTIHORARIO));
		sistemaSolar.agregar(new Planeta("planeta3", 180, 3,
				Sentido.ANTIHORARIO));

		assertEquals(Clima.Sequia, sistemaSolar.clima(1));
	}

	public void testPlanetasAntihorariosSequia2() {
		SistemaSolar sistemaSolar = new SistemaSolar();
		sistemaSolar
				.agregar(new Planeta("planeta1", 0, 1, Sentido.ANTIHORARIO));
		sistemaSolar.agregar(new Planeta("planeta2", 180, 2,
				Sentido.ANTIHORARIO));
		sistemaSolar
				.agregar(new Planeta("planeta3", 0, 3, Sentido.ANTIHORARIO));

		assertEquals(Clima.Sequia, sistemaSolar.clima(1));
	}

	public void testPlanetasAntihorariosSequia3() {
		SistemaSolar sistemaSolar = new SistemaSolar();
		sistemaSolar.agregar(new Planeta("planeta1", 180, 1,
				Sentido.ANTIHORARIO));
		sistemaSolar
				.agregar(new Planeta("planeta2", 0, 2, Sentido.ANTIHORARIO));
		sistemaSolar
				.agregar(new Planeta("planeta3", 0, 3, Sentido.ANTIHORARIO));

		assertEquals(Clima.Sequia, sistemaSolar.clima(1));
	}

	public void testPlanetasAntihorariosSequia4() {
		SistemaSolar sistemaSolar = new SistemaSolar();
		sistemaSolar
				.agregar(new Planeta("planeta1", 90, 1, Sentido.ANTIHORARIO));
		sistemaSolar.agregar(new Planeta("planeta2", 270, 2,
				Sentido.ANTIHORARIO));
		sistemaSolar.agregar(new Planeta("planeta3", 270, 3,
				Sentido.ANTIHORARIO));

		assertEquals(Clima.Sequia, sistemaSolar.clima(1));
	}

	public void testPlanetasAntihorariosSequia5() {
		SistemaSolar sistemaSolar = new SistemaSolar();
		sistemaSolar.agregar(new Planeta("planeta1", 270, 1,
				Sentido.ANTIHORARIO));
		sistemaSolar.agregar(new Planeta("planeta2", 270, 2,
				Sentido.ANTIHORARIO));
		sistemaSolar.agregar(new Planeta("planeta3", 270, 3,
				Sentido.ANTIHORARIO));

		assertEquals(Clima.Sequia, sistemaSolar.clima(1));
	}

	public void testPlanetasAntihorariosSequia6() {
		SistemaSolar sistemaSolar = new SistemaSolar();
		sistemaSolar.agregar(new Planeta("planeta1", 270, 1,
				Sentido.ANTIHORARIO));
		sistemaSolar
				.agregar(new Planeta("planeta2", 90, 2, Sentido.ANTIHORARIO));
		sistemaSolar
				.agregar(new Planeta("planeta3", 90, 3, Sentido.ANTIHORARIO));

		assertEquals(Clima.Sequia, sistemaSolar.clima(1));
	}

	public void testPlanetasHorariosSequia1() {
		SistemaSolar sistemaSolar = new SistemaSolar();
		sistemaSolar.agregar(new Planeta("planeta1", 0, 1, Sentido.HORARIO));
		sistemaSolar.agregar(new Planeta("planeta2", 0, 2, Sentido.HORARIO));
		sistemaSolar.agregar(new Planeta("planeta3", 180, 3, Sentido.HORARIO));

		assertEquals(Clima.Sequia, sistemaSolar.clima(1));
	}

	public void testPlanetasHorariosOptimo1() {
		SistemaSolar sistemaSolar = new SistemaSolar();
		sistemaSolar.agregar(new Planeta("planeta1", 0.0, 1,
				Sentido.ANTIHORARIO));
		sistemaSolar.agregar(new Planeta("planeta2", 60.0, 2,
				Sentido.ANTIHORARIO));
		sistemaSolar.agregar(new Planeta("planeta3", Math.toDegrees(Math
				.acos(1.0 / 3)), 3, Sentido.ANTIHORARIO));

		assertEquals(Clima.Optimo, sistemaSolar.clima(1));
	}

	public void testLogicaSequia() {
		List<Ubicacion> ubicaciones = new ArrayList<Ubicacion>();
		ubicaciones.add(new Ubicacion(1, 0));
		ubicaciones.add(new Ubicacion(2, 0));
		ubicaciones.add(new Ubicacion(3, 0));

		assertTrue((new TresPlanetasClimaStrategy()).esSequia(ubicaciones));
	}

	// public void testLogicaOptimo() {
	// List<Ubicacion> ubicaciones = new ArrayList<Ubicacion>();
	// ubicaciones.add(new Ubicacion(1, 0));
	// ubicaciones.add(new Ubicacion(2, 60));
	// ubicaciones.add(new Ubicacion(2, 60));
	// // ubicaciones.add(new Ubicacion(3,Math.toDegrees(1.23095942)));
	//
	// boolean resultado = (new SistemaSolar()).esOptimo(ubicaciones);
	//
	// assertTrue(resultado);
	// }
	//
	// public void testLogicaOptimo2() {
	// List<Ubicacion> ubicaciones = new ArrayList<Ubicacion>();
	// ubicaciones.add(new Ubicacion(1, 0));
	// ubicaciones.add(new Ubicacion(2, 60));
	// ubicaciones.add(new Ubicacion(3, Math.toDegrees(Math.acos(1.0 / 3))));
	//
	// boolean resultado = (new SistemaSolar()).esOptimo(ubicaciones);
	//
	// assertTrue(resultado);
	// }

	public void testLogicaOptimo() {
		double x1 = 1;
		double y1 = 0;

		double x2 = 1;
		double y2 = Math.sqrt(3);

		double x3 = 1;
		double y3 = 3 / 2;

		boolean resultado = (new TresPlanetasClimaStrategy()).esOptimoBajoNivel(x1, y1, x2,
				y2, x3, y3);

		assertTrue(resultado);
	}

	public void testIntegral1() {
		SistemaSolar sistemaSolar = new SistemaSolar();
		sistemaSolar.agregar(new Planeta("planeta1", 10.0, 1,
				Sentido.ANTIHORARIO));
		sistemaSolar.agregar(new Planeta("planeta2", 10.0, 2,
				Sentido.ANTIHORARIO));
		sistemaSolar.agregar(new Planeta("planeta3", 10.0, 3,
				Sentido.ANTIHORARIO));

		ReporteClima reporte = sistemaSolar.reporte(1, 5);

		assertEquals(1, reporte.getPeriodosSequia());
		assertEquals(0, reporte.getPeriodosLluvia());
		assertEquals(0, reporte.getPeriodosOptimos());
	}

	public void testIntegral2() {
		SistemaSolar sistemaSolar = new SistemaSolar();
		sistemaSolar.agregar(new Planeta("planeta1", 0.0, 1,
				Sentido.ANTIHORARIO));
		sistemaSolar.agregar(new Planeta("planeta2", 90.0, 2,
				Sentido.ANTIHORARIO));
		sistemaSolar.agregar(new Planeta("planeta3", 180.0, 3,
				Sentido.ANTIHORARIO));

		ReporteClima reporte = sistemaSolar.reporte(1, 3);

		assertEquals(1, reporte.getPeriodosSequia());
		assertEquals(2, reporte.getPeriodosLluvia());
		assertEquals(0, reporte.getPeriodosOptimos());
		
		sistemaSolar = new SistemaSolar();
		sistemaSolar.agregar(new Planeta("planeta1", 90.0, 1,
				Sentido.ANTIHORARIO));
		sistemaSolar.agregar(new Planeta("planeta2", 190.0, 2,
				Sentido.ANTIHORARIO));
		sistemaSolar.agregar(new Planeta("planeta3", 280.0, 3,
				Sentido.ANTIHORARIO));

		reporte = sistemaSolar.reporte(1, 3);

		assertEquals(0, reporte.getPeriodosSequia());
		assertEquals(1, reporte.getPeriodosLluvia());
		assertEquals(0, reporte.getPeriodosOptimos());
	}

	public void testLluvia0() {
		TresPlanetasClimaStrategy tresPlanetasClimaStrategy = new TresPlanetasClimaStrategy();

		double x1 = 1;
		double y1 = 0;
		double x2 = -2;
		double y2 = 0;
		double x3 = 0;
		double y3 = 3;

		Ubicacion u1 = Ubicacion.crearUbicacionConCoordenadas(x1, y1);
		Ubicacion u2 = Ubicacion.crearUbicacionConCoordenadas(x2, y2);
		Ubicacion u3 = Ubicacion.crearUbicacionConCoordenadas(x3, y3);
		
		List<Ubicacion> ubicaciones = new ArrayList<Ubicacion>();
		ubicaciones.add(u1);
		ubicaciones.add(u2);
		ubicaciones.add(u3);
		
		assertTrue(tresPlanetasClimaStrategy.esLluvia(ubicaciones));
	}

	public void testLluvia00() {
		TresPlanetasClimaStrategy tresPlanetasClimaStrategy = new TresPlanetasClimaStrategy();

		double x1 = -3;
		double y1 = 0;
		double x2 = 0;
		double y2 = 2;
		double x3 = 1;
		double y3 = 0;
		
		Ubicacion u1 = Ubicacion.crearUbicacionConCoordenadas(x1, y1);
		Ubicacion u2 = Ubicacion.crearUbicacionConCoordenadas(x2, y2);
		Ubicacion u3 = Ubicacion.crearUbicacionConCoordenadas(x3, y3);
		
		List<Ubicacion> ubicaciones = new ArrayList<Ubicacion>();
		ubicaciones.add(u1);
		ubicaciones.add(u2);
		ubicaciones.add(u3);
		
		assertTrue(tresPlanetasClimaStrategy.esLluvia(ubicaciones));
	}
	
	public void testLluvia0000() {
		TresPlanetasClimaStrategy tresPlanetasClimaStrategy = new TresPlanetasClimaStrategy();
		
		double x1 = -1; 
		double y1 = 0;
		double x2 = 0; 
		double y2 = 1;
		double x3 = 1; 
		double y3 = 0;
		
		Ubicacion u1 = Ubicacion.crearUbicacionConCoordenadas(x1, y1);
		Ubicacion u2 = Ubicacion.crearUbicacionConCoordenadas(x2, y2);
		Ubicacion u3 = Ubicacion.crearUbicacionConCoordenadas(x3, y3);
		
		List<Ubicacion> ubicaciones = new ArrayList<Ubicacion>();
		ubicaciones.add(u1);
		ubicaciones.add(u2);
		ubicaciones.add(u3);
		
		assertTrue(tresPlanetasClimaStrategy.esLluvia(ubicaciones));
		
		y2 = -1;
		Ubicacion u4 = Ubicacion.crearUbicacionConCoordenadas(x2, y2);
		
		ubicaciones = new ArrayList<Ubicacion>();
		ubicaciones.add(u1);
		ubicaciones.add(u2);
		ubicaciones.add(u4);
		
		assertTrue(tresPlanetasClimaStrategy.esLluvia(ubicaciones));
	}
	
	public void testLluvia000() {
		TresPlanetasClimaStrategy tresPlanetasClimaStrategy = new TresPlanetasClimaStrategy();

		double x1 = -3;
		double y1 = 0;
		double x2 = 0;
		double y2 = -2;
		double x3 = 1;
		double y3 = 0;
		
		Ubicacion u1 = Ubicacion.crearUbicacionConCoordenadas(x1, y1);
		Ubicacion u2 = Ubicacion.crearUbicacionConCoordenadas(x2, y2);
		Ubicacion u3 = Ubicacion.crearUbicacionConCoordenadas(x3, y3);
		
		List<Ubicacion> ubicaciones = new ArrayList<Ubicacion>();
		ubicaciones.add(u1);
		ubicaciones.add(u2);
		ubicaciones.add(u3);
		
		assertTrue(tresPlanetasClimaStrategy.esLluvia(ubicaciones));
	}

	public void testLluvia1() {
		TresPlanetasClimaStrategy tresPlanetasClimaStrategy = new TresPlanetasClimaStrategy();

		double x1 = 1;
		double y1 = 0;
		double x2 = -2;
		double y2 = 0;
		double x3 = 0;
		double y3 = -3;
		
		Ubicacion u1 = Ubicacion.crearUbicacionConCoordenadas(x1, y1);
		Ubicacion u2 = Ubicacion.crearUbicacionConCoordenadas(x2, y2);
		Ubicacion u3 = Ubicacion.crearUbicacionConCoordenadas(x3, y3);
		
		List<Ubicacion> ubicaciones = new ArrayList<Ubicacion>();
		ubicaciones.add(u1);
		ubicaciones.add(u2);
		ubicaciones.add(u3);
		
		assertTrue(tresPlanetasClimaStrategy.esLluvia(ubicaciones));

//		assertTrue(tresPlanetasClimaStrategy.esLluviaBajoNivel(x1, y1, x2, y2, x3, y3));

	}

	public void testLluvia2() {
		TresPlanetasClimaStrategy tresPlanetasClimaStrategy = new TresPlanetasClimaStrategy();
		
		double x1 = 1;
		double y1 = 1;
		double x2 = -2;
		double y2 = 1;
		double x3 = 0;
		double y3 = -3;
		
		Ubicacion u1 = Ubicacion.crearUbicacionConCoordenadas(x1, y1);
		Ubicacion u2 = Ubicacion.crearUbicacionConCoordenadas(x2, y2);
		Ubicacion u3 = Ubicacion.crearUbicacionConCoordenadas(x3, y3);
		
		List<Ubicacion> ubicaciones = new ArrayList<Ubicacion>();
		ubicaciones.add(u1);
		ubicaciones.add(u2);
		ubicaciones.add(u3);
		
		assertTrue(tresPlanetasClimaStrategy.esLluvia(ubicaciones));

//		assertTrue(tresPlanetasClimaStrategy.esLluviaBajoNivel(x1, y1, x2, y2, x3, y3));
	}

	public void testLluvia3() {
		TresPlanetasClimaStrategy tresPlanetasClimaStrategy = new TresPlanetasClimaStrategy();

		double x1 = 1;
		double y1 = 0;
		double x2 = 0;
		double y2 = 2;
		double x3 = -3;
		double y3 = 0;
		
		Ubicacion u1 = Ubicacion.crearUbicacionConCoordenadas(x1, y1);
		Ubicacion u2 = Ubicacion.crearUbicacionConCoordenadas(x2, y2);
		Ubicacion u3 = Ubicacion.crearUbicacionConCoordenadas(x3, y3);
		
		List<Ubicacion> ubicaciones = new ArrayList<Ubicacion>();
		ubicaciones.add(u1);
		ubicaciones.add(u2);
		ubicaciones.add(u3);
		
		assertTrue(tresPlanetasClimaStrategy.esLluvia(ubicaciones));

//		assertTrue(tresPlanetasClimaStrategy.esLluviaBajoNivel(x1, y1, x2, y2, x3, y3));
	}

	public void testLluvia4() {
		TresPlanetasClimaStrategy tresPlanetasClimaStrategy = new TresPlanetasClimaStrategy();

		double x1 = 1;
		double y1 = 1;
		double x2 = -1;
		double y2 = -1;
		double x3 = -2;
		double y3 = 2;
		
		Ubicacion u1 = Ubicacion.crearUbicacionConCoordenadas(x1, y1);
		Ubicacion u2 = Ubicacion.crearUbicacionConCoordenadas(x2, y2);
		Ubicacion u3 = Ubicacion.crearUbicacionConCoordenadas(x3, y3);
		
		List<Ubicacion> ubicaciones = new ArrayList<Ubicacion>();
		ubicaciones.add(u1);
		ubicaciones.add(u2);
		ubicaciones.add(u3);
		
		assertTrue(tresPlanetasClimaStrategy.esLluvia(ubicaciones));

//		assertTrue(tresPlanetasClimaStrategy.esLluviaBajoNivel(x1, y1, x2, y2, x3, y3));
	}

	public void testLluvia5() {
		TresPlanetasClimaStrategy tresPlanetasClimaStrategy = new TresPlanetasClimaStrategy();

		double x1 = 1;
		double y1 = 1;
		double x2 = 0;
		double y2 = 0;
		double x3 = -2;
		double y3 = 2;
		
		Ubicacion u1 = Ubicacion.crearUbicacionConCoordenadas(x1, y1);
		Ubicacion u2 = Ubicacion.crearUbicacionConCoordenadas(x2, y2);
		Ubicacion u3 = Ubicacion.crearUbicacionConCoordenadas(x3, y3);
		
		List<Ubicacion> ubicaciones = new ArrayList<Ubicacion>();
		ubicaciones.add(u1);
		ubicaciones.add(u2);
		ubicaciones.add(u3);
		
		assertTrue(tresPlanetasClimaStrategy.esLluvia(ubicaciones));

//		assertTrue(tresPlanetasClimaStrategy.esLluviaBajoNivel(x1, y1, x2, y2, x3, y3));
	}

	public void testLluvia6() {
		TresPlanetasClimaStrategy tresPlanetasClimaStrategy = new TresPlanetasClimaStrategy();

		double x1 = -1;
		double y1 = -1;
		double x2 = 3;
		double y2 = 3;
		double x3 = -2;
		double y3 = 2;
		
		Ubicacion u1 = Ubicacion.crearUbicacionConCoordenadas(x1, y1);
		Ubicacion u2 = Ubicacion.crearUbicacionConCoordenadas(x2, y2);
		Ubicacion u3 = Ubicacion.crearUbicacionConCoordenadas(x3, y3);
		
		List<Ubicacion> ubicaciones = new ArrayList<Ubicacion>();
		ubicaciones.add(u1);
		ubicaciones.add(u2);
		ubicaciones.add(u3);
		
		assertTrue(tresPlanetasClimaStrategy.esLluvia(ubicaciones));

//		assertTrue(tresPlanetasClimaStrategy.esLluviaBajoNivel(x1, y1, x2, y2, x3, y3));
	}

	public void testLluvia7() {
		TresPlanetasClimaStrategy tresPlanetasClimaStrategy = new TresPlanetasClimaStrategy();

		double x1 = 1;
		double y1 = 0;
		double x2 = -3;
		double y2 = 0;
		double x3 = 2;
		double y3 = 2;
		
		Ubicacion u1 = Ubicacion.crearUbicacionConCoordenadas(x1, y1);
		Ubicacion u2 = Ubicacion.crearUbicacionConCoordenadas(x2, y2);
		Ubicacion u3 = Ubicacion.crearUbicacionConCoordenadas(x3, y3);
		
		List<Ubicacion> ubicaciones = new ArrayList<Ubicacion>();
		ubicaciones.add(u1);
		ubicaciones.add(u2);
		ubicaciones.add(u3);
		
		assertTrue(tresPlanetasClimaStrategy.esLluvia(ubicaciones));

//		assertTrue(tresPlanetasClimaStrategy.esLluviaBajoNivel(x1, y1, x2, y2, x3, y3));
	}

	public void testLluvia8() {
		TresPlanetasClimaStrategy tresPlanetasClimaStrategy = new TresPlanetasClimaStrategy();

		double x1 = 1;
		double y1 = 0;
		double x2 = -3;
		double y2 = 0;
		double x3 = 2;
		double y3 = -2;
		
		Ubicacion u1 = Ubicacion.crearUbicacionConCoordenadas(x1, y1);
		Ubicacion u2 = Ubicacion.crearUbicacionConCoordenadas(x2, y2);
		Ubicacion u3 = Ubicacion.crearUbicacionConCoordenadas(x3, y3);
		
		List<Ubicacion> ubicaciones = new ArrayList<Ubicacion>();
		ubicaciones.add(u1);
		ubicaciones.add(u2);
		ubicaciones.add(u3);

		assertTrue(tresPlanetasClimaStrategy.esLluvia(ubicaciones));
//		assertTrue(tresPlanetasClimaStrategy.esLluviaBajoNivel(x1, y1, x2, y2, x3, y3));
	}

	public void testLluvia9() {
		TresPlanetasClimaStrategy tresPlanetasClimaStrategy = new TresPlanetasClimaStrategy();

		double x1 = 2;
		double y1 = -2;
		double x2 = -2;
		double y2 = -2;
		double x3 = 0;
		double y3 = 3;
		
		Ubicacion u1 = Ubicacion.crearUbicacionConCoordenadas(x1, y1);
		Ubicacion u2 = Ubicacion.crearUbicacionConCoordenadas(x2, y2);
		Ubicacion u3 = Ubicacion.crearUbicacionConCoordenadas(x3, y3);
		
		List<Ubicacion> ubicaciones = new ArrayList<Ubicacion>();
		ubicaciones.add(u1);
		ubicaciones.add(u2);
		ubicaciones.add(u3);
		
		assertTrue(tresPlanetasClimaStrategy.esLluvia(ubicaciones));
		
		//assertTrue(tresPlanetasClimaStrategy.esLluviaBajoNivel(x2, y2, x3, y3, x1, y1));
//		assertTrue(tresPlanetasClimaStrategy.esLluviaBajoNivel(x2, y2, x1, y1, x3, y3));
//		assertTrue(tresPlanetasClimaStrategy.esLluviaBajoNivel(x3, y3, x2, y2, x1, y1));
//		assertTrue(tresPlanetasClimaStrategy.esLluviaBajoNivel(x1, y1, x3, y3, x2, y2));
		//assertTrue(tresPlanetasClimaStrategy.esLluviaBajoNivel(x1, y1, x2, y2, x3, y3));
	}
	
	public void testComparator() {
		Ubicacion u1 = new Ubicacion(1, 10);
		Ubicacion u2 = new Ubicacion(1, 20);
		assertTrue(u1.compareTo(u2) < 0);
	}
	
	public void testLluvia9b() {
		Vector2D v = new Vector2D(2,2);
		assertEquals(Math.sqrt(8), v.norm());
		assertEquals(45.0, Math.toDegrees(v.angle()));
		
		TresPlanetasClimaStrategy tresPlanetasClimaStrategy = new TresPlanetasClimaStrategy();
		
		double x1 = 2;
		double y1 = -2;
		double x2 = -2;
		double y2 = -2;
		double x3 = 0;
		double y3 = 3;
		
		Ubicacion u1 = Ubicacion.crearUbicacionConCoordenadas(x1, y1);
		Ubicacion u2 = Ubicacion.crearUbicacionConCoordenadas(x2, y2);
		Ubicacion u3 = Ubicacion.crearUbicacionConCoordenadas(x3, y3);
		
		List<Ubicacion> ubicaciones = new ArrayList<Ubicacion>();
		ubicaciones.add(u1);
		ubicaciones.add(u2);
		ubicaciones.add(u3);

		assertTrue(tresPlanetasClimaStrategy.esLluvia(ubicaciones));
	}

	public void testLluvia10() {
		TresPlanetasClimaStrategy tresPlanetasClimaStrategy = new TresPlanetasClimaStrategy();

		double x1 = 2;
		double y1 = 2;
		double x2 = -2;
		double y2 = 2;
		double x3 = 0;
		double y3 = -3;
		
		Ubicacion u1 = Ubicacion.crearUbicacionConCoordenadas(x1, y1);
		Ubicacion u2 = Ubicacion.crearUbicacionConCoordenadas(x2, y2);
		Ubicacion u3 = Ubicacion.crearUbicacionConCoordenadas(x3, y3);
		
		List<Ubicacion> ubicaciones = new ArrayList<Ubicacion>();
		ubicaciones.add(u1);
		ubicaciones.add(u2);
		ubicaciones.add(u3);
		
		assertTrue(tresPlanetasClimaStrategy.esLluvia(ubicaciones));
	}

	public void testLluvia11() {
		TresPlanetasClimaStrategy tresPlanetasClimaStrategy = new TresPlanetasClimaStrategy();

		double x1 = -2;
		double y1 = 2;
		double x2 = -2;
		double y2 = -2;
		double x3 = 3;
		double y3 = 0;
		
		Ubicacion u1 = Ubicacion.crearUbicacionConCoordenadas(x1, y1);
		Ubicacion u2 = Ubicacion.crearUbicacionConCoordenadas(x2, y2);
		Ubicacion u3 = Ubicacion.crearUbicacionConCoordenadas(x3, y3);
		
		List<Ubicacion> ubicaciones = new ArrayList<Ubicacion>();
		ubicaciones.add(u1);
		ubicaciones.add(u2);
		ubicaciones.add(u3);
		
		assertTrue(tresPlanetasClimaStrategy.esLluvia(ubicaciones));
	}

	public void testLluvia12() {
		TresPlanetasClimaStrategy tresPlanetasClimaStrategy = new TresPlanetasClimaStrategy();

		double x1 = 2;
		double y1 = 2;
		double x2 = 2;
		double y2 = -2;
		double x3 = -3;
		double y3 = 0;
		
		Ubicacion u1 = Ubicacion.crearUbicacionConCoordenadas(x1, y1);
		Ubicacion u2 = Ubicacion.crearUbicacionConCoordenadas(x2, y2);
		Ubicacion u3 = Ubicacion.crearUbicacionConCoordenadas(x3, y3);
		
		List<Ubicacion> ubicaciones = new ArrayList<Ubicacion>();
		ubicaciones.add(u1);
		ubicaciones.add(u2);
		ubicaciones.add(u3);
		
		assertTrue(tresPlanetasClimaStrategy.esLluvia(ubicaciones));
	}

	public void testNoLlueve1() {
		TresPlanetasClimaStrategy tresPlanetasClimaStrategy = new TresPlanetasClimaStrategy();

		double x1 = -2;
		double y1 = 2;
		double x2 = -2;
		double y2 = 0;
		double x3 = 3;
		double y3 = 2;
		
		Ubicacion u1 = Ubicacion.crearUbicacionConCoordenadas(x1, y1);
		Ubicacion u2 = Ubicacion.crearUbicacionConCoordenadas(x2, y2);
		Ubicacion u3 = Ubicacion.crearUbicacionConCoordenadas(x3, y3);
		
		List<Ubicacion> ubicaciones = new ArrayList<Ubicacion>();
		ubicaciones.add(u1);
		ubicaciones.add(u2);
		ubicaciones.add(u3);
		
		assertFalse(tresPlanetasClimaStrategy.esLluvia(ubicaciones));
	}

	public void testNoLlueve2() {
		TresPlanetasClimaStrategy tresPlanetasClimaStrategy = new TresPlanetasClimaStrategy();

		double x1 = 2;
		double y1 = 2;
		double x2 = 2;
		double y2 = 0;
		double x3 = -3;
		double y3 = 2;
		
		Ubicacion u1 = Ubicacion.crearUbicacionConCoordenadas(x1, y1);
		Ubicacion u2 = Ubicacion.crearUbicacionConCoordenadas(x2, y2);
		Ubicacion u3 = Ubicacion.crearUbicacionConCoordenadas(x3, y3);
		
		List<Ubicacion> ubicaciones = new ArrayList<Ubicacion>();
		ubicaciones.add(u1);
		ubicaciones.add(u2);
		ubicaciones.add(u3);
		
		assertFalse(tresPlanetasClimaStrategy.esLluvia(ubicaciones));
	}

	public void testNoLlueve3() {
		TresPlanetasClimaStrategy tresPlanetasClimaStrategy = new TresPlanetasClimaStrategy();

		double x1 = -2;
		double y1 = 2;
		double x2 = 2;
		double y2 = 2;
		double x3 = 0;
		double y3 = 5;
		
		Ubicacion u1 = Ubicacion.crearUbicacionConCoordenadas(x1, y1);
		Ubicacion u2 = Ubicacion.crearUbicacionConCoordenadas(x2, y2);
		Ubicacion u3 = Ubicacion.crearUbicacionConCoordenadas(x3, y3);
		
		List<Ubicacion> ubicaciones = new ArrayList<Ubicacion>();
		ubicaciones.add(u1);
		ubicaciones.add(u2);
		ubicaciones.add(u3);
		
		assertFalse(tresPlanetasClimaStrategy.esLluvia(ubicaciones));
	}

	public void testNoLlueve4() {
		TresPlanetasClimaStrategy tresPlanetasClimaStrategy = new TresPlanetasClimaStrategy();

		double x1 = -2;
		double y1 = 2;
		double x2 = 2;
		double y2 = 2;
		double x3 = 0;
		double y3 = 1;
		
		Ubicacion u1 = Ubicacion.crearUbicacionConCoordenadas(x1, y1);
		Ubicacion u2 = Ubicacion.crearUbicacionConCoordenadas(x2, y2);
		Ubicacion u3 = Ubicacion.crearUbicacionConCoordenadas(x3, y3);
		
		List<Ubicacion> ubicaciones = new ArrayList<Ubicacion>();
		ubicaciones.add(u1);
		ubicaciones.add(u2);
		ubicaciones.add(u3);
		
		assertFalse(tresPlanetasClimaStrategy.esLluvia(ubicaciones));
	}

	public void testNoLlueve5() {
		TresPlanetasClimaStrategy tresPlanetasClimaStrategy = new TresPlanetasClimaStrategy();

		double x1 = -2;
		double y1 = 2;
		double x2 = 2;
		double y2 = 2;
		double x3 = 0;
		double y3 = 0;
		
		Ubicacion u1 = Ubicacion.crearUbicacionConCoordenadas(x1, y1);
		Ubicacion u2 = Ubicacion.crearUbicacionConCoordenadas(x2, y2);
		Ubicacion u3 = Ubicacion.crearUbicacionConCoordenadas(x3, y3);
		
		List<Ubicacion> ubicaciones = new ArrayList<Ubicacion>();
		ubicaciones.add(u1);
		ubicaciones.add(u2);
		ubicaciones.add(u3);
		
		assertTrue(tresPlanetasClimaStrategy.esLluvia(ubicaciones));
	}

	public void testLluvia14() {
		TresPlanetasClimaStrategy tresPlanetasClimaStrategy = new TresPlanetasClimaStrategy();

		double x1 = 0;
		double y1 = 0;
		double x2 = -2;
		double y2 = 2;
		double x3 = 2;
		double y3 = 2;
		
		Ubicacion u1 = Ubicacion.crearUbicacionConCoordenadas(x1, y1);
		Ubicacion u2 = Ubicacion.crearUbicacionConCoordenadas(x2, y2);
		Ubicacion u3 = Ubicacion.crearUbicacionConCoordenadas(x3, y3);
		
		List<Ubicacion> ubicaciones = new ArrayList<Ubicacion>();
		ubicaciones.add(u1);
		ubicaciones.add(u2);
		ubicaciones.add(u3);
		
		assertTrue(tresPlanetasClimaStrategy.esLluvia(ubicaciones));
	}

	public void testNoLlueve7() {
		TresPlanetasClimaStrategy tresPlanetasClimaStrategy = new TresPlanetasClimaStrategy();

		double x1 = -2;
		double y1 = -2;
		double x2 = -2;
		double y2 = -4;
		double x3 = 2;
		double y3 = -5;
		
		Ubicacion u1 = Ubicacion.crearUbicacionConCoordenadas(x1, y1);
		Ubicacion u2 = Ubicacion.crearUbicacionConCoordenadas(x2, y2);
		Ubicacion u3 = Ubicacion.crearUbicacionConCoordenadas(x3, y3);
		
		List<Ubicacion> ubicaciones = new ArrayList<Ubicacion>();
		ubicaciones.add(u1);
		ubicaciones.add(u2);
		ubicaciones.add(u3);
		
		assertFalse(tresPlanetasClimaStrategy.esLluvia(ubicaciones));
	}

	public void testNoLlueve8() {
		TresPlanetasClimaStrategy tresPlanetasClimaStrategy = new TresPlanetasClimaStrategy();

		double x1 = -2;
		double y1 = -2;
		double x2 = 2;
		double y2 = 0;
		double x3 = 2;
		double y3 = -4;
		
		Ubicacion u1 = Ubicacion.crearUbicacionConCoordenadas(x1, y1);
		Ubicacion u2 = Ubicacion.crearUbicacionConCoordenadas(x2, y2);
		Ubicacion u3 = Ubicacion.crearUbicacionConCoordenadas(x3, y3);
		
		List<Ubicacion> ubicaciones = new ArrayList<Ubicacion>();
		ubicaciones.add(u1);
		ubicaciones.add(u2);
		ubicaciones.add(u3);
		
		assertFalse(tresPlanetasClimaStrategy.esLluvia(ubicaciones));
	}

	public void testNoLlueve9() {
		TresPlanetasClimaStrategy tresPlanetasClimaStrategy = new TresPlanetasClimaStrategy();

		double x1 = 0;
		double y1 = -2;
		double x2 = 2;
		double y2 = -6;
		double x3 = -2;
		double y3 = -6;
		
		Ubicacion u1 = Ubicacion.crearUbicacionConCoordenadas(x1, y1);
		Ubicacion u2 = Ubicacion.crearUbicacionConCoordenadas(x2, y2);
		Ubicacion u3 = Ubicacion.crearUbicacionConCoordenadas(x3, y3);
		
		List<Ubicacion> ubicaciones = new ArrayList<Ubicacion>();
		ubicaciones.add(u1);
		ubicaciones.add(u2);
		ubicaciones.add(u3);
		
		assertFalse(tresPlanetasClimaStrategy.esLluvia(ubicaciones));
	}

	public void testNoLlueve10() {
		TresPlanetasClimaStrategy tresPlanetasClimaStrategy = new TresPlanetasClimaStrategy();

		double x1 = -2;
		double y1 = -2;
		double x2 = 2;
		double y2 = -2;
		double x3 = 0;
		double y3 = -6;
		
		Ubicacion u1 = Ubicacion.crearUbicacionConCoordenadas(x1, y1);
		Ubicacion u2 = Ubicacion.crearUbicacionConCoordenadas(x2, y2);
		Ubicacion u3 = Ubicacion.crearUbicacionConCoordenadas(x3, y3);
		
		List<Ubicacion> ubicaciones = new ArrayList<Ubicacion>();
		ubicaciones.add(u1);
		ubicaciones.add(u2);
		ubicaciones.add(u3);
		
		assertFalse(tresPlanetasClimaStrategy.esLluvia(ubicaciones));
	}
	
	public void testLluvia13() {
		TresPlanetasClimaStrategy tresPlanetasClimaStrategy = new TresPlanetasClimaStrategy();
		// Triángulos inclinados respecto al eje.
		double x1 = -2;
		double y1 = -2;
		double x2 = 2;
		double y2 = 2;
		double x3 = -3;
		double y3 = 2;
		
		Ubicacion u1 = Ubicacion.crearUbicacionConCoordenadas(x1, y1);
		Ubicacion u2 = Ubicacion.crearUbicacionConCoordenadas(x2, y2);
		Ubicacion u3 = Ubicacion.crearUbicacionConCoordenadas(x3, y3);
		
		List<Ubicacion> ubicaciones = new ArrayList<Ubicacion>();
		ubicaciones.add(u1);
		ubicaciones.add(u2);
		ubicaciones.add(u3);
		
		assertTrue(tresPlanetasClimaStrategy.esLluvia(ubicaciones));
	}

	public void testIntegral3() {
		SistemaSolar sistemaSolar = new SistemaSolar();
		sistemaSolar.agregar(new Planeta("planeta1", 0.0, 1,
				Sentido.ANTIHORARIO));
		sistemaSolar.agregar(new Planeta("planeta2", 60.0, 2,
				Sentido.ANTIHORARIO));
		sistemaSolar.agregar(new Planeta("planeta3", Math.toDegrees(Math
				.acos(1.0 / 3)), 3, Sentido.ANTIHORARIO));

		ReporteClima reporte = sistemaSolar.reporte(1, 2);

		assertEquals(0, reporte.getPeriodosSequia());
		assertEquals(0, reporte.getPeriodosLluvia());
		assertEquals(1, reporte.getPeriodosOptimos());
	}

	public void testIntegral4() {
		SistemaSolar sistemaSolar = new SistemaSolar();
		sistemaSolar.agregar(new Planeta("planeta1", 0.0, 1,
				Sentido.ANTIHORARIO));
		sistemaSolar.agregar(new Planeta("planeta2", 90.0, 2,
				Sentido.ANTIHORARIO));
		sistemaSolar.agregar(new Planeta("planeta3", 180.0, 3,
				Sentido.ANTIHORARIO));

		ReporteClima reporte = sistemaSolar.reporte(1, 3);

		assertEquals(2, reporte.getPeriodosLluvia());
		assertEquals(1, reporte.getDiaLluviaMaxima());
	}

	public void testIntegral5() {
		SistemaSolar sistemaSolar = new SistemaSolar();
		sistemaSolar.agregar(new Planeta("planeta1", 10.0, 1, Sentido.HORARIO));
		sistemaSolar.agregar(new Planeta("planeta2", 10.0, 2, Sentido.HORARIO));
		sistemaSolar.agregar(new Planeta("planeta3", 10.0, 3, Sentido.HORARIO));

		ReporteClima reporte = sistemaSolar.reporte(1, 5);

		assertEquals(1, reporte.getPeriodosSequia());
		assertEquals(0, reporte.getPeriodosLluvia());
		assertEquals(0, reporte.getPeriodosOptimos());
	}

	public void testIntegral6() {
		SistemaSolar sistemaSolar = new SistemaSolar();
		sistemaSolar.agregar(new Planeta("planeta1", 0.0, 1, Sentido.HORARIO));
		sistemaSolar.agregar(new Planeta("planeta2", 90.0, 2, Sentido.HORARIO));
		sistemaSolar
				.agregar(new Planeta("planeta3", 180.0, 3, Sentido.HORARIO));

		ReporteClima reporte = sistemaSolar.reporte(1, 3);

		assertEquals(1, reporte.getPeriodosSequia());
		assertEquals(2, reporte.getPeriodosLluvia());
		assertEquals(0, reporte.getPeriodosOptimos());
	}

	public void testIntegral7() {
		SistemaSolar sistemaSolar = new SistemaSolar();
		sistemaSolar.agregar(new Planeta("planeta1", 0.0, 1, Sentido.HORARIO));
		sistemaSolar.agregar(new Planeta("planeta2", 60.0, 2, Sentido.HORARIO));
		sistemaSolar.agregar(new Planeta("planeta3", Math.toDegrees(Math
				.acos(1.0 / 3)), 3, Sentido.HORARIO));

		ReporteClima reporte = sistemaSolar.reporte(1, 4);

		assertEquals(0, reporte.getPeriodosSequia());
		assertEquals(1, reporte.getPeriodosLluvia());
		assertEquals(1, reporte.getPeriodosOptimos());
	}

	public void testIntegral8() {
		SistemaSolar sistemaSolar = new SistemaSolar();
		sistemaSolar.agregar(new Planeta("planeta1", 30.0, 1,
				Sentido.ANTIHORARIO));
		sistemaSolar.agregar(new Planeta("planeta2", 60.0, 2, Sentido.HORARIO));
		sistemaSolar.agregar(new Planeta("planeta3", 30.0, 3, Sentido.HORARIO));

		ReporteClima reporte = sistemaSolar.reporte(1, 4);

		assertEquals(0, reporte.getPeriodosSequia());
		assertEquals(1, reporte.getPeriodosLluvia());
		assertEquals(0, reporte.getPeriodosOptimos());
	}
	
	public void testIntegral9() {
		SistemaSolar sistemaSolar = new SistemaSolar();
		sistemaSolar.agregar(new Planeta("planeta1", 30.0, 1,
				Sentido.ANTIHORARIO));
		sistemaSolar.agregar(new Planeta("planeta2", 60.0, 2, Sentido.HORARIO));
		sistemaSolar.agregar(new Planeta("planeta3", 30.0, 3, Sentido.HORARIO));

		ReporteClima reporte = sistemaSolar.reporte(2, 2);

		assertEquals(1, reporte.getPeriodosLluvia());
	}

	public void testPerimetro1() {
		List<Ubicacion> ubicaciones = new ArrayList<Ubicacion>();
		ubicaciones.add(new Ubicacion(1, 0));
		ubicaciones.add(new Ubicacion(2, 90));
		ubicaciones.add(new Ubicacion(3, 180));
		double resultado = ClimaStrategy.getTresPlanetasClimaStrategy().perimetro(ubicaciones);
		assertEquals(Math.sqrt(5.0) + Math.sqrt(13.0) + 4.0, resultado);
	}

	public void testPerimetro2() {
		List<Ubicacion> ubicaciones = new ArrayList<Ubicacion>();
		ubicaciones.add(new Ubicacion(1, 0));
		ubicaciones.add(new Ubicacion(2, 270));
		ubicaciones.add(new Ubicacion(3, 180));
		double resultado = ClimaStrategy.getTresPlanetasClimaStrategy().perimetro(ubicaciones);
		assertEquals(Math.sqrt(5.0) + Math.sqrt(13.0) + 4.0, resultado);
	}
	
	public void testAgregarPlanetas() {
		SistemaSolar sistemaSolar = new SistemaSolar();
		sistemaSolar.agregar(new Planeta("planeta1", 0, 1, Sentido.ANTIHORARIO));
		sistemaSolar.agregar(new Planeta("planeta2", 0, 1, Sentido.ANTIHORARIO));
		sistemaSolar.agregar(new Planeta("planeta2", 0, 1, Sentido.ANTIHORARIO));
		sistemaSolar.agregar(new Planeta("planeta2", 0, 2, Sentido.ANTIHORARIO));
		
		assertEquals(2, sistemaSolar.getPlanetas().size());
	}

	/*
	 * public void testPlanetasChoque() { SistemaSolar sistemaSolar = new
	 * SistemaSolar();
	 * sistemaSolar.add(Planeta.newPlanetaAntihorario("planeta1", 90, 1));
	 * sistemaSolar.add(Planeta.newPlanetaHorario("planeta2", 90, 1));
	 * sistemaSolar.add(Planeta.newPlanetaAntihorario("planeta3", 0, 2)); }
	 */
}

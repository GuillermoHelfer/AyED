package tp04.ejercicio2;

import tp04.ejercicio1.ArbolGeneral;
import tp04.ejercicio3.RecorridosAG;

public class PruebasDeArbolGeneral {

	public static void main(String[] args) {
		ArbolGeneral<Integer> a = new ArbolGeneral<Integer>(0);
		ArbolGeneral<Integer> b = new ArbolGeneral<Integer>(1);
		ArbolGeneral<Integer> c = new ArbolGeneral<Integer>(2);
		ArbolGeneral<Integer> d = new ArbolGeneral<Integer>(3);
		ArbolGeneral<Integer> e = new ArbolGeneral<Integer>(11);
		ArbolGeneral<Integer> f = new ArbolGeneral<Integer>(12);
		ArbolGeneral<Integer> g = new ArbolGeneral<Integer>(13);
		ArbolGeneral<Integer> h = new ArbolGeneral<Integer>(14);
		ArbolGeneral<Integer> i = new ArbolGeneral<Integer>(15);

		a.agregarHijo(b);
		a.agregarHijo(c);
		a.agregarHijo(d);
		b.agregarHijo(e);
		b.agregarHijo(f);
		d.agregarHijo(g);
		g.agregarHijo(h);
		h.agregarHijo(i);

		/*
		 * 0 / | \ 1 2 3 / \ / 11 12 13 | 14 | 15
		 */

		System.out.println(a.preOrden().toString());
		System.out.println(a.inOrden().toString());
		System.out.println(a.postOrden().toString());
		System.out.println(a.porNiveles().toString());

		RecorridosAG rec = new RecorridosAG();

		System.out.println(rec.numerosImparesMayoresQuePreOrden(a, -1));
		System.out.println(rec.numerosImparesMayoresQueInOrden(a, -1));
		System.out.println(rec.numerosImparesMayoresQuePostOrden(a, -1));
		System.out.println(rec.numerosImparesMayoresQuePorNiveles(a, -1));

		System.out.println(rec.numerosImparesMayoresQuePreOrden(a, 2));
		System.out.println(rec.numerosImparesMayoresQueInOrden(a, 2));
		System.out.println(rec.numerosImparesMayoresQuePostOrden(a, 2));
		System.out.println(rec.numerosImparesMayoresQuePorNiveles(a, 2));

		System.out.println(a.altura());

		System.out.println(a.nivel(14));

		System.out.println(a.ancho());

		System.out.println(a.esAncestro(d.getDato(), g.getDato()));

	}

}

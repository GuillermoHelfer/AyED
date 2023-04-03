package tp04.ejercicio3;

import tp02.ejercicio2.ListaEnlazadaGenerica;
import tp02.ejercicio2.ListaGenerica;
import tp02.ejercicio3.ColaGenerica;
import tp04.ejercicio1.ArbolGeneral;

public class RecorridosAG {

	public ListaGenerica<Integer> numerosImparesMayoresQuePreOrden(ArbolGeneral<Integer> a, Integer n) {
		ListaGenerica<Integer> lista = new ListaEnlazadaGenerica<Integer>();
		if (!a.esVacio())
			this.numerosImparesMayoresQuePreOrdenRec(a, lista, n);
		return lista;
	}

	private void numerosImparesMayoresQuePreOrdenRec(ArbolGeneral<Integer> a, ListaGenerica<Integer> l, int n) {
		if ((!(a.getDato() % 2 == 0)) && (a.getDato() > n))
			l.agregarFinal(a.getDato());
		ListaGenerica<ArbolGeneral<Integer>> lHijos = a.getHijos();
		lHijos.comenzar();
		while (!lHijos.fin())
			numerosImparesMayoresQuePreOrdenRec(lHijos.proximo(), l, n);
	}

	public ListaGenerica<Integer> numerosImparesMayoresQueInOrden(ArbolGeneral<Integer> a, Integer n) {
		ListaGenerica<Integer> lista = new ListaEnlazadaGenerica<Integer>();
		if (!a.esVacio())
			this.numerosImparesMayoresQueInOrdenRec(a, lista, n);
		return lista;
	}

	private void numerosImparesMayoresQueInOrdenRec(ArbolGeneral<Integer> a, ListaGenerica<Integer> l, int n) {
		ListaGenerica<ArbolGeneral<Integer>> lHijos = a.getHijos();
		lHijos.comenzar();
		if (!lHijos.fin()) {
			numerosImparesMayoresQuePostOrdenRec(lHijos.proximo(), l, n);
			if ((!(a.getDato() % 2 == 0)) && (a.getDato() > n))
				l.agregarFinal(a.getDato());
			while (!lHijos.fin())
				numerosImparesMayoresQuePostOrdenRec(lHijos.proximo(), l, n);
		} else {
			if ((!(a.getDato() % 2 == 0)) && (a.getDato() > n))
				l.agregarFinal(a.getDato());
		}
	}

	public ListaGenerica<Integer> numerosImparesMayoresQuePostOrden(ArbolGeneral<Integer> a, Integer n) {
		ListaGenerica<Integer> lista = new ListaEnlazadaGenerica<Integer>();
		if (!a.esVacio())
			this.numerosImparesMayoresQuePostOrdenRec(a, lista, n);
		return lista;
	}

	private void numerosImparesMayoresQuePostOrdenRec(ArbolGeneral<Integer> a, ListaGenerica<Integer> l, int n) {
		ListaGenerica<ArbolGeneral<Integer>> lHijos = a.getHijos();
		lHijos.comenzar();
		while (!lHijos.fin())
			numerosImparesMayoresQuePostOrdenRec(lHijos.proximo(), l, n);
		if ((!(a.getDato() % 2 == 0)) && (a.getDato() > n))
			l.agregarFinal(a.getDato());
	}

	public ListaGenerica<Integer> numerosImparesMayoresQuePorNiveles(ArbolGeneral<Integer> a, Integer n) {
		ListaEnlazadaGenerica<Integer> lis = new ListaEnlazadaGenerica<Integer>();
		ColaGenerica<ArbolGeneral<Integer>> cola = new ColaGenerica<ArbolGeneral<Integer>>();
		if (!a.esVacio())
			numerosImparesMayoresQuePorNivelesRec(a, lis, cola, n);
		return lis;
	}

	private void numerosImparesMayoresQuePorNivelesRec(ArbolGeneral<Integer> a, ListaEnlazadaGenerica<Integer> l,
			ColaGenerica<ArbolGeneral<Integer>> c, Integer n) {
		c.encolar(a);
		c.encolar(null);
		while (!c.esVacia()) {
			ArbolGeneral<Integer> arbolAUX = c.desencolar();
			if (arbolAUX != null) {
				if ((!(arbolAUX.getDato() % 2 == 0)) && (arbolAUX.getDato() > n))
					l.agregarFinal(arbolAUX.getDato());
				arbolAUX.getHijos().comenzar();
				while (!arbolAUX.getHijos().fin())
					c.encolar(arbolAUX.getHijos().proximo());
			} else {
				if (!c.esVacia())
					c.encolar(null);
			}
		}
	}

}

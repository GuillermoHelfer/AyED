package tp04.ejercicio1;

import tp02.ejercicio2.ListaEnlazadaGenerica;
import tp02.ejercicio2.ListaGenerica;
import tp02.ejercicio3.ColaGenerica;

public class ArbolGeneral<T> {

	private T dato;

	private ListaGenerica<ArbolGeneral<T>> hijos = new ListaEnlazadaGenerica<ArbolGeneral<T>>();

	public T getDato() {
		return dato;
	}

	public void setDato(T dato) {
		this.dato = dato;
	}

	public void setHijos(ListaGenerica<ArbolGeneral<T>> hijos) {
		if (hijos == null)
			this.hijos = new ListaEnlazadaGenerica<ArbolGeneral<T>>();
		else
			this.hijos = hijos;
	}

	public ArbolGeneral(T dato) {
		this.dato = dato;
	}

	public ArbolGeneral(T dato, ListaGenerica<ArbolGeneral<T>> hijos) {
		this(dato);
		if (hijos == null)
			this.hijos = new ListaEnlazadaGenerica<ArbolGeneral<T>>();
		else
			this.hijos = hijos;
	}

	public ListaGenerica<ArbolGeneral<T>> getHijos() {
		return this.hijos;
	}

	public void agregarHijo(ArbolGeneral<T> unHijo) {

		this.getHijos().agregarFinal(unHijo);
	}

	public boolean esHoja() {

		return !this.tieneHijos();
	}

	public boolean tieneHijos() {
		return !this.hijos.esVacia();
	}

	public boolean esVacio() {

		return this.dato == null && !this.tieneHijos();
	}

	public void eliminarHijo(ArbolGeneral<T> hijo) {
		if (this.tieneHijos()) {
			ListaGenerica<ArbolGeneral<T>> hijos = this.getHijos();
			if (hijos.incluye(hijo))
				hijos.eliminar(hijo);
		}
	}

	public ListaEnlazadaGenerica<T> preOrden() {
		ListaEnlazadaGenerica<T> lis = new ListaEnlazadaGenerica<T>();
		if (!this.esVacio())
			this.recorrerPreOrden(lis);
		return lis;
	}

	private void recorrerPreOrden(ListaGenerica<T> l) {
		l.agregarFinal(this.getDato());
		ListaGenerica<ArbolGeneral<T>> lHijos = this.getHijos();
		lHijos.comenzar();
		while (!lHijos.fin())
			(lHijos.proximo()).recorrerPreOrden(l);
	}

	public ListaEnlazadaGenerica<T> inOrden() {
		ListaEnlazadaGenerica<T> lis = new ListaEnlazadaGenerica<T>();
		if (!this.esVacio())
			this.recorrerInOrden(lis);
		return lis;
	}

	private void recorrerInOrden(ListaGenerica<T> l) {
		ListaGenerica<ArbolGeneral<T>> lHijos = this.getHijos();
		lHijos.comenzar();
		if (!lHijos.fin()) {
			(lHijos.proximo()).recorrerInOrden(l);
			l.agregarFinal(this.getDato());
			while (!lHijos.fin()) {
				(lHijos.proximo()).recorrerInOrden(l);
			}
		} else
			l.agregarFinal(this.getDato());
	}

	public ListaEnlazadaGenerica<T> postOrden() {
		ListaEnlazadaGenerica<T> lis = new ListaEnlazadaGenerica<T>();
		if (!this.esVacio())
			this.recorrerPostOrden(lis);
		return lis;
	}

	private void recorrerPostOrden(ListaGenerica<T> l) {
		ListaGenerica<ArbolGeneral<T>> lHijos = this.getHijos();
		lHijos.comenzar();
		while (!lHijos.fin())
			(lHijos.proximo()).recorrerPostOrden(l);
		l.agregarFinal(this.getDato());
	}

	public ListaEnlazadaGenerica<T> porNiveles() {
		ListaEnlazadaGenerica<T> lis = new ListaEnlazadaGenerica<T>();
		ColaGenerica<ArbolGeneral<T>> cola = new ColaGenerica<ArbolGeneral<T>>();
		if (!this.esVacio())
			this.recorrerPorNiveles(lis, cola);
		return lis;
	}

	private void recorrerPorNiveles(ListaGenerica<T> l, ColaGenerica<ArbolGeneral<T>> c) {
		c.encolar(this);
		c.encolar(null);
		while (!c.esVacia()) {
			ArbolGeneral<T> arbolAUX = c.desencolar();
			if (arbolAUX != null) {
				l.agregarFinal(arbolAUX.getDato());
				arbolAUX.getHijos().comenzar();
				while (!arbolAUX.getHijos().fin()) {
					c.encolar(arbolAUX.getHijos().proximo());
				}
			} else {
				if (!c.esVacia())
					c.encolar(null);
			}
		}
	}

	public Integer altura() {
		if (!this.esVacio())
			return verificarAltura();
		else
			return -1;
	}

	private Integer verificarAltura() {
		Integer altura = -1;
		if (this.esHoja())
			return 0;
		else {
			ListaGenerica<ArbolGeneral<T>> lHijos = this.getHijos();
			lHijos.comenzar();
			while (!lHijos.fin()) {
				altura = lHijos.proximo().verificarAltura();
			}
		}
		return altura + 1;
	}

	public Integer nivel(T dato) {
		ColaGenerica<ArbolGeneral<T>> cola = new ColaGenerica<ArbolGeneral<T>>();
		if (!this.esVacio())
			return verificarNivel(cola, dato);
		else
			return -1;
	}

	private Integer verificarNivel(ColaGenerica<ArbolGeneral<T>> c, T d) {
		c.encolar(this);
		c.encolar(null);
		boolean encontre = false;
		Integer nivel = 0;
		while ((!c.esVacia()) && (!encontre)) {
			ArbolGeneral<T> arbolAUX = c.desencolar();
			if ((arbolAUX != null) && (!encontre)) {
				if (arbolAUX.getDato() == d)
					encontre = true;
				arbolAUX.getHijos().comenzar();
				while ((!arbolAUX.getHijos().fin()) && (!encontre)) {
					c.encolar(arbolAUX.getHijos().proximo());
				}
			} else {
				if (!c.esVacia())
					c.encolar(null);
				nivel++;
			}
		}
		return nivel;
	}

	public Integer ancho() {
		ColaGenerica<ArbolGeneral<T>> cola = new ColaGenerica<ArbolGeneral<T>>();
		if (!esVacio())
			return verificarAncho(cola);
		else
			return 1;
	}

	private Integer verificarAncho(ColaGenerica<ArbolGeneral<T>> c) {
		c.encolar(this);
		c.encolar(null);
		Integer nivel = 0;
		Integer nivelMax = -1;
		Integer cantNodos = 0;
		Integer nodosMax = -1;
		while (!c.esVacia()) {
			cantNodos++;
			ArbolGeneral<T> arbolAUX = c.desencolar();
			if (arbolAUX != null) {
				arbolAUX.getHijos().comenzar();
				while (!arbolAUX.getHijos().fin()) {
					c.encolar(arbolAUX.getHijos().proximo());
				}
			} else {
				if (!c.esVacia()) {
					c.encolar(null);
					if (cantNodos > nodosMax) {
						nodosMax = cantNodos;
						nivelMax = nivel;
					}
					cantNodos = 0;
				}
				nivel++;
			}
		}
		return nivelMax;
	}

	public Boolean esAncestro(T a, T b) {
		if (esVacio())
			return false;
		ArbolGeneral<T> nodoA = buscarDevolverNodo(a);
		if (nodoA != null)
			return buscarDevolverBoolean(nodoA, b);
		return false;
	}

	private ArbolGeneral<T> buscarDevolverNodo(T e) {
		if (getDato() == e)
			return this;
		if (!esHoja()) {
			ListaGenerica<ArbolGeneral<T>> lHijos = this.getHijos();
			lHijos.comenzar();
			while (!lHijos.fin())
				lHijos.proximo().buscarDevolverNodo(e);
		}
		return null;
	}

	private Boolean buscarDevolverBoolean(ArbolGeneral<T> arbol, T e) {
		if (arbol.getDato() == e)
			return true;
		if (!arbol.esHoja()) {
			ListaGenerica<ArbolGeneral<T>> lHijos = arbol.getHijos();
			lHijos.comenzar();
			while (!lHijos.fin())
				return lHijos.proximo().buscarDevolverBoolean(arbol, e);
		}
		return false;
	}

}
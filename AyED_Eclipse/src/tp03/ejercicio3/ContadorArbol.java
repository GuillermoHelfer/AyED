package tp03.ejercicio3;

import tp02.ejercicio2.ListaEnlazadaGenerica;
import tp03.ejercicio1.ArbolBinario;

public class ContadorArbol {
	private ArbolBinario<Integer> arbol = new ArbolBinario<Integer>();

	public ListaEnlazadaGenerica<Integer> numerosParesInorden() {
		ListaEnlazadaGenerica<Integer> lista = new ListaEnlazadaGenerica<Integer>();
		lista.comenzar();
		if (!this.arbol.esVacio())
			agregarInOrden(lista, arbol);
		return lista;
	}

	public void agregarInOrden(ListaEnlazadaGenerica<Integer> lista, ArbolBinario<Integer> arbol) {
		if (arbol.tieneHijoIzquierdo())
			agregarInOrden(lista, arbol.getHijoIzquierdo());
		if (arbol.getDato() % 2 == 0)
			lista.agregarFinal(arbol.getDato());
		if (arbol.tieneHijoDerecho())
			agregarInOrden(lista, arbol.getHijoDerecho());
	}

	public ListaEnlazadaGenerica<Integer> numerosParesPostorden() {
		ListaEnlazadaGenerica<Integer> lista = new ListaEnlazadaGenerica<Integer>();
		lista.comenzar();
		if (!this.arbol.esVacio())
			agregarPostOrden(lista, arbol);
		return lista;
	}

	public void agregarPostOrden(ListaEnlazadaGenerica<Integer> lista, ArbolBinario<Integer> arbol) {
		if (arbol.tieneHijoIzquierdo())
			agregarInOrden(lista, arbol.getHijoIzquierdo());
		if (arbol.tieneHijoDerecho())
			agregarInOrden(lista, arbol.getHijoDerecho());
		if (arbol.getDato() % 2 == 0)
			lista.agregarFinal(arbol.getDato());
	}

}

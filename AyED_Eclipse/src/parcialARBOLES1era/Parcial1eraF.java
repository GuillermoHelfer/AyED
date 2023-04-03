package parcialARBOLES1era;

import tp02.ejercicio2.ListaEnlazadaGenerica;
import tp02.ejercicio2.ListaGenerica;
import tp03.ejercicio1.ArbolBinario;
import tp04.ejercicio1.ArbolGeneral;

public class Parcial1eraF {
	
	public ListaGenerica<Integer> primerCaminoConPares(ArbolBinario<Integer> a, Integer n){
		ListaEnlazadaGenerica<Integer> numeros = new ListaEnlazadaGenerica<Integer>();
		if ((!a.esVacio())&&(n > 0)) {
			Integer suma = 0;
			primerCaminoConParesRec(a,numeros,n,suma);
		}
		return numeros;
	}
	
	public ListaGenerica<Integer> primerCaminoConParesGral(ArbolGeneral<Integer> a, Integer n){
		ListaEnlazadaGenerica<Integer> numeros = new ListaEnlazadaGenerica<Integer>();
		if ((!a.esVacio())&&(n > 0)) {
			Integer suma = 0;
			primerCaminoConParesRecGral(a,numeros,n,suma);
		}
		return numeros;
	}
	
	public Integer primerCaminoConParesRec(ArbolBinario <Integer> a, ListaEnlazadaGenerica <Integer>numeros, int n, int suma) {
		numeros.agregarFinal(a.getDato());
		if (a.getDato() % 2 == 0)
			suma++;
		if ((suma < n)&&(a.tieneHijoIzquierdo()))
			suma = primerCaminoConParesRec(a.getHijoIzquierdo(),numeros,n,suma);
		if (suma < n)
			numeros.eliminarEn(numeros.tamanio());
		if ((suma < n)&&(a.tieneHijoDerecho()))
			primerCaminoConParesRec(a.getHijoDerecho(),numeros,n,suma);
		if (suma < n)
			numeros.eliminarEn(numeros.tamanio());
		return suma;
	}
	
	public Integer primerCaminoConParesRecModHastaElFinal(ArbolBinario <Integer> a, ListaEnlazadaGenerica <Integer>numeros, int n, int suma) {
		numeros.agregarFinal(a.getDato());
		int sumaAct = suma;
		if (a.getDato() % 2 == 0)
			suma++;
		if (suma < n) {
			if (a.tieneHijoIzquierdo())
				suma = primerCaminoConParesRecModHastaElFinal(a.getHijoIzquierdo(),numeros,n,suma);
			if ((suma < n)&&(a.tieneHijoDerecho()))
				suma = primerCaminoConParesRecModHastaElFinal(a.getHijoDerecho(),numeros,n,suma);
			if (suma < n) {
				numeros.eliminarEn(numeros.tamanio());
				suma = sumaAct;
			}
		}
		else {
			if (a.tieneHijoIzquierdo())
				primerCaminoConParesRecModHastaElFinal(a.getHijoIzquierdo(),numeros,n,suma);
			else {
				if (a.tieneHijoDerecho())
					primerCaminoConParesRecModHastaElFinal(a.getHijoDerecho(),numeros,n,suma);
			}
		}
		return suma;
	}
	
	public Integer primerCaminoConParesRecGral(ArbolGeneral<Integer> a, ListaEnlazadaGenerica<Integer> numeros, int n, int suma) {
		numeros.agregarFinal(a.getDato());
		if (a.getDato() % 2 == 0)
			suma++;
		if (!a.esHoja()) {
			ListaGenerica<ArbolGeneral<Integer>> lHijos = a.getHijos();
			lHijos.comenzar();
			while ((! lHijos.fin()) && (suma < n)) 
				suma = primerCaminoConParesRecGral(lHijos.proximo(), numeros, n , suma);
			if (suma < n)
				numeros.eliminarEn(numeros.tamanio());
		}
		else {
			if (suma < n)
				numeros.eliminarEn(numeros.tamanio());
		}
		return suma;
	}
	
	/*
										1
								2				3
							7   	5   	6   	7
						  5 8  9   10 11   12 13   14 15
	*/
}

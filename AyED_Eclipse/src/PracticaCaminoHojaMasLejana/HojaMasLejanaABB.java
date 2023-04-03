package PracticaCaminoHojaMasLejana;

import tp02.ejercicio2.ListaEnlazadaGenerica;
import tp02.ejercicio2.ListaGenerica;
import tp03.ejercicio1.ArbolBinario;

public class HojaMasLejanaABB{
	
	public ListaGenerica<Integer> caminoAHojaMasLejana(ArbolBinario<Integer> a){
		ListaGenerica<Integer> caminoAct = new ListaEnlazadaGenerica<Integer>();
		ListaGenerica<Integer> caminoRet = new ListaEnlazadaGenerica<Integer>();
		if (!a.esVacio())
			caminoAHojaMasLejanaRec(a,caminoAct, caminoRet);
		return caminoRet;
	}
	
	public void caminoAHojaMasLejanaRec(ArbolBinario<Integer> a, ListaGenerica<Integer> caminoAct, ListaGenerica<Integer> caminoRet){
		caminoAct.agregarFinal(a.getDato());
		if (a.esHoja()) {
			if (caminoAct.tamanio() > caminoRet.tamanio()) {
				caminoRet.comenzar();
				while (! caminoRet.fin())
					caminoRet.eliminarEn(caminoRet.proximo());
				caminoAct.comenzar();
				while(!caminoAct.fin())
					caminoRet.agregarFinal(caminoAct.proximo());
			}
		}
		else {
			if (a.tieneHijoIzquierdo())
				caminoAHojaMasLejanaRec(a.getHijoIzquierdo(),caminoAct,caminoRet);
			caminoAct.eliminarEn(caminoAct.tamanio());
			if (a.tieneHijoDerecho())
				caminoAHojaMasLejanaRec(a.getHijoDerecho(),caminoAct,caminoRet);
			caminoAct.eliminarEn(caminoAct.tamanio());
		}
	}
}

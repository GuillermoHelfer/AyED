package PracticaCaminoHojaMasLejana;

import tp02.ejercicio2.ListaEnlazadaGenerica;
import tp02.ejercicio2.ListaGenerica;
import tp04.ejercicio1.ArbolGeneral;

public class HojaMasLejana {
	
	public ListaGenerica<Integer> caminoAHojaMasLejana(ArbolGeneral<Integer> a){
		ListaGenerica<Integer> caminoAct = new ListaEnlazadaGenerica<Integer>();
		ListaGenerica<Integer> caminoRet = new ListaEnlazadaGenerica<Integer>();
		if (!a.esVacio())
			caminoAHojaMasLejanaRec(a,caminoAct, caminoRet);
		return caminoRet;
	}
	
	public void caminoAHojaMasLejanaRec(ArbolGeneral<Integer> a, ListaGenerica<Integer> caminoAct, ListaGenerica<Integer> caminoRet){
		caminoAct.agregarFinal(a.getDato());
		if (a.esHoja()) {
			if (caminoAct.tamanio() > caminoRet.tamanio()) {
				caminoRet.comenzar();
				while (!caminoRet.fin())
					caminoRet.eliminarEn(caminoRet.proximo());
				caminoAct.comenzar();
				while (! caminoAct.fin())
					caminoRet.agregarFinal(caminoAct.proximo());
			}
		}
		else {
			ListaGenerica<ArbolGeneral<Integer>> lHijos = a.getHijos();
			lHijos.comenzar();
			while (! lHijos.fin()) {
				caminoAHojaMasLejanaRec(lHijos.proximo(), caminoAct, caminoRet);
				caminoAct.eliminarEn(caminoAct.tamanio());
			}
		}
	}
}

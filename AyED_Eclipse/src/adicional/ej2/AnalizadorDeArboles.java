package adicional.ej2;

import tp02.ejercicio2.ListaEnlazadaGenerica;
import tp02.ejercicio2.ListaGenerica;
import tp04.ejercicio1.ArbolGeneral;

public class AnalizadorDeArboles {

	public ListaEnlazadaGenerica<Integer> caminoTodosNeg(ArbolGeneral<Integer> arbol) {
		ListaEnlazadaGenerica<Integer> lCamino = new ListaEnlazadaGenerica<Integer>();
		if (!arbol.esVacio())
			caminoTodosNegRec(arbol, lCamino);
		return lCamino;
	}

	private Boolean caminoTodosNegRec(ArbolGeneral<Integer> arbol, ListaEnlazadaGenerica<Integer> lCamino) {
		Boolean encontre = false;
		if (arbol.getDato() < 0) {
			lCamino.agregarFinal(arbol.getDato());
			if (arbol.esHoja())
				return true;
			else {
				ListaGenerica<ArbolGeneral<Integer>> lHijos = new ListaEnlazadaGenerica<ArbolGeneral<Integer>>();
				lHijos.comenzar();
				while (!lHijos.fin() && !encontre) {
					encontre = caminoTodosNegRec(lHijos.proximo(), lCamino);
				}
				if (!encontre)
					lCamino.eliminarEn(lCamino.tamanio());
			}
		}
		return encontre;
	}
}

package practicaParcialFrontera;

import tp02.ejercicio2.ListaEnlazadaGenerica;
import tp02.ejercicio2.ListaGenerica;
import tp04.ejercicio1.ArbolGeneral;

public class PracticaFrontera{
	
	public ListaEnlazadaGenerica<Integer> obtenerFrontera (ArbolGeneral<Integer> a){
		ListaEnlazadaGenerica<Integer> frontera = new ListaEnlazadaGenerica<Integer>();
		if (!a.esVacio())
			obtenerFronteraRec(a,frontera);
		return frontera;
	}
	
	public void obtenerFronteraRec (ArbolGeneral<Integer> a, ListaEnlazadaGenerica<Integer> frontera){
		if (a.esHoja())
			frontera.agregarFinal(a.getDato());
		else {
			ListaGenerica<ArbolGeneral<Integer>> lHijos = a.getHijos();
			lHijos.comenzar();
			while(!lHijos.fin()) {
				obtenerFronteraRec(lHijos.proximo(),frontera);
			}
		}
	}
	
}

package tp03.ejercicio5;

import tp02.ejercicio3.ColaGenerica;
import tp03.ejercicio1.ArbolBinario;

public class ProfundidadDeArbolBinario {
	private ArbolBinario<Integer> arbol = new ArbolBinario<Integer>();

	public void setArbol(ArbolBinario<Integer> arbol) {
		this.arbol = arbol;
	}

	public int sumaElementosProfundidad(int p) {
		int sumaTotal = 0;
		if (!this.arbol.esVacio()) {
			ColaGenerica<ArbolBinario<Integer>> cola = new ColaGenerica<ArbolBinario<Integer>>();
			ArbolBinario<Integer> arbolAUX = null;
			int nivel = 0;
			cola.encolar(this.arbol);
			cola.encolar(null);
			while (!cola.esVacia() && (nivel < p)) {
				arbolAUX = cola.desencolar();
				if (arbolAUX != null) {
					if (arbolAUX.tieneHijoIzquierdo())
						cola.encolar(arbolAUX.getHijoIzquierdo());
					if (arbolAUX.tieneHijoDerecho())
						cola.encolar(arbolAUX.getHijoDerecho());
				} else {
					nivel++;
					if (!cola.esVacia())
						cola.encolar(null);
				}
			}
			while (!cola.esVacia()) {
				arbolAUX = cola.desencolar();
				if (arbolAUX != null)
					sumaTotal += arbolAUX.getDato();
			}
		}
		return sumaTotal;
	}

}

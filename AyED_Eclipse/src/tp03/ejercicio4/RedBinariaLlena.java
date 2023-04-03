package tp03.ejercicio4;

import tp03.ejercicio1.ArbolBinario;

public class RedBinariaLlena {

	public int retardoReenvio(ArbolBinario<Integer> arbol) {
		int retardo = 0;
		if (arbol.esHoja())
			return arbol.getDato();
		else {
			if (arbol.tieneHijoIzquierdo())
				retardo = Math.max(retardo, retardoReenvio(arbol.getHijoIzquierdo()));
			if (arbol.tieneHijoDerecho())
				retardo = Math.max(retardo, retardoReenvio(arbol.getHijoDerecho()));
		}
		return retardo + arbol.getDato();
	}
}

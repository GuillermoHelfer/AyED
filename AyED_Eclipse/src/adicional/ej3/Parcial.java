package adicional.ej3;

import tp02.ejercicio2.ListaGenerica;
import tp04.ejercicio1.ArbolGeneral;

public class Parcial {

	public Boolean esDeSeleccion(ArbolGeneral<Integer> arbol) {
		Boolean correcto = false;
		if (!arbol.esVacio()) {
			if (arbol.esHoja())
				return true;
			ListaGenerica<ArbolGeneral<Integer>> lHijos = arbol.getHijos();
			Integer hijoMin = arbol.getDato();
			Integer hijoAct = null;
			lHijos.comenzar();
			while (!lHijos.fin()) {
				hijoAct = lHijos.proximo().getDato();
				if (hijoAct < hijoMin)
					hijoMin = hijoAct;
			}
			if (arbol.getDato() == hijoMin) {
				correcto = true;
				lHijos.comenzar();
				while ((!lHijos.fin()) && (correcto))
					correcto = esDeSeleccion(lHijos.proximo());
			}
		}
		return correcto;
	}

}

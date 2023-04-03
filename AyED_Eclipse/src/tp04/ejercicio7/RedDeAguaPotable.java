package tp04.ejercicio7;

import tp02.ejercicio2.ListaGenerica;
import tp04.ejercicio1.ArbolGeneral;

public class RedDeAguaPotable {
	private ArbolGeneral<Character> arbol;

	public RedDeAguaPotable(ArbolGeneral<Character> a) {
		this.arbol = a;
	}

	public ArbolGeneral<Character> getArbol() {
		return this.arbol;
	}

	public double minimoCaudal(double caudal) {
		if (!(getArbol().esVacio()) || !(getArbol().esHoja())) {
			ListaGenerica<ArbolGeneral<Character>> lHijos = this.getArbol().getHijos();
			lHijos.comenzar();
			int tamanio = lHijos.tamanio();
			double caudalAct;
			double caudalMin = caudal;
			while (!lHijos.fin()) {
				caudalAct = new RedDeAguaPotable(lHijos.proximo()).minimoCaudal(caudal / tamanio);
				if (caudalAct < caudalMin)
					caudalMin = caudalAct;
			}
			return caudalMin;
		}
		return caudal;
	}

}

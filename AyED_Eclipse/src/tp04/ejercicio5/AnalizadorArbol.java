package tp04.ejercicio5;

import tp02.ejercicio3.ColaGenerica;
import tp04.ejercicio1.ArbolGeneral;

public class AnalizadorArbol {

	public Double devolverMaximoPromedio(ArbolGeneral<AreaEmpresa> arbol) {
		ColaGenerica<ArbolGeneral<AreaEmpresa>> cola = new ColaGenerica<ArbolGeneral<AreaEmpresa>>();
		if (!arbol.esVacio())
			return devolverMaximoPromedioNiveles(arbol, cola);
		else
			return -1.0;
	}

	private Double devolverMaximoPromedioNiveles(ArbolGeneral<AreaEmpresa> a,
			ColaGenerica<ArbolGeneral<AreaEmpresa>> c) {
		c.encolar(a);
		c.encolar(null);
		Integer cantNodos = 0;
		Double promNodos = 0.0;
		Double promMax = -1.0;
		while (!c.esVacia()) {
			if (c.tope() != null) {
				cantNodos++;
				promNodos += c.tope().getDato().getLatencia();
			}
			ArbolGeneral<AreaEmpresa> arbolAUX = c.desencolar();
			if (arbolAUX != null) {
				arbolAUX.getHijos().comenzar();
				while (!arbolAUX.getHijos().fin()) {
					c.encolar(arbolAUX.getHijos().proximo());
				}
			} else {
				if (!c.esVacia()) {
					c.encolar(null);
					promNodos = promNodos / cantNodos;
					if (promNodos > promMax) {
						promMax = promNodos;
					}
					cantNodos = 0;
					promNodos = 0.0;
				}
			}
		}
		return promMax;
	}
}

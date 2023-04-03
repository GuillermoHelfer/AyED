package tp02.ejercicio3;

import tp02.ejercicio2.ListaEnlazadaGenerica;
import tp02.ejercicio2.ListaGenerica;

public class ColaGenerica<T> {
	private ListaGenerica<T> cola;

	public ColaGenerica() {
		cola = new ListaEnlazadaGenerica<T>();
		cola.comenzar();
	}

	public void encolar(T dato) {
		this.cola.agregarFinal(dato);
	}

	public T desencolar() {
		T aux = this.cola.elemento(1);
		this.cola.eliminarEn(1);
		return aux;
	}

	public T tope() {
		return this.cola.elemento(1);
	}

	public boolean esVacia() {
		return this.cola.esVacia();
	}

}

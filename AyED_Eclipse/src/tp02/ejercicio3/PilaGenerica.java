package tp02.ejercicio3;

import tp02.ejercicio2.ListaEnlazadaGenerica;
import tp02.ejercicio2.ListaGenerica;

public class PilaGenerica<T> {
	private ListaGenerica<T> pila;

	public PilaGenerica() {
		pila = new ListaEnlazadaGenerica<T>();
		pila.comenzar();
	}

	public boolean apilar(T dato) {
		this.pila.agregarFinal(dato);
		return true;
	}

	public T desapilar() {
		if (!esVacia()) {
			T aux = this.pila.elemento(this.pila.tamanio());
			this.pila.eliminarEn(this.pila.tamanio());
			return aux;
		} else
			return null;
	}

	public T tope() {
		return this.pila.elemento(this.pila.tamanio());
	}

	public boolean esVacia() {
		return this.pila.esVacia();
	}
}

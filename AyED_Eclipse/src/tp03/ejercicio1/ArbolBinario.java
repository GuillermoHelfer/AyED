package tp03.ejercicio1;

import tp02.ejercicio3.ColaGenerica;

public class ArbolBinario<T> {
	private T dato;
	private ArbolBinario<T> hijoIzquierdo;
	private ArbolBinario<T> hijoDerecho;

	public ArbolBinario() {
		super();
	}

	public ArbolBinario(T dato) {
		this.dato = dato;
	}

	/*
	 * getters y setters
	 * 
	 */
	public T getDato() {
		return dato;
	}

	public void setDato(T dato) {
		this.dato = dato;
	}

	/**
	 * Preguntar antes de invocar si tieneHijoIzquierdo()
	 * 
	 * @return
	 */
	public ArbolBinario<T> getHijoIzquierdo() {
		return this.hijoIzquierdo;
	}

	public ArbolBinario<T> getHijoDerecho() {
		return this.hijoDerecho;

	}

	public void agregarHijoIzquierdo(ArbolBinario<T> hijo) {
		this.hijoIzquierdo = hijo;
	}

	public void agregarHijoDerecho(ArbolBinario<T> hijo) {
		this.hijoDerecho = hijo;
	}

	public void eliminarHijoIzquierdo() {
		this.hijoIzquierdo = null;
	}

	public void eliminarHijoDerecho() {
		this.hijoDerecho = null;
	}

	public boolean esVacio() {
		return this.getDato() == null && !this.tieneHijoIzquierdo() && !this.tieneHijoDerecho();
	}

	public boolean esHoja() {
		return (!this.tieneHijoIzquierdo() && !this.tieneHijoDerecho());

	}

	@Override
	public String toString() {
		return this.getDato().toString();
	}

	public boolean tieneHijoIzquierdo() {
		return this.hijoIzquierdo != null;
	}

	public boolean tieneHijoDerecho() {
		return this.hijoDerecho != null;
	}

	public int contarHojas() {
		if (!esVacio()) {
			if (this.esHoja())
				return 1;
			else {
				if (this.tieneHijoIzquierdo() && (this.tieneHijoDerecho()))
					return this.getHijoIzquierdo().contarHojas() + this.getHijoDerecho().contarHojas();
				else {
					if (tieneHijoIzquierdo())
						return this.getHijoIzquierdo().contarHojas();
					else
						return this.getHijoDerecho().contarHojas();
				}
			}
		}
		return 0;
	}

	public ArbolBinario<T> espejo() {
		ArbolBinario<T> nuevo = new ArbolBinario<T>();
		if (!this.esVacio())
			nuevo.setDato(this.getDato());
		espejar(nuevo, this);
		return nuevo;
	}

	protected void espejar(ArbolBinario<T> nuevo, ArbolBinario<T> copia) {
		if (copia.tieneHijoIzquierdo()) {
			nuevo.agregarHijoDerecho(new ArbolBinario<T>(copia.getHijoIzquierdo().getDato()));
			espejar(nuevo.getHijoDerecho(), copia.getHijoIzquierdo());
		}
		if (copia.tieneHijoDerecho()) {
			nuevo.agregarHijoIzquierdo(new ArbolBinario<T>(copia.getHijoDerecho().getDato()));
			espejar(nuevo.getHijoIzquierdo(), copia.getHijoDerecho());
		}
	}

	public void entreNiveles(int n, int m) {
		if (!esVacio()) {
			ColaGenerica<ArbolBinario<T>> cola = new ColaGenerica<ArbolBinario<T>>();
			ArbolBinario<T> arbolAUX = null;
			int nivel = 0;
			cola.encolar(this);
			cola.encolar(null);
			while (!cola.esVacia() && (nivel < n)) {
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
			while (!cola.esVacia() && (nivel <= m)) {
				arbolAUX = cola.desencolar();
				if (arbolAUX != null) {
					System.out.print(arbolAUX.getDato() + " ");
					if (arbolAUX.tieneHijoIzquierdo())
						cola.encolar(arbolAUX.getHijoIzquierdo());
					if (arbolAUX.tieneHijoDerecho())
						cola.encolar(arbolAUX.getHijoDerecho());
				} else {
					nivel++;
					System.out.println();
					if (!cola.esVacia())
						cola.encolar(null);
				}
			}
		}
	}

	public int altura() {
		int altura = -1;
		if (this.esHoja())
			return 0;
		else {
			if (this.tieneHijoIzquierdo())
				altura = Math.max(altura, this.getHijoIzquierdo().altura());
			if (this.tieneHijoDerecho())
				altura = Math.max(altura, this.getHijoDerecho().altura());
		}
		return altura + 1;
	}

}

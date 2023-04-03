package tp03.ejercicio5;

import tp03.ejercicio1.ArbolBinario;

public class ProfundidadDeArbolBinarioImp {

	public static void main(String[] args) {
		ArbolBinario<Integer> a = new ArbolBinario<Integer>(10);
		ArbolBinario<Integer> b = new ArbolBinario<Integer>(5);
		ArbolBinario<Integer> c = new ArbolBinario<Integer>(8);
		ArbolBinario<Integer> d = new ArbolBinario<Integer>(7);
		ArbolBinario<Integer> e = new ArbolBinario<Integer>(2);
		ArbolBinario<Integer> f = new ArbolBinario<Integer>(5);
		ArbolBinario<Integer> g = new ArbolBinario<Integer>(6);

		a.agregarHijoIzquierdo(b);
		a.agregarHijoDerecho(c);
		b.agregarHijoIzquierdo(d);
		b.agregarHijoDerecho(e);
		c.agregarHijoIzquierdo(f);
		c.agregarHijoDerecho(g);

		ProfundidadDeArbolBinario prof = new ProfundidadDeArbolBinario();

		prof.setArbol(a);

		System.out.println(prof.sumaElementosProfundidad(2));

	}

}

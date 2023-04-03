package tp03.ejercicio1;

public class ArbolTest {

	public static void main(String[] args) {
		ArbolBinario<Character> a = new ArbolBinario<Character>('A');
		ArbolBinario<Character> b = new ArbolBinario<Character>('B');
		ArbolBinario<Character> c = new ArbolBinario<Character>('C');
		ArbolBinario<Character> d = new ArbolBinario<Character>('D');
		ArbolBinario<Character> e = new ArbolBinario<Character>('E');
		ArbolBinario<Character> f = new ArbolBinario<Character>('F');

		a.agregarHijoIzquierdo(b);
		a.agregarHijoDerecho(c);
		b.agregarHijoIzquierdo(d);
		c.agregarHijoIzquierdo(e);
		c.agregarHijoDerecho(f);

		a.entreNiveles(0, 2);

		a.entreNiveles(1, 2);

		ArbolBinario<Character> espejito = a.espejo();

		espejito.entreNiveles(0, 2);

		espejito.entreNiveles(1, 2);

	}

}

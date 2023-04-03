package tp04.ejercicio7;

import tp04.ejercicio1.ArbolGeneral;

public class RedImplementacion {

	public static void main(String[] args) {
		ArbolGeneral<Character> a = new ArbolGeneral<Character>('a');
		ArbolGeneral<Character> b = new ArbolGeneral<Character>('b');
		ArbolGeneral<Character> c = new ArbolGeneral<Character>('c');
		ArbolGeneral<Character> d = new ArbolGeneral<Character>('d');
		ArbolGeneral<Character> e = new ArbolGeneral<Character>('e');
		ArbolGeneral<Character> f = new ArbolGeneral<Character>('f');
		ArbolGeneral<Character> g = new ArbolGeneral<Character>('g');
		ArbolGeneral<Character> h = new ArbolGeneral<Character>('h');
		ArbolGeneral<Character> i = new ArbolGeneral<Character>('i');
		ArbolGeneral<Character> j = new ArbolGeneral<Character>('j');
		ArbolGeneral<Character> k = new ArbolGeneral<Character>('k');
		ArbolGeneral<Character> l = new ArbolGeneral<Character>('l');
		ArbolGeneral<Character> m = new ArbolGeneral<Character>('m');
		ArbolGeneral<Character> n = new ArbolGeneral<Character>('n');

		a.agregarHijo(b);
		a.agregarHijo(c);
		a.agregarHijo(d);
		a.agregarHijo(e);
		c.agregarHijo(f);
		c.agregarHijo(g);
		g.agregarHijo(l);
		d.agregarHijo(h);
		d.agregarHijo(i);
		d.agregarHijo(j);
		d.agregarHijo(k);
		j.agregarHijo(m);
		j.agregarHijo(n);

		RedDeAguaPotable red = new RedDeAguaPotable(a);
		System.out.println(red.minimoCaudal(1000));

	}

}

package tp04.ejercicio5;

import tp04.ejercicio1.ArbolGeneral;

public class AnalizadorArbolImpl {

	public static void main(String[] args) {
		AnalizadorArbol AA = new AnalizadorArbol();

		AreaEmpresa a1 = new AreaEmpresa(14);
		AreaEmpresa a2 = new AreaEmpresa(13);
		AreaEmpresa a3 = new AreaEmpresa(25);
		AreaEmpresa a4 = new AreaEmpresa(10);
		AreaEmpresa a5 = new AreaEmpresa(4);
		AreaEmpresa a6 = new AreaEmpresa(7);
		AreaEmpresa a7 = new AreaEmpresa(5);
		AreaEmpresa a8 = new AreaEmpresa(6);
		AreaEmpresa a9 = new AreaEmpresa(10);
		AreaEmpresa a10 = new AreaEmpresa(18);
		AreaEmpresa a11 = new AreaEmpresa(9);
		AreaEmpresa a12 = new AreaEmpresa(12);
		AreaEmpresa a13 = new AreaEmpresa(19);

		ArbolGeneral<AreaEmpresa> m = new ArbolGeneral<AreaEmpresa>(a1);
		ArbolGeneral<AreaEmpresa> j = new ArbolGeneral<AreaEmpresa>(a2);
		ArbolGeneral<AreaEmpresa> k = new ArbolGeneral<AreaEmpresa>(a3);
		ArbolGeneral<AreaEmpresa> l = new ArbolGeneral<AreaEmpresa>(a4);
		ArbolGeneral<AreaEmpresa> a = new ArbolGeneral<AreaEmpresa>(a5);
		ArbolGeneral<AreaEmpresa> b = new ArbolGeneral<AreaEmpresa>(a6);
		ArbolGeneral<AreaEmpresa> c = new ArbolGeneral<AreaEmpresa>(a7);
		ArbolGeneral<AreaEmpresa> d = new ArbolGeneral<AreaEmpresa>(a8);
		ArbolGeneral<AreaEmpresa> e = new ArbolGeneral<AreaEmpresa>(a9);
		ArbolGeneral<AreaEmpresa> f = new ArbolGeneral<AreaEmpresa>(a10);
		ArbolGeneral<AreaEmpresa> g = new ArbolGeneral<AreaEmpresa>(a11);
		ArbolGeneral<AreaEmpresa> h = new ArbolGeneral<AreaEmpresa>(a12);
		ArbolGeneral<AreaEmpresa> i = new ArbolGeneral<AreaEmpresa>(a13);

		m.agregarHijo(j);
		m.agregarHijo(k);
		m.agregarHijo(l);
		j.agregarHijo(a);
		j.agregarHijo(b);
		j.agregarHijo(c);
		k.agregarHijo(d);
		k.agregarHijo(e);
		k.agregarHijo(f);
		l.agregarHijo(g);
		l.agregarHijo(h);
		l.agregarHijo(i);

		System.out.println(AA.devolverMaximoPromedio(m));

	}

}

package tp02.ejercicio1;

public class TestListaDeEnterosEnlazada {

	public static void main(String[] args) {
		ListaDeEnterosEnlazada listaEE = new ListaDeEnterosEnlazada();
		Ejercicio1_5 e1_5 = new Ejercicio1_5();

		listaEE.agregarFinal(0);
		listaEE.agregarFinal(1);
		listaEE.agregarFinal(2);
		listaEE.agregarFinal(3);
		listaEE.agregarFinal(4);
		listaEE.agregarFinal(5);
		listaEE.agregarFinal(6);
		listaEE.agregarFinal(7);
		listaEE.agregarFinal(8);
		listaEE.agregarFinal(9);

		for (int i = 1; i <= listaEE.tamanio(); i++) {
			System.out.println(listaEE.elemento(i));
		}

		e1_5.desdeAtras(listaEE, listaEE.tamanio());

	}

}

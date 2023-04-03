package tp02.ejercicio1;

public class TestListaDeEnterosConArreglos {

	public static void main(String[] args) {

		ListaDeEnterosConArreglos listaECA = new ListaDeEnterosConArreglos();

		listaECA.agregarFinal(0);
		listaECA.agregarFinal(1);
		listaECA.agregarFinal(2);
		listaECA.agregarFinal(3);
		listaECA.agregarFinal(4);
		listaECA.agregarFinal(5);
		listaECA.agregarFinal(6);
		listaECA.agregarFinal(7);
		listaECA.agregarFinal(8);
		listaECA.agregarFinal(9);

		for (int i = 1; i <= listaECA.tamanio(); i++) {
			System.out.println(listaECA.elemento(i));
		}
		// HAY QUE EMPEZAR EN 1 LA ITERACION
	}
}

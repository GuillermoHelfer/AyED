package tp02.ejercicio1;

public class Ejercicio1_5 {

	public void desdeAtras(ListaDeEnterosEnlazada listaEE, int donde) {
		if (donde != 0) {
			System.out.println(listaEE.elemento(donde));
			desdeAtras(listaEE, donde - 1);
		}
	}
}

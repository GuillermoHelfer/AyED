package tp02.ejercicio1;

public class Ejercicio1_6 {
	private ListaDeEnterosEnlazada lista = new ListaDeEnterosEnlazada();

	public ListaDeEnterosEnlazada calcularSucesion(int n) {
		lista.agregarFinal(n);
		if (n != 1) {
			if (n % 2 != 0)
				calcularSucesion(3 * n + 1);
			else
				calcularSucesion(n / 2);
		}
		return lista;
	}

}

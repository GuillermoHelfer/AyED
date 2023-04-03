package tp02.ejercicio1;

public class Ejercicio1_7 {

	public static void main(String[] args) {

		Ejercicio1_6 f = new Ejercicio1_6();
		ListaDeEnterosEnlazada l = f.calcularSucesion(6);

		for (int i = 1; i <= l.tamanio(); i++) {
			System.out.println(l.elemento(i));
		}

	}

}

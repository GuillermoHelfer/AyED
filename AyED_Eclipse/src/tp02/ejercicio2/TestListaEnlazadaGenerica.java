package tp02.ejercicio2;

import practica1.Estudiante;

public class TestListaEnlazadaGenerica {
	public static void main(String[] args) {
		Estudiante guille = new Estudiante("Guillermo", "Helfer", 2, "guillehelfer@gmail.com", "68 262");

		Estudiante facu = new Estudiante("Facundo", "Pujol", 3, "facupujol@gmail.com", " 1 2345");

		Estudiante martina = new Estudiante("Martina", "Garcia", 2, "martigarcia@gmail.com", "5 4321");

		Estudiante nico = new Estudiante("Nicolas", "Alvarez", 1, "nicoalvarez@gmail.com", "6 9420");

		ListaEnlazadaGenerica<Estudiante> listaEG = new ListaEnlazadaGenerica<Estudiante>();

		listaEG.comenzar();
		listaEG.agregarFinal(guille);
		listaEG.agregarFinal(facu);
		listaEG.agregarFinal(martina);
		listaEG.agregarFinal(nico);

		System.out.println(listaEG.toString());

	}

}

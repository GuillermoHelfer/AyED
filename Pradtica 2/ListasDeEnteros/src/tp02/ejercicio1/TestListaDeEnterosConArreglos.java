package tp02.ejercicio1;

import tp02.ejercicio1.*;

public class TestListaDeEnterosConArreglos {
	public static void main(String[] args) {
		
		ListaDeEnterosConArreglos lista = new ListaDeEnterosConArreglos();
		
		int num1 = 1;
		int num2 = 2;
		int num3 = 3;
		int num4 = 4;
		int num5 = 5;
		
		lista.comenzar();
		lista.agregarFinal(num3);
		lista.agregarFinal(num5);
		lista.agregarFinal(num1);
		lista.agregarFinal(num2);
		lista.agregarFinal(num4);
		
		for (int i = 1; i<=lista.tamanio(); i++) {
			System.out.println(lista.elemento(i));
		}
			
	}
}

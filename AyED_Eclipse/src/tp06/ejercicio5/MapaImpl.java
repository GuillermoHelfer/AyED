package tp06.ejercicio5;

import tp02.ejercicio2.ListaEnlazadaGenerica;
import tp06.ejercicio3.VerticeImplListAdy;

public class MapaImpl {

	public static void main(String[] args) {
		Mapa mapa= new Mapa();
		VerticeImplListAdy<String> vert1 = new VerticeImplListAdy<>("La Plata");
		VerticeImplListAdy<String> vert2 = new VerticeImplListAdy<>("Mar del Plata");
		VerticeImplListAdy<String> vert3 = new VerticeImplListAdy<>("Lujan");
		mapa.getMapaCiudades().agregarVertice(vert1);
		mapa.getMapaCiudades().agregarVertice(vert2);
		mapa.getMapaCiudades().agregarVertice(vert3);
		mapa.getMapaCiudades().conectar(vert1, vert2);
		mapa.getMapaCiudades().conectar(vert2, vert1);
		mapa.getMapaCiudades().conectar(vert2, vert3);
		mapa.getMapaCiudades().conectar(vert3, vert2);
		
		for (int i=1; i<=mapa.getMapaCiudades().listaDeVertices().tamanio(); i++) {
			System.out.println(mapa.getMapaCiudades().listaDeVertices().elemento(i).dato());
		}
		
		System.out.println(mapa.devolverCamino("La Plata", "Lujan").toString());
	}

}

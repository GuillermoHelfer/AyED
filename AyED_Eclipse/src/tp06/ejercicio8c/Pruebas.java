package tp06.ejercicio8c;

import tp02.ejercicio2.ListaEnlazadaGenerica;
import tp02.ejercicio2.ListaGenerica;
import tp06.ejercicio3.Arista;
import tp06.ejercicio3.Grafo;
import tp06.ejercicio3.GrafoImplListAdy;
import tp06.ejercicio3.Vertice;

public class Pruebas {
	
	private Grafo<String> grafo = new GrafoImplListAdy<>();
	private ColaPrioridades<T> heap;
	
	private ListaGenerica<String> caminoMinDijkstra(int inicial, int fin, Grafo<String> grafo, boolean[] marca) {
		ListaGenerica <Vertice<String>> lVertices = grafo.listaDeVertices();
		ListaGenerica<String> listaRet = new ListaEnlazadaGenerica<>();
		int n = lVertices.tamanio();
		int [] distancias = new int [n+1];
		int [] pasando = new int [n+1];
		for (int i=1; i<=n; i++) {
			distancias[i] = Integer.MAX_VALUE;
			pasando[i] = 0;
		}
		distancias[inicial] = 0;
		for (int i=1; i<=n; i++) {
			int u = findMinDistance(distancias,marca);
			marca[u-1] = true;
			if (grafo.vetice(u).equals(grafo.vetice(fin))){
				int aux= -1;
				listaRet.agregarInicio(grafo.vetice(fin).dato());
				while(aux != inicial) {
					aux = pasando[fin];
					listaRet.agregarInicio(grafo.vetice(aux).dato());
				}
				listaRet.agregarInicio(grafo.vetice(inicial).dato());
				return listaRet;
			}
			ListaGenerica <Arista<String>> lAdyacentes = grafo.listaDeAdyacentes(grafo.vetice(u));
			for (int j=1; j<=lAdyacentes.tamanio(); j++) {
				if (!marca[j]) {
					if (distancias[j] > distancias[u-1] + grafo.peso(grafo.vetice(u), grafo.vetice(j))) {
						distancias[j] = distancias[u-1] + grafo.peso(grafo.vetice(u), grafo.vetice(j));
						pasando[j] = u;
					}
				}
			}
		}
		return listaRet;
}
	
	
}

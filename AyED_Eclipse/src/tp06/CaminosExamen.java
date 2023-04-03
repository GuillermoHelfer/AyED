package tp06;

import tp02.ejercicio2.ListaEnlazadaGenerica;
import tp02.ejercicio2.ListaGenerica;
import tp02.ejercicio3.ColaGenerica;
import tp06.ejercicio3.Arista;
import tp06.ejercicio3.Grafo;
import tp06.ejercicio3.Vertice;

public class CaminosExamen <T> {
	
	private Grafo<String> mapaCiudades;
	
	//DFS
	
		public ListaGenerica<Vertice<T>> dfs(Grafo<T> grafo){
			boolean[] marca = new boolean[grafo.listaDeVertices().tamanio() + 1]; //uso una posicion mas de memoria
			ListaGenerica<Vertice<T>> lista = new ListaEnlazadaGenerica<>();
			for (int i=1; i<grafo.listaDeVertices().tamanio();i++) {
				if(!marca[i])
					this.dfs(i,grafo,marca,lista);
			}
			return lista;
		}

		private void dfs(int i, Grafo<T> grafo, boolean[] marca, ListaGenerica<Vertice<T>> lista) {
			marca[i] = true;
			Arista <T> arista=null;
			int j=0;
			Vertice<T> v = grafo.listaDeVertices().elemento(i);
			lista.agregarFinal(v);
			System.out.println(v);
			ListaGenerica<Arista<T>> ady = grafo.listaDeAdyacentes(v);
			ady.comenzar();
			while(!ady.fin()) {
				arista=ady.proximo();
				j = arista.verticeDestino().getPosicion();
				if(!marca[j])
						this.dfs(j, grafo, marca, lista);
			}
		}
	
	//BFS
	
		public ListaGenerica<Vertice<T>> bfs(Grafo<T> grafo){
			boolean[] marca = new boolean[grafo.listaDeVertices().tamanio() + 1];
			ListaGenerica<Vertice<T>> lista = new ListaEnlazadaGenerica<>();
			for (int i=1; i<=grafo.listaDeVertices().tamanio();i++) {
				if(!marca[i])
					this.bfs(i,grafo,marca,lista);
			}
			return lista;
		}

		private void bfs(int i, Grafo<T> grafo, boolean[] marca, ListaGenerica<Vertice<T>> lista) {
			ListaGenerica<Arista<T>> ady = null;
			ColaGenerica<Vertice<T>> q = new ColaGenerica<>();
			q.encolar(grafo.listaDeVertices().elemento(i));
			marca[i] = true;
			while (!q.esVacia()) { //visitar vertices de la cola
				Vertice<T> v = q.desencolar();
				lista.agregarFinal(v);
				System.out.println(v);
				ady = grafo.listaDeAdyacentes(v);
				ady.comenzar();
				while(!ady.fin()) { //encolar adyacentes no visitados
					Arista<T> arista = ady.proximo();
					int j = arista.verticeDestino().getPosicion();
					if(!marca[j]) {
						Vertice<T> w = arista.verticeDestino();
						marca[j] = true;
						q.encolar(w);
					}
				}
			}
		}
		
		public ListaGenerica<String> caminoMasCortoDijkstra(String ciudad1, String ciudad2){
			boolean[] marca = new boolean[mapaCiudades.listaDeVertices().tamanio()];
			int inicial=0;
			int fin=0;
			for (int i=1; i<=mapaCiudades.listaDeVertices().tamanio();i++) {
				if(mapaCiudades.vetice(i).dato().equals(ciudad1))
					inicial = i;
				if(mapaCiudades.vetice(i).dato().equals(ciudad2))
					fin = i;
			}
			return caminoMinDijkstra(inicial,fin,mapaCiudades,marca);
		}

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
		
		private int findMinDistance(int[] distance, boolean[] visitedVertex) {
		    int minDistance = Integer.MAX_VALUE;
		    int minDistanceVertex = -1;
		    for (int i = 1; i <= distance.length; i++) {
		    	if (!visitedVertex[i] && distance[i] < minDistance) {
		    		minDistance = distance[i];
		    		minDistanceVertex = i;
		    	}
		    }
		    return minDistanceVertex;
		}
		
		
		public ListaGenerica<String> caminoMasCortoDijkstraNegativos(String ciudad1, String ciudad2){
			boolean[] marca = new boolean[mapaCiudades.listaDeVertices().tamanio()];
			int inicial=0;
			int fin=0;
			for (int i=1; i<=mapaCiudades.listaDeVertices().tamanio();i++) {
				if(mapaCiudades.vetice(i).dato().equals(ciudad1))
					inicial = i;
				if(mapaCiudades.vetice(i).dato().equals(ciudad2))
					fin = i;
			}
			return caminoMinDijkstraNegativos(inicial,fin,mapaCiudades,marca);
		}

		private ListaGenerica<String> caminoMinDijkstraNegativos(int inicial, int fin, Grafo<String> grafo, boolean[] marca) {
				int n = grafo.listaDeVertices().tamanio();
				int [] distancias = new int [n+1];
				int [] pasando = new int [n+1];
				for (int i=1; i<=n; i++) {
					distancias[i] = Integer.MAX_VALUE;
					pasando[i] = 0;
				}
				distancias[inicial] = 0;
				ColaGenerica <Vertice<String>> Q = new ColaGenerica<>();
				Q.encolar(grafo.vetice(inicial));
				Vertice<String> u;
				while(!Q.esVacia()) {
					u = Q.desencolar();
					ListaGenerica <Arista<String>> lAdyacentes = grafo.listaDeAdyacentes(u);
					Vertice<String> w;
					for (int i=1; i <= lAdyacentes.tamanio(); i++) {
						w = lAdyacentes.elemento(i).verticeDestino();
						if (distancias[w.getPosicion()] > distancias[u.getPosicion()] + grafo.peso(u, w)) {
							distancias[w.getPosicion()] = distancias[u.getPosicion()] + grafo.peso(u, w);
							pasando[w.getPosicion()] = u.getPosicion();
							ListaGenerica<Vertice<String>> laux = new ListaEnlazadaGenerica<>();
							ColaGenerica <Vertice<String>> Qaux = Q;
							while(!Qaux.esVacia()) {
								laux.agregarFinal(Qaux.desencolar());
							}
							if (!laux.incluye(w)) {
								Q.encolar(w);
							}
						}
					}
				}
				ListaGenerica<String> listaRet = new ListaEnlazadaGenerica<>();
				int act = fin;
				while (act != inicial) {
					listaRet.agregarInicio(grafo.vetice(act).dato());
					act = pasando[act];
				}
				listaRet.agregarInicio(grafo.vetice(inicial).dato());
				return listaRet;
		}
		
}

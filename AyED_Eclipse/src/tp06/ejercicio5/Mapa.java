package tp06.ejercicio5;

import tp02.ejercicio2.ListaEnlazadaGenerica;
import tp02.ejercicio2.ListaGenerica;
import tp06.ejercicio3.Arista;
import tp06.ejercicio3.Grafo;
import tp06.ejercicio3.GrafoImplListAdy;
import tp06.ejercicio3.Vertice;

public class Mapa {
	private Grafo<String> mapaCiudades = new GrafoImplListAdy<>();
	
	public ListaGenerica<String> devolverCamino (String ciudad1, String ciudad2) {
		boolean[] marca = new boolean[mapaCiudades.listaDeVertices().tamanio()];
		ListaGenerica<String> lista = new ListaEnlazadaGenerica<>();
		int vInicio = 1;
		while (!(getMapaCiudades().listaDeVertices().elemento(vInicio).dato().equals(ciudad1))) 
			vInicio++;
		devolverCaminoDFS(vInicio,mapaCiudades,marca,lista,ciudad2);
		return lista;
	}
	
	private boolean devolverCaminoDFS(int i, Grafo<String> grafo, boolean[] marca, ListaGenerica<String> lista, String ciudad2) {
		marca[i-1] = true;
		int j = 0;
		boolean encontre = false;
		Vertice<String> v = grafo.listaDeVertices().elemento(i);
		lista.agregarFinal(v.dato());
		if(v.dato().equals(ciudad2))
			return true;
		Arista <String> arista = null;
		ListaGenerica<Arista<String>> ady = grafo.listaDeAdyacentes(v);
		ady.comenzar();
		while((!ady.fin()&&(!encontre))) {
			arista=ady.proximo();
			j = arista.verticeDestino().getPosicion();
			if(!marca[j-1])
					encontre = devolverCaminoDFS(j, grafo, marca, lista,ciudad2);
		}
		if (!encontre)
			lista.eliminarEn(lista.tamanio());
		return encontre;
	}
	
	public ListaGenerica<String> devolverCaminoExceptuando (String ciudad1, String ciudad2, ListaGenerica<String> ciudades) {
		boolean[] marca = new boolean[getMapaCiudades().listaDeVertices().tamanio()];
		ListaGenerica<String> lista = new ListaEnlazadaGenerica<>();
		int vInicio = 1;
		while (!(getMapaCiudades().listaDeVertices().elemento(vInicio).dato() == ciudad1)) vInicio++;
		devolverCaminoExceptuandoDFS(vInicio,getMapaCiudades(),marca,lista,ciudad2,ciudades);
		return lista;
	}

	private boolean devolverCaminoExceptuandoDFS(int i, Grafo<String> grafo, boolean[] marca, ListaGenerica<String> lista, String ciudad2, ListaGenerica<String> ciudades) {
		marca[i] = true;
		Arista <String> arista = null;
		int j = 0;
		boolean encontre = false;
		Vertice<String> v = grafo.listaDeVertices().elemento(i);
		lista.agregarFinal(v.dato());
		if(v.dato().equals(ciudad2))
			return true;
		ciudades.comenzar();
		for (int x=1; x <= ciudades.tamanio(); x++) { //ver si estoy sobre una ciudad no permitida
			if (v.dato().equals(ciudades.proximo())) {
				ciudades.eliminarEn(x); //saco la ciudad de la lista poirque ya no puedo ingresar por ella y ahorro lugar y t de busqueda
				lista.eliminarEn(lista.tamanio()); //borro la ciudad agregada a la listaRet que estaba prohibida
				return false; // podria hacer la comparacion antes de agregar y hasta hacer un modulo  aparte
			}
		}
		ListaGenerica<Arista<String>> ady = grafo.listaDeAdyacentes(v);
		ady.comenzar();
		while((!ady.fin()&&(!encontre))) {
			arista=ady.proximo();
			j = arista.verticeDestino().getPosicion();
			if(!marca[j])
					encontre = devolverCaminoDFS(j, grafo, marca, lista,ciudad2);
		}
		if (!encontre)
			lista.eliminarEn(lista.tamanio());
		return encontre;
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
	
	public ListaGenerica<String> caminoSinCargarCombustible(String ciudad1, String ciudad2, int combustible){
		boolean[] marca = new boolean[mapaCiudades.listaDeVertices().tamanio()];
		int inicial=0;
		int fin=0;
		for (int i=1; i<=mapaCiudades.listaDeVertices().tamanio();i++) {
			if(mapaCiudades.vetice(i).dato().equals(ciudad1))
				inicial = i;
			if(mapaCiudades.vetice(i).dato().equals(ciudad2))
				fin = i;
		}
		return caminoMinDijkstraSinCombustible(inicial,fin,mapaCiudades,marca,combustible);
	}
	
	private ListaGenerica<String> caminoMinDijkstraSinCombustible(int inicial, int fin, Grafo<String> grafo, boolean[] marca, int combustible) {
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
		int pesoAct = 0;
		for (int i=1; i<=n; i++) { // para cada vertice
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
			if (pesoAct + grafo.peso(grafo.vetice(i), grafo.vetice(u)) <= combustible) { //si aun queda combustible entonces actualizar adyacentes
				pesoAct += grafo.peso(grafo.vetice(i), grafo.vetice(u));
				ListaGenerica <Arista<String>> lAdyacentes = grafo.listaDeAdyacentes(grafo.vetice(u));
				for (int j=1; j<=lAdyacentes.tamanio(); j++) { //actualiza los adyacentes no visitados
					if (!marca[j-1]) {
						if (distancias[j] > distancias[u-1] + grafo.peso(grafo.vetice(u), grafo.vetice(j))) {
							distancias[j] = distancias[u-1] + grafo.peso(grafo.vetice(u), grafo.vetice(j));
							pasando[j] = u;
						}
					}
				}
			}
		}
		return listaRet;
	}
	
	public ListaGenerica<String> caminoConMenorCargaDeCombustible(String ciudad1, String ciudad2, int combustible){
		boolean[] marca = new boolean[mapaCiudades.listaDeVertices().tamanio()];
		ListaGenerica<String> listaRet = new ListaEnlazadaGenerica<String>();
		int inicial=0;
		int fin=0;
		for (int i=1; i<=mapaCiudades.listaDeVertices().tamanio();i++) {
			if(mapaCiudades.vetice(i).dato().equals(ciudad1))
				inicial = i;
			if(mapaCiudades.vetice(i).dato().equals(ciudad2))
				fin = i;
		}
		caminoDFSCombustible(listaRet,inicial,fin,mapaCiudades,marca,combustible);
		return listaRet;
	}
	
	private boolean caminoDFSCombustible(ListaGenerica<String> listaRet, int i, int fin, Grafo<String> grafo, boolean[] marca, int combustible){
		marca[i-1] = true; //creo el vector de marcas y las pongo en true
		Arista <String> arista = null;
		int j = 0;   //creo e inicio en valores default variables aux
		boolean encontre = false;
		Vertice<String> v = grafo.listaDeVertices().elemento(i); //guardo la lista de vertices en una v aux
		listaRet.agregarFinal(v.dato());  //agrego al final de la lista a retornar el primer elemento de la lista aux
		if(v.dato().equals(grafo.vetice(fin).dato())) //si estoy en el final, terminar el programa
			return true;
		ListaGenerica<Arista<String>> ady = grafo.listaDeAdyacentes(v); //sino,  creo lista de adyacencias aux
		ady.comenzar();
		while((!ady.fin()&&(!encontre))) { //recorro la lista de adyacencias aux
			arista=ady.proximo();
			j = arista.verticeDestino().getPosicion(); //guardo la posicion del vert ady
			if((!marca[j-1]) && (grafo.peso(grafo.vetice(i), arista.verticeDestino()) <= combustible)) //si no esta marcado y hay combust
					encontre = caminoDFSCombustible(listaRet,j,fin,grafo,marca,combustible); // sigo por este camino
		}
		if (!encontre)  // si el camino no coincide borro
			listaRet.eliminarEn(listaRet.tamanio());
		return encontre;
	}
	
	
	public Grafo<String> getMapaCiudades() {
		return mapaCiudades;
	}
	
}

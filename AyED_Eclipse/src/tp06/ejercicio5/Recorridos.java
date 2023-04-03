package tp06.ejercicio5;

import tp02.ejercicio2.ListaEnlazadaGenerica;
import tp02.ejercicio2.ListaGenerica;
import tp02.ejercicio3.ColaGenerica;
import tp06.ejercicio3.Arista;
import tp06.ejercicio3.Grafo;
import tp06.ejercicio3.Vertice;

public class Recorridos <T>{
	
	//DFS
	
	public ListaGenerica<Vertice<T>> dfs(Grafo<T> grafo){
		boolean[] marca = new boolean[grafo.listaDeVertices().tamanio()];
		ListaGenerica<Vertice<T>> lista = new ListaEnlazadaGenerica<>();
		for (int i=0; i<grafo.listaDeVertices().tamanio();i++) {
			if(!marca[i])
				this.dfs(i+1,grafo,marca,lista);
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
			if(!marca[j-1])
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
		ListaGenerica<Arista<T>> ady;
		ColaGenerica<Vertice<T>> q = new ColaGenerica<>();
		q.encolar(grafo.listaDeVertices().elemento(i));
		marca[i] = true;
		Vertice<T> v;
		while (!q.esVacia()) { //visitar vertices de la cola
			v = q.desencolar();
			lista.agregarFinal(v);
			System.out.println(v.dato());
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
	
	protected int bfsVIRUS(int inicial, Grafo<T> grafo, boolean[] marca, ListaGenerica<Vertice<T>> lista) {
		int n = grafo.listaDeVertices().tamanio();
		ColaGenerica<Vertice<T>> q = new ColaGenerica<>();
		int[] tiempos = new int [n + 1];
		int tMax = 0;
		int tAct = 0;
		for (int i=0; i<=n;i++) {
			tiempos[i] = Integer.MAX_VALUE;
		}
		marca[inicial] = true;
		q.encolar(grafo.listaDeVertices().elemento(inicial));
		Vertice<T> v;
		while (!q.esVacia()) { //visitar vertices de la cola
			v = q.desencolar();
			tAct = tiempos[v.getPosicion()] + 1;
			ListaGenerica<Arista<T>> ady = grafo.listaDeAdyacentes(v);
			ady.comenzar();
			while(!ady.fin()) { //encolar adyacentes no visitados
				Arista<T> arista = ady.proximo();
				Vertice<T> w = arista.verticeDestino();
				int j = w.getPosicion();
				if (tiempos[j] == Integer.MAX_VALUE) {
					tiempos[j] = tAct;
					if (tAct > tMax) tMax = tAct;
					q.encolar(w);
				}
			}
		}
		return tMax;
	}
	
	
	
	private boolean dfs (ListaGenerica<String> listaRet, Grafo<String> grafo, int i, int fin, boolean[] marca, int tiempoMax, 
			int tiempoAct, ListaGenerica<String> restringidos){
		Arista<String> arista = null;
		int j;
		boolean encontre = false;
		Vertice<String> v = grafo.listaDeVertices().elemento(i);
		if (v.dato().equals(grafo.vetice(fin).dato())) {
			listaRet.agregarFinal(v.dato());
			return true;
		}
		ListaGenerica<Arista<String>> lAdyacentes = grafo.listaDeAdyacentes(v);
		lAdyacentes.comenzar();
		while ((!lAdyacentes.esVacia())&&(!encontre)) {
			arista = lAdyacentes.proximo();
			if (esValido(arista.verticeDestino().dato(),restringidos)) {
				j = arista.verticeDestino().getPosicion();
				if ((!marca[j])&&(tiempoAct + grafo.peso(v, grafo.vetice(j)) <= tiempoMax)) {
					tiempoAct += grafo.peso(v, grafo.vetice(j));
					marca[j] = true;
					listaRet.agregarFinal(v.dato());
					
					encontre = dfs (listaRet,grafo,j,fin,marca,tiempoMax,tiempoAct,restringidos);
					
					if (!encontre) {
						listaRet.eliminarEn(listaRet.tamanio());
						marca[j] = false;
					}
				}
			}
		}
		return encontre;
	}
	
	public ListaGenerica<String> recorridoBFS(Grafo<String> lugares, String destino, ListaGenerica<String> lugaresRestringidos){
		boolean[] marca = new boolean[lugares.listaDeVertices().tamanio() + 1];
		ListaGenerica<String> listaRet = new ListaEnlazadaGenerica<>();
		int inicial=0;
		int fin=0;
		for (int i=1; i<=lugares.listaDeVertices().tamanio();i++) {
			if(lugares.vetice(i).dato().equals("Ayuntamiento"))
				inicial = i;
			if(lugares.vetice(i).dato().equals(destino))
				fin = i;
		}
		if((fin != 0)&&(inicial != 0))
			marca[inicial] = true;
			bfsMinimo(listaRet,lugares,inicial,fin,marca,lugaresRestringidos);
		return listaRet;
	}
	
	private void bfsMinimo(ListaGenerica<String> listaRet, Grafo<String> grafo, int i, int fin,boolean marca[], ListaGenerica<String> restringidos) {
		ListaGenerica<Arista<String>> ady;
		ColaGenerica<Vertice<String>> q = new ColaGenerica<>();
		int [] distancias = new int [grafo.listaDeVertices().tamanio()+1];
		int [] pasando = new int [grafo.listaDeVertices().tamanio()+1];
		for (int x=1;x<=distancias.length; x++) {
			distancias[x]=Integer.MAX_VALUE;
			pasando[x] = 0;
		}
		distancias[i] = 0;
		q.encolar(grafo.listaDeVertices().elemento(i));
		marca[i] = true;
		Vertice<String> v;
		while (!q.esVacia()) { //visitar vertices de la cola
			v = q.desencolar();
			ady = grafo.listaDeAdyacentes(v);
			ady.comenzar();
			while(!ady.fin()) { //encolar adyacentes no visitados
				Arista<String> arista = ady.proximo();
				Vertice<String> w = arista.verticeDestino();
				int j = w.getPosicion();
				if(!marca[j]) {
					distancias[j] = distancias[v.getPosicion()] + 1;
					pasando[j] = v.getPosicion();
					marca[j] = true;
					q.encolar(w);
				}
			}
		}
		int act = fin;
		while (act != i) {
			listaRet.agregarInicio(grafo.vetice(act).dato());
			act = pasando[act];
		}
		listaRet.agregarInicio(grafo.vetice(i).dato());
	}
	
		
		private boolean esValido(String lugar, ListaGenerica<String> restringidos) {
			boolean valido = true;
			restringidos.comenzar();
			while ((!restringidos.fin()&&(valido))) {
				valido = (!restringidos.proximo().equals(lugar));
			}
			return valido; // devuelve true si el lugar que se le pasa por parametro no esta en la lista de restringidos
			
		}
		
	
}

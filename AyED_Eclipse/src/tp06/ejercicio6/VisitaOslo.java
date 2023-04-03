package tp06.ejercicio6;

import tp02.ejercicio2.ListaEnlazadaGenerica;
import tp02.ejercicio2.ListaGenerica;
import tp06.ejercicio3.Arista;
import tp06.ejercicio3.Grafo;
import tp06.ejercicio3.Vertice;

public class VisitaOslo {
	
	public ListaGenerica<String> paseoEnBici(Grafo<String> lugares, String destino, int maxTiempo, ListaGenerica<String> lugaresRestringidos){
		boolean[] marca = new boolean[lugares.listaDeVertices().tamanio() + 1];
		ListaGenerica<String> listaRet = new ListaEnlazadaGenerica<>();
		int inicial=0;
		int fin=0;
		int actTiempo = 0;
		for (int i=1; i<=lugares.listaDeVertices().tamanio();i++) {
			if(lugares.vetice(i).dato().equals("Ayuntamiento"))
				inicial = i;
			if(lugares.vetice(i).dato().equals(destino))
				fin = i;
		}
		if((fin != 0)&&(inicial != 0)) {
			caminoDFS(listaRet,lugares,inicial,fin,marca,maxTiempo,actTiempo,lugaresRestringidos);
		}
		return listaRet;
	}

	private boolean caminoDFS(ListaGenerica<String> listaRet, Grafo<String> grafo, int i, int fin, boolean[] marca, int tiempoMax, 
			int tiempoAct, ListaGenerica<String> restringidos) {
		
		boolean encontre = false;
		Vertice<String> v = grafo.vetice(i);
		marca[i] = true;
		listaRet.agregarFinal(v.dato());
		
		if(v.dato().equals(grafo.vetice(fin).dato()))
			return true;
		
		ListaGenerica<Arista<String>> ady = grafo.listaDeAdyacentes(v);
		ady.comenzar();
		while((!ady.fin()&&(!encontre))) {
			Arista <String> arista = ady.proximo();
			
			if (!restringidos.incluye(arista.verticeDestino().dato())) {             
				int j = arista.verticeDestino().getPosicion();    
				int tiempoSig = tiempoAct + grafo.peso(v, arista.verticeDestino());
				
				if((!marca[j]) && (tiempoSig <= tiempoMax))
					encontre = caminoDFS(listaRet,grafo,j,fin,marca,tiempoMax,tiempoSig,restringidos);
			}
		}
		
		if (!encontre) {
			listaRet.eliminarEn(listaRet.tamanio());
			marca[i] = false;
		}
		
		return encontre;
	}
	
}

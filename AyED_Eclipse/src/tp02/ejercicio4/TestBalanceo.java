package tp02.ejercicio4;

import tp02.ejercicio3.PilaGenerica;

public class TestBalanceo {
	private PilaGenerica<Character> pila;

	public TestBalanceo() {
		pila = new PilaGenerica<Character>();
	}

	public boolean estaBalanceado(String str) {
		boolean estado = true;
		if (!str.isEmpty()) {
			int i = 0;
			while ((i <= str.length()) && (estado)) {
				switch (str.charAt(i)) {
				case '{', '[', '(':
					this.pila.apilar(str.charAt(i + 0));
					break;
				case '}':
					if (pila.tope() == '{')
						pila.desapilar();
					else
						estado = false;
					break;
				case ']':
					if (pila.tope() == '[')
						pila.desapilar();
					else
						estado = false;
					break;
				case ')':
					if (pila.tope() == '(')
						pila.desapilar();
					else
						estado = false;
					break;
				}
				i++;
			}
		}
		return estado;
	}

}

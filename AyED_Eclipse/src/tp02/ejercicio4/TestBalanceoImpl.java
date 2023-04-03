package tp02.ejercicio4;

public class TestBalanceoImpl {

	public static void main(String[] args) {
		String s1 = "{()[()]}";
		String s2 = "([)]";

		TestBalanceo t = new TestBalanceo();

		System.out.println(t.estaBalanceado(s1));
		System.out.println(t.estaBalanceado(s2));

	}

}

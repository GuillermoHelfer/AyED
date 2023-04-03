package tp04.ejercicio5;

public class AreaEmpresa {
	private String area;
	private int latencia;

	public AreaEmpresa(int latencia) {
		this.setArea("contable");
		this.setLatencia(latencia);
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public int getLatencia() {
		return latencia;
	}

	public void setLatencia(int latencia) {
		this.latencia = latencia;
	}

}

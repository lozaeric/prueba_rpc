
public class Clima {
	private boolean estaCacheada = false;
	private double temperatura;
	private String descripcion;
	
	public Clima () {
		
	}
	
	public Clima (double temperatura, String descripcion) {
		this.temperatura = temperatura;
		this.descripcion = descripcion;
	}
	
	public void cachear () {
		estaCacheada = true;
	}
	
	public boolean estaCacheada () {
		return estaCacheada;
	}

	@Override
	public String toString() {
		return "Clima [estaCacheada=" + estaCacheada + ", temperatura="
				+ temperatura + ", descripcion=" + descripcion + "]";
	}
	
	

}

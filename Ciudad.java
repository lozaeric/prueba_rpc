
public class Ciudad {
	private String nombre;
	private int id;
	private Clima clima  = new Clima ();
	
	public Ciudad (int id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public int getId() {
		return id;
	}
	
	public Clima obtenerClima () {
		if (!clima.estaCacheada()) {
			clima  = Main.c.consultar (524901);
			clima.cachear();
		}
		return clima;
	}

	@Override
	public String toString() {
		return "Ciudad [nombre=" + nombre + ", id=" + id + ", clima=" + clima
				+ "]";
	}
	
	
}

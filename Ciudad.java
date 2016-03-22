import java.io.Serializable;
import java.rmi.RemoteException;


public class Ciudad implements Serializable{
	
	private String nombre;
	private int id;
	private Clima clima  = new Clima ();
	private static final long serialVersionUID = -231512405473655757L;
	
	public Ciudad (int id, String nombre) throws RemoteException {
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
			clima  = OpenWeatherImp.c.consultar (524901);
			clima.cachear();
		}
		return clima;
	}

	@Override
	public String toString() {
		return nombre+" ("+id+")";
	}
	
	
}

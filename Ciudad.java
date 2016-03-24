import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Calendar;


public class Ciudad implements Serializable{
	
	private String nombre;
	private int id;
	private Clima clima;
	private static final long serialVersionUID = -231512405473655757L;
	
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
		if (clima == null) 
			clima  = Consultador.consultar (id);
		return clima;
	}

	@Override
	public String toString() {
		return nombre+" ("+id+")";
	}
	
	public boolean revisarCache () {
		if (clima == null)
			return false;
		if (Calendar.getInstance ().getTime ().getTime ()-clima.obtenerFechaCache ()>7200000) { //7200 segundos
			clima = null;
			return true;
		}
		return false;
	}
}

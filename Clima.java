import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class Clima implements Serializable {
	private boolean estaCacheada = false;
	private double temperatura;
	private String descripcion;
	private static final long serialVersionUID = -231512405473655758L;
	
	public Clima () throws RemoteException {
		
	}
	
	public Clima (double temperatura, String descripcion) throws RemoteException {
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
		return "Temperatura: "+(temperatura-273)+", Descripcion: "+descripcion;
	}
	
	

}

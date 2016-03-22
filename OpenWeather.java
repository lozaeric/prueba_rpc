
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Toda interface RMI deve estender java.rmi.Remote e seus m�todos
 * devem lan�ar java.rmi.RemoteException.
 */
public interface OpenWeather extends Remote {

	void agregarCiudad (Ciudad c) throws RemoteException;
	
	String verCiudades () throws RemoteException;
	
	String consultarClima (int id) throws RemoteException;
	
}

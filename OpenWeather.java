
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Toda interface RMI deve estender java.rmi.Remote e seus métodos
 * devem lançar java.rmi.RemoteException.
 */
public interface OpenWeather extends Remote {

	void agregarCiudad (Ciudad c) throws RemoteException;
	
	ArrayList <Ciudad> getCiudades () throws RemoteException;
	
	String verCiudades () throws RemoteException;
	
	Clima consultarClima (int id) throws RemoteException;
	
}

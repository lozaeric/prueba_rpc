import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class OpenWeatherImp extends UnicastRemoteObject implements OpenWeather {
	private static ArrayList<Ciudad> ciudades = new ArrayList<Ciudad>();
	private static final long serialVersionUID = -231512405473655756L;

	protected OpenWeatherImp() throws RemoteException {
		super();
	}
	
	public  void agregarCiudad (Ciudad c) throws RemoteException {
		ciudades.add (c);
		Collections.sort(ciudades, new Comparator<Ciudad>() {
		    public int compare(Ciudad c1, Ciudad c2) {
		        return c1.getNombre ().compareTo(c2.getNombre());
		    }
		});
	}
	
	public  ArrayList <Ciudad> getCiudades () throws RemoteException {
		return ciudades;
	}
	
	public  String verCiudades () throws RemoteException {
		StringBuilder mostrar = new StringBuilder ();
		
		for (Ciudad c : ciudades) 
			mostrar.append(c.toString()+"\n");
		
		if (mostrar.toString().isEmpty())
			return null;
		return mostrar.toString();
	}
	
	public Clima consultarClima (int id) throws RemoteException {
		Ciudad c = null;
		
		for (Ciudad cada : ciudades) {
			if (cada.getId ()==id)
				c = cada;
		}
		if (c==null)
			return null;
		return c.obtenerClima ();
	}
	/*
	public static void main(String[] args) {
		Ciudad nueva = new Ciudad (524901, "Moscow");
		c = new Consultador ();
		System.out.println (nueva.toString ());
		System.out.println (nueva.obtenerClima());
	}*/
}

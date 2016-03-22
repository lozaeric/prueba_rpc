import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;


public class OpenWeatherImp extends UnicastRemoteObject implements OpenWeather {

	public static Consultador c;
	private static ArrayList<Ciudad> ciudades = new ArrayList<Ciudad>();
	private static final long serialVersionUID = -231512405473655756L;

	protected OpenWeatherImp() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public  void agregarCiudad (Ciudad c) throws RemoteException {
		ciudades.add (c);
	}
	
	public  String verCiudades () throws RemoteException {
		StringBuilder mostrar = new StringBuilder ();
		
		for (Ciudad c : ciudades) 
			mostrar.append(c.toString()+"\n");
		
		if (mostrar.toString().isEmpty())
			return null;
		return mostrar.toString();
	}
	
	public String consultarClima (int id) throws RemoteException {
		Ciudad c = null;
		
		for (Ciudad cada : ciudades) {
			if (cada.getId ()==id)
				c = cada;
		}
		if (c==null)
			return "ID erronea";
		return c.obtenerClima().toString();
	}
	/*
	public static void main(String[] args) {
		Ciudad nueva = new Ciudad (524901, "Moscow");
		c = new Consultador ();
		System.out.println (nueva.toString ());
		System.out.println (nueva.obtenerClima());
	}*/
}


import java.rmi.Naming;

public class Client{

	public static void main(String[] args) throws Exception {
		OpenWeather obj = (OpenWeather)Naming.lookup("OpenWeather");
		obj.agregarCiudad(new Ciudad(524901, "Moscow"));
		System.out.println(obj.verCiudades());
		System.out.println(obj.consultarClima(524901));
	}
	
}

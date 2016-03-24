import static org.junit.Assert.*;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Date;
import org.junit.Test;

public class Testing {

  @Test
  public void agregarCiudad() throws RemoteException {

    OpenWeatherImp ow = new OpenWeatherImp ();
    assertNull(ow.verCiudades());
    ow.agregarCiudad(new Ciudad (524901, "Moscow"));
    assertNotNull (ow.verCiudades());
	 assertFalse (ow.getCiudades ().get (0).revisarCache ());
  }
  
  @Test
  public void verificarListaCiudades() throws RemoteException {

    OpenWeatherImp ow = new OpenWeatherImp ();
    assertNull(ow.consultarClima (-1));
  }  
  
  @Test
  public void consultarClima () {
	  Clima c = Consultador.consultar (524901);
	  assertNotNull (c);
	  assertNotNull (c.obtenerDateCache ());
  }
  
  @Test
  public void probarCache () {
	  Ciudad ciudad = new Ciudad (1, "ciudad_prueba", new Clima (10,"clima_prueba",new Date(100)));
	  assertTrue (ciudad.revisarCache ());
	  Ciudad ciudad2 = new Ciudad (2, "ciudad_prueba", new Clima (20,"clima_prueba",Calendar.getInstance ().getTime ()));
	  assertFalse (ciudad2.revisarCache ());
  }
    
  
} 

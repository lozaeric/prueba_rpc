import static org.junit.Assert.*;
import java.rmi.RemoteException;
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
	  assertNotNull (c.obtenerFechaCache ());
  }
  
} 

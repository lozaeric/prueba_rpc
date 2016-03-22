import static org.junit.Assert.*;

import java.rmi.RemoteException;

import org.junit.Test;

public class Testing {

  @Test
  public void cargaCiudad() throws RemoteException {

    OpenWeatherImp ow = new OpenWeatherImp ();
    assertNull(ow.verCiudades());
    ow.agregarCiudad(new Ciudad (1, "Prueba"));
    assertNotNull (ow.verCiudades());
  }

} 

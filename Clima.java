import java.io.Serializable;
import java.util.Date;


public class Clima implements Serializable {
	private Date ultimaCache;
	private float temperatura;
	private String descripcion;
	private static final long serialVersionUID = -231512405473655758L;
	
	public Clima (float temperatura, String descripcion, Date ultimaCache) {
		this.temperatura = temperatura-273;
		this.descripcion = descripcion;
		this.ultimaCache = ultimaCache;
	}
	
	public Long obtenerFechaCache () {
		if (ultimaCache == null)
			return null;
		return ultimaCache.getTime ();
	}
	
	public float getTemperatura () {
		return temperatura;
	}
	
	public String getDesc () {
		return descripcion;
	}

	@Override
	public String toString() {
		return "Temperatura: "+temperatura+", Descripcion: "+descripcion+ ", Ultima cache: "+ultimaCache.toString ();
	}
	
	

}

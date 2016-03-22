
public class Main {
	public static Consultador c;
	
	
	public static void main(String[] args) {
		Ciudad nueva = new Ciudad (524901, "Moscow");
		c = new Consultador ();
		System.out.println (nueva.toString ());
		System.out.println (nueva.obtenerClima());
	}

}

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;


public class Temporizador implements ActionListener {
	private Client cliente;
	
	public Temporizador(Client cliente) {
		this.cliente = cliente;
		(new Timer(60000,this)).start(); //cada minuto se revisa la fecha de los caches
	}
	public void actionPerformed(ActionEvent e) {
		try {
	      cliente.revisarCache ();
      }
      catch (Exception err) {
	      err.printStackTrace();
      }
	}
}
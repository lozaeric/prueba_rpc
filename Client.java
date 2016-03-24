
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.text.Format;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;

public class Client extends JFrame {
	private OpenWeather ow;
	private final JLabel id = new JLabel ("ID"), nombre = new JLabel ("Nombre"),
					         temperatura = new JLabel ("Temperatura"), descripcion = new JLabel ("Descripcion");
	private final JTextField campo_id = new JTextField (5), campo_nombre = new JTextField (8),
									 campo_temperatura = new JTextField (4), campo_descripcion = new JTextField (7);
	private final JComboBox<String> ciudades = new JComboBox<String> ();
	private final JButton agregar = new JButton ("Agregar"), consultar  = new JButton ("Consultar"), verTodas  = new JButton ("Ver Todas");
	
	public Client () throws Exception {
		ow = (OpenWeather)Naming.lookup("OpenWeather");
		setTitle ("Open Weather");
		setDefaultCloseOperation (EXIT_ON_CLOSE);
		setLayout (new FlowLayout ());
		setLocation (150,250);
		setSize (320,160);
		new Temporizador (this);
		agregar.addActionListener(
			new ActionListener () {
				public void actionPerformed (ActionEvent e) {
					try {
	                 ow.agregarCiudad (new Ciudad (Integer.parseInt (campo_id.getText ()), campo_nombre.getText ()));
	                 ciudades.removeAllItems ();
	                 for (Ciudad c : ow.getCiudades ())
	               	  ciudades.addItem(String.valueOf (c.getId ()));
                 }
                 catch (Exception err) {
	                 err.printStackTrace();
                 }
				}
			}
		);
		consultar.addActionListener(
				new ActionListener () {
					public void actionPerformed (ActionEvent e) {
						try {
		                 Clima c = ow.consultarClima (Integer.parseInt (ciudades.getSelectedItem ().toString ()));
		                 DecimalFormat formato = new DecimalFormat("00.00");
		                 campo_temperatura.setText (formato.format (c.getTemperatura ()));
		                 campo_descripcion.setText (c.getDesc ());
	                 }
	                 catch (Exception err) {
		                 err.printStackTrace();
	                 }
					}
				}
			);
		verTodas.addActionListener(
				new ActionListener () {
					public void actionPerformed (ActionEvent e) {
						try {
							JOptionPane.showMessageDialog (Client.this, ow.verCiudades (), "Ciudades cargadas", JOptionPane.INFORMATION_MESSAGE);
	                 }
	                 catch (Exception err) {
		                 err.printStackTrace();
	                 }
					}
				}
			);
		add (id);
		add (campo_id);
		add (nombre);
		add (campo_nombre);
		add (agregar);
		add (id);
		add (ciudades);
		add (consultar);
		add (verTodas);
		add (temperatura);
		add (campo_temperatura);
		add (descripcion);
		add (campo_descripcion);
 		setVisible (true);
	}
	
	public void revisarCache () throws Exception {
		for (Ciudad c : ow.getCiudades ()) 
			c.revisarCache ();
	}
 
	public static void main(String[] args) throws Exception {
		new Client ();
	}
	
}

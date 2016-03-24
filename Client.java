
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
	private final JTextField campo_id = new JTextField (5), campo_nombre = new JTextField (8), campo_fecha = new JTextField (16),
									 campo_temperatura = new JTextField (4), campo_descripcion = new JTextField (8);
	private final JComboBox<String> ciudades = new JComboBox<String> ();
	private final JButton agregar = new JButton ("Agregar"), consultar  = new JButton ("Consultar"), verTodas  = new JButton ("Ver Todas");
	
	public Client () throws Exception {
		ow = (OpenWeather)Naming.lookup("OpenWeather");
		setTitle ("Open Weather");
		setDefaultCloseOperation (EXIT_ON_CLOSE);
		setLayout (new FlowLayout ());
		setLocation (150,250);
		setSize (330,160);
		new Temporizador (this);
      for (Ciudad c : ow.getCiudades ())
    	  ciudades.addItem(String.valueOf (c.getId ()));
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
		                 campo_fecha.setText (c.obtenerDateCache ().toString ());
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
		campo_fecha.setEditable (false);
		campo_descripcion.setEditable (false);
		campo_temperatura.setEditable (false);
		add (new JLabel ("ID"));
		add (campo_id);
		add (new JLabel ("Nombre"));
		add (campo_nombre);
		add (agregar);
		add (new JLabel ("ID"));
		add (ciudades);
		add (consultar);
		add (verTodas);
		add (new JLabel ("Temperatura"));
		add (campo_temperatura);
		add (new JLabel ("Descripcion"));
		add (campo_descripcion);
		add (new JLabel ("Ultima Cache"));
		add (campo_fecha);
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

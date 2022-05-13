package Presentacion.Ingredientes;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Negocio.FactoriaAbstractaNegocio.FactoriaAbstractaNegocio;
import Presentacion.Evento;
import Presentacion.IGUI;
import Presentacion.Location;
import Presentacion.Controlador.Controlador;


public class VistaMostrarIngredientes extends JFrame implements IGUI {
	
	public VistaMostrarIngredientes(){
		
		super("Leer Ingrediente");
		Controlador.getInstance().registerView(Evento.MOSTRAR_INGREDIENTE, this);
		initGUI();
		
	}
	
	private void initGUI() {
		JPanel panel = new JPanel();
		this.add(panel);
		panel.setLayout( new BoxLayout(panel, BoxLayout.Y_AXIS) );
		
		JPanel upPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel.add(upPanel);
		upPanel.add( new JLabel("Introduzca el ID del ingrediente que desee leer"));
		
		JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,5,5));
		panel.add(centerPanel);
		centerPanel.add(new JLabel("ID:"));
		final JTextField tID = new JTextField(20);
		centerPanel.add(tID);
		
		JPanel downPanel = new JPanel();
		panel.add(downPanel);
		JButton aceptar = new JButton("Aceptar");
		JButton cancelar = new JButton("Cancelar");
		downPanel.add(aceptar);
		downPanel.add(cancelar);
		
		aceptar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				try {
					int ID = Integer.parseInt(tID.getText());
					if(FactoriaAbstractaNegocio.getInstance().createSAIngredientes().read(ID)==null) {
						throw new Exception("ID del Ingrediente no existente");
					}
					Controlador.getInstance().accion(Evento.MOSTRAR_INGREDIENTE, ID);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}

			}

		});

		cancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		
		pack();
		this.setLocation(Location.LOCATION_VIEW_W, Location.LOCATION_VIEW_H);
		this.setVisible(true);
		this.setResizable(false);
		
	}


	public void actualizar(int evento, Object datos) {
		if (evento == Evento.RES_MOSTRAR_INGREDIENTE_OK)
			JOptionPane.showMessageDialog(this, datos.toString());
		else if (evento == Evento.RES_MOSTRAR_INGREDIENTE_KO)
			JOptionPane.showMessageDialog(this, "No se ha podido leer el ingrediente");
	}
}
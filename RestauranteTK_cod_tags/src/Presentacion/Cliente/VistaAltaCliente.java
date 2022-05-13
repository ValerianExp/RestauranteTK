/**
 * 
 */
package Presentacion.Cliente;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Negocio.Cliente.TCliente;
import Presentacion.Evento;
import Presentacion.IGUI;
import Presentacion.Location;
import Presentacion.Controlador.Controlador;

public class VistaAltaCliente extends JFrame implements IGUI {

	public VistaAltaCliente() {
		super("Alta Cliente");
		Controlador.getInstance().registerView(Evento.ALTA_CLIENTE, this);
		initGUI();
	}

	private void initGUI() {
		
		JPanel panel = new JPanel();
		this.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JPanel upPanel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel.add(upPanel1);
		upPanel1.add(new JLabel("Introduzca los datos del cliente que quiere añadir"));

		JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel.add(centerPanel);
		centerPanel.add(new JLabel("DNI:"));
		final JTextField tDNI = new JTextField(20);
		centerPanel.add(tDNI);
		centerPanel.add(new JLabel("Nombre:"));
		final JTextField tNombre = new JTextField(20);
		centerPanel.add(tNombre);

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
					String DNIT = tDNI.getText();
					String nombre = tNombre.getText();
					
					if(DNIT.equals("")||nombre.equals("")) 
						throw new IllegalArgumentException("Ningun apartado pueden ser vacio");
					
					TCliente toc = new TCliente(0, nombre, DNIT, true);
					Controlador.getInstance().accion(Evento.ALTA_CLIENTE, toc);
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

	@Override
	public void actualizar(int evento, Object datos) {
		if (evento == Evento.RES_ALTA_CLIENTE_OK)
			JOptionPane.showMessageDialog(this, "Se ha creado correctamente el cliente con id " + (Integer) datos);
		else if (evento == Evento.RES_ALTA_CLIENTE_KO)
			JOptionPane.showMessageDialog(this, "No se ha podido crear el cliente");
	}

}
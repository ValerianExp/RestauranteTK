/**
 * 
 */
package Presentacion.Cliente;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Negocio.Cliente.TCliente;
import Negocio.FactoriaAbstractaNegocio.FactoriaAbstractaNegocio;
import Presentacion.Evento;
import Presentacion.IGUI;
import Presentacion.Location;
import Presentacion.Controlador.Controlador;

public class VistaActualizarCliente extends JFrame implements IGUI {

	public VistaActualizarCliente() {

		super("Actualizar Cliente");
		Controlador.getInstance().registerView(Evento.ACTUALIZAR_CLIENTE, this);
		initGUI();

	}

	void initGUI() {

		JPanel panel = new JPanel();
		this.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JPanel upPanel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel.add(upPanel1);
		upPanel1.add(new JLabel("Introduzca el id del cliente que quiere actualizar"));

		JPanel upPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
		panel.add(upPanel);
		upPanel.add(new JLabel("ID:"));
		final JTextField tID = new JTextField(20);
		upPanel.add(tID);

		JPanel upPanel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel.add(upPanel2);
		upPanel2.add(new JLabel("Introduzca los datos del cliente que quiere actualizar"));

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
					if (tID.getText().equals(""))
						throw new Exception("No se ha proporcionado ningun ID");
					int ID = Integer.parseInt(tID.getText());

					String DNIT = "", nombre = "";
					DNIT = tDNI.getText();
					nombre = tNombre.getText();
					if(FactoriaAbstractaNegocio.getInstance().createSACliente().read(ID)==null)
						throw new Exception("Id incorrecto");
					if (DNIT.equals("") && nombre.equals(""))
						throw new Exception("No se ha proporcionado ningun dato");
					if (DNIT.equals(""))
						DNIT = FactoriaAbstractaNegocio.getInstance().createSACliente().read(ID).getDNI();
					if (nombre.equals(""))
						nombre = FactoriaAbstractaNegocio.getInstance().createSACliente().read(ID).getNombre();

					TCliente datos = new TCliente(ID, nombre, DNIT, true);

					Controlador.getInstance().accion(Evento.ACTUALIZAR_CLIENTE, datos);
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
		if (evento == Evento.RES_ACTUALIZAR_CLIENTE_OK)
			JOptionPane.showMessageDialog(this, "Se ha actualizado correctamente el cliente de id " + (Integer) datos);
		else if (evento == Evento.RES_CLIENTE_ID_NO_EXISTENTE)
			JOptionPane.showMessageDialog(this, "El id del cliente no existe");
		else if (evento == Evento.RES_CLIENTE_DNI_REPETIDO)
			JOptionPane.showMessageDialog(this, "El dni del cliente se repite prueba a poner uno diferente");
		else if (evento == Evento.RES_ACTUALIZAR_CLIENTE_KO)
			JOptionPane.showMessageDialog(this, "No se ha podido actualizar el cliente");
	}

}
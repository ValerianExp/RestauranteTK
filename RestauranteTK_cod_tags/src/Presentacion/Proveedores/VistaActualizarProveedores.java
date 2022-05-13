/**
 * 
 */
package Presentacion.Proveedores;

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
import Negocio.Proveedores.TProveedores;
import Presentacion.Evento;
import Presentacion.IGUI;
import Presentacion.Location;
import Presentacion.Controlador.Controlador;

public class VistaActualizarProveedores extends JFrame implements IGUI {

	public VistaActualizarProveedores() {

		super("Actualizar proveedores");
		Controlador.getInstance().registerView(Evento.ACTUALIZAR_PROVEEDOR, this);
		initGUI();

	}

	void initGUI() {

		JPanel panel = new JPanel();
		this.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JPanel upPanel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel.add(upPanel1);
		upPanel1.add(new JLabel("Introduzca el id del proveedor que quiere actualizar"));

		JPanel upPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
		panel.add(upPanel);
		upPanel.add(new JLabel("ID:"));
		final JTextField tID = new JTextField(20);
		upPanel.add(tID);

		JPanel upPanel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel.add(upPanel2);
		upPanel2.add(new JLabel("Introduzca los datos del proveedor que quiere actualizar"));

		JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel.add(centerPanel);
		centerPanel.add(new JLabel("NIF:"));
		final JTextField tNIF = new JTextField(20);
		centerPanel.add(tNIF);
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
					if(FactoriaAbstractaNegocio.getInstance().createSAProveedores().read(ID)==null) {
						throw new Exception("ID del Proveedor no existente");
					}
					String nif = tNIF.getText();
					String nombre = tNombre.getText();

					if (nif.equals("") && nombre.equals(""))
						throw new Exception("No se ha proporcionado ningun dato");
					if (nif.equals(""))
						nif = FactoriaAbstractaNegocio.getInstance().createSAProveedores().read(ID).getNIF();
					if (nombre.equals(""))
						nombre = FactoriaAbstractaNegocio.getInstance().createSAProveedores().read(ID).getNombre();

					TProveedores datos = new TProveedores(ID, nombre, nif, true);
					Controlador.getInstance().accion(Evento.ACTUALIZAR_PROVEEDOR, datos);
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
		// begin-user-code
		if (evento == Evento.RES_ACTUALIZAR_PROVEEDOR_OK)
			JOptionPane.showMessageDialog(this,
					"Se ha actualizado correctamente el proveedor de id " + (Integer) datos);
		else if (evento == Evento.RES_PROVEEDOR_ID_NO_EXISTENTE)
			JOptionPane.showMessageDialog(this, "El id del proveedor no existe");
		else if (evento == Evento.RES_PROVEEDOR_NIF_REPETIDO)
			JOptionPane.showMessageDialog(this, "El nif del proveedor se repite prueba a poner uno diferente");
		else if (evento == Evento.RES_ACTUALIZAR_PROVEEDOR_KO)
			JOptionPane.showMessageDialog(this, "No se ha podido actualizar el proveedor");

		// end-user-code
	}
}
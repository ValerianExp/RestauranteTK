/**
 * 
 */
package Presentacion.Empleado;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.CardLayout;

import javax.swing.*;

import Negocio.Empleado.TCamarero;
import Negocio.Empleado.TChef;
import Negocio.Empleado.TEmpleado;
import Presentacion.Evento;
import Presentacion.IGUI;
import Presentacion.Location;
import Presentacion.Controlador.Controlador;

public class VistaAltaEmpleado extends JFrame implements IGUI {

	public VistaAltaEmpleado() {
		setTitle("Alta empleado");
		Controlador.getInstance().registerView(Evento.ALTA_EMPLEADO, this);
		initGUI();
	}

	private void initGUI() {

		JPanel panel = new JPanel();
		this.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JPanel upPanel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel.add(upPanel1);
		upPanel1.add(new JLabel("Introduzca los datos del empleado que quiere añadir"));

		JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,5, 5));
		panel.add(centerPanel);

		centerPanel.add(new JLabel("DNI:"));
		final JTextField tDNI = new JTextField(20);
		centerPanel.add(tDNI);

		centerPanel.add(new JLabel("Nombre:"));
		final JTextField tNombre = new JTextField(20);
		centerPanel.add(tNombre);

		centerPanel.add(new JLabel("Salario"));
		final JTextField tSalario = new JTextField(20);
		centerPanel.add(tSalario);

		ButtonGroup buttonGroup = new ButtonGroup();
		JRadioButton chefButton = new JRadioButton("Chef", true);
		JRadioButton camareroButton = new JRadioButton("Camarero");
		buttonGroup.add(chefButton);
		buttonGroup.add(camareroButton);

		//Chef
		JPanel chefPanel = new JPanel();

		JLabel lEstrellas = new JLabel("Estrellas");
		JSpinner spinner = new JSpinner(new SpinnerNumberModel(0, 0, 5, 1));
		chefPanel.add(lEstrellas);
		chefPanel.add(spinner);
		panel.add(chefPanel);

		//Camarero
		JPanel camareroPanel = new JPanel();
		camareroPanel.add(new JLabel("Idiomas"));
		final JTextField tIdiomas = new JTextField(20);
		camareroPanel.add(tIdiomas);
		panel.add(camareroPanel);

		//Cards
		JPanel cardPanel = new JPanel();
		CardLayout cardLayout = new CardLayout();
		cardPanel.setLayout(cardLayout);
		cardPanel.add(camareroPanel, "camarero");
		cardPanel.add(chefPanel, "chef");
		cardPanel.add(chefButton);
		cardPanel.add(camareroButton);

		chefButton.addActionListener(e -> cardLayout.show(cardPanel, "chef"));
		camareroButton.addActionListener(e -> cardLayout.show(cardPanel, "camarero"));
		chefButton.setVisible(true);
		camareroButton.setVisible(true);
		panel.add(chefButton);
		panel.add(camareroButton);
		panel.add(cardPanel);

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
					TEmpleado emp = null;
					String dni = tDNI.getText();
					String nombre = tNombre.getText();
					if(dni.equalsIgnoreCase("")||nombre.equalsIgnoreCase("")||tSalario.getText().equalsIgnoreCase(""))
						throw new IllegalArgumentException("Ningun apartado pueden ser vacio");
					int salario = Integer.parseInt(tSalario.getText());
					if (chefButton.isSelected()) {
						int estrellas = (int) spinner.getValue();
						emp = new TChef(0, nombre, dni, salario, estrellas, true);
					} else if (camareroButton.isSelected()) {
						String idiomas = tIdiomas.getText();
						if(idiomas.equalsIgnoreCase(""))
							throw new IllegalArgumentException("Ningun apartado pueden ser vacio");
						emp = new TCamarero(0, nombre, dni, idiomas, salario, true);
					}
					Controlador.getInstance().accion(Evento.ALTA_EMPLEADO, emp);
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
		// begin-user-code
		if (evento == Evento.RES_ALTA_EMPLEADO_OK) {
			JOptionPane.showMessageDialog(this, "Se ha creado correctamente el empleado con id " + (Integer) datos);
		} else if (evento == Evento.RES_ALTA_EMPLEADO_KO) {
			JOptionPane.showMessageDialog(this, "No se ha podido crear el empleado");
		}
		// end-user-code
	}
}
/**
 * 
 */
package Presentacion.Empleado;

import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import Negocio.Empleado.TCamarero;
import Negocio.Empleado.TChef;
import Negocio.Empleado.TEmpleado;
import Negocio.FactoriaAbstractaNegocio.FactoriaAbstractaNegocio;
import Presentacion.Evento;
import Presentacion.IGUI;
import Presentacion.Location;
import Presentacion.Controlador.Controlador;

public class VistaActualizarEmpleado extends JFrame implements IGUI {

	public VistaActualizarEmpleado() {
		setTitle("Actualiza empleado");
		Controlador.getInstance().registerView(Evento.ACTUALIZAR_EMPLEADO, this);
		initGUI();
	}

	private void initGUI() {

		JPanel panel = new JPanel();
		this.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JPanel upPanel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel.add(upPanel1);
		upPanel1.add(new JLabel("Introduzca el id del empleado que quiere actualizar"));

		JPanel upPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,5,5));
		panel.add(upPanel);
		upPanel.add(new JLabel("ID:"));
		final JTextField tID = new JTextField(20);
		upPanel.add(tID);

		JPanel upPanel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel.add(upPanel2);
		upPanel2.add(new JLabel("Introduzca los datos del empleado que quiere actualizar"));

		JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,5,5));
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
		chefPanel.add(new JLabel("por defecto se marcarán 0 estrellas"));
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
					
					if (tID.getText().equals("")) throw new Exception("No se ha proporcionado ningun ID");
					int id = Integer.parseInt(tID.getText());
					
					TEmpleado emp = null;
					String dni = tDNI.getText();
					String nombre = tNombre.getText();
					int salario;
					boolean ok = true;
					if(FactoriaAbstractaNegocio.getInstance().createSAEmpleado().read(id)==null)
						throw new Exception("Id incorrecto");
					if (dni.equals("") && nombre.equals("") && tSalario.getText().equals("")) ok = false;
					if (camareroButton.isSelected()) {
						String idiomas = tIdiomas.getText();
						if(!ok && (idiomas.equals(""))) throw new Exception("No se ha proporcionado ningun dato");
					}
					if (dni.equals("")) dni = FactoriaAbstractaNegocio.getInstance().createSAEmpleado().read(id).getDNI();
					if (nombre.equals(""))nombre = FactoriaAbstractaNegocio.getInstance().createSAEmpleado().read(id).getNombre();
					if (tSalario.getText().equals("") ) salario = FactoriaAbstractaNegocio.getInstance().createSAEmpleado().read(id).getSalario();
					else salario = Integer.parseInt(tSalario.getText());

					if (chefButton.isSelected()) {
						int estrellas = (int) spinner.getValue();
						emp = new TChef(id, nombre, dni, salario, estrellas, true);
					} else if (camareroButton.isSelected()) {
						String idiomas = tIdiomas.getText();
						if (idiomas.equals("")) {
							TCamarero c = (TCamarero) FactoriaAbstractaNegocio.getInstance().createSAEmpleado().read(id);
							idiomas = c.getIdiomas();
						}
						emp = new TCamarero(id, nombre, dni, idiomas, salario, true);
					}
					Controlador.getInstance().accion(Evento.ACTUALIZAR_EMPLEADO, emp);
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
		if (evento == Evento.RES_ACTUALIZAR_EMPLEADO_OK)
			JOptionPane.showMessageDialog(this, "Se ha actualizado correctamente el empleado de id " + (Integer) datos);
		else if (evento == Evento.RES_ACTUALIZAR_EMPLEADO_KO)
			JOptionPane.showMessageDialog(this, "No se ha podido actualizar el empleado");
	}
}
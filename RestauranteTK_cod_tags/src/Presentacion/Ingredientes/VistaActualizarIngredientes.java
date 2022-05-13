/**
 * 
 */
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
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import Negocio.FactoriaAbstractaNegocio.FactoriaAbstractaNegocio;
import Negocio.Ingredientes.TIngredientes;
import Presentacion.Evento;
import Presentacion.IGUI;
import Presentacion.Location;
import Presentacion.Controlador.Controlador;


public class VistaActualizarIngredientes extends JFrame implements IGUI {

	public VistaActualizarIngredientes() {
		super("Actualizar Ingrediente");
		Controlador.getInstance().registerView(Evento.ACTUALIZAR_INGREDIENTE, this);
		initGUI();
	}

	private void initGUI() { 

		JPanel panel = new JPanel();
		this.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JPanel upPanel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel.add(upPanel1);
		upPanel1.add(new JLabel("Introduzca el id del ingrediente que quiere actualizar"));

		JPanel upPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
		panel.add(upPanel);
		upPanel.add(new JLabel("ID:"));
		final JTextField tID = new JTextField(20);
		upPanel.add(tID);

		JPanel upPanel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel.add(upPanel2);
		upPanel2.add(new JLabel("Introduzca los datos del ingrediente que quiere añadir"));

		JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel.add(centerPanel);
		
		centerPanel.add(new JLabel("Nombre:"));
		final JTextField tNombre = new JTextField(20);
		centerPanel.add(tNombre);

		centerPanel.add(new JLabel("Cantidad:"));
		final JTextField tCantidad = new JTextField(20);
		centerPanel.add(tCantidad);
		
		JPanel centerPanel2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel.add(centerPanel2);
		
		centerPanel2.add(new JLabel("ID Proveedor:"));
		final JTextField tProveedor = new JTextField(20);
		centerPanel2.add(tProveedor);
		
		JRadioButton bGluten = new JRadioButton("Tiene Gluten ");
		centerPanel2.add(bGluten);
		
		centerPanel2.add(new JLabel("(por defecto se marcará SIN gluten)"));
		
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
					int ID = Integer.parseInt(tID.getText());
					if(FactoriaAbstractaNegocio.getInstance().createSAIngredientes().read(ID)==null) {
						throw new Exception("ID del Ingrediente no existente");
					}
					int cantidad, proveedor;
					String nombre = tNombre.getText();
					
					boolean gluten = false;
					if(bGluten.isSelected()) {gluten = true;}

					if (tCantidad.getText().equals("") && nombre.equals("") && tProveedor.getText().equals("")) throw new Exception("No se ha proporcionado ningun dato");
					if (tCantidad.getText().equals("")) cantidad = FactoriaAbstractaNegocio.getInstance().createSAIngredientes().read(ID).getCantidad();
					else  cantidad = Integer.parseInt(tCantidad.getText());
					if (nombre.equals(""))nombre = FactoriaAbstractaNegocio.getInstance().createSAIngredientes().read(ID).getNombre();
					if (tProveedor.getText().equals("")) proveedor = FactoriaAbstractaNegocio.getInstance().createSAIngredientes().read(ID).getIdProveedor();
					else proveedor = Integer.parseInt(tProveedor.getText());
					
					TIngredientes toc = new TIngredientes(ID, gluten, nombre, cantidad, proveedor, true);
					Controlador.getInstance().accion(Evento.ACTUALIZAR_INGREDIENTE, toc);
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
		if (evento == Evento.RES_ACTUALIZAR_INGREDIENTE_OK)
			JOptionPane.showMessageDialog(this, "Se ha actualizado correctamente el ingrediente con id " + (Integer) datos);
		else if (evento == Evento.RES_ACTUALIZAR_INGREDIENTE_KO)
			JOptionPane.showMessageDialog(this, "No se ha podido actualizar el ingrediente");
	}
}
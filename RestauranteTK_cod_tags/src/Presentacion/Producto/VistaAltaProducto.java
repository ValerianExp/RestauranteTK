
package Presentacion.Producto;

import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import Negocio.Empleado.TEmpleado;
import Negocio.FactoriaAbstractaNegocio.FactoriaAbstractaNegocio;
import Negocio.FactoriaAbstractaNegocio.FactoriaNegocioImp;
import Negocio.Ingredientes.TIngredientes;
import Negocio.Producto.TBebida;
import Negocio.Producto.TComida;
import Negocio.Producto.TLineaProducto;
import Negocio.Producto.TProducto;
import Presentacion.Evento;
import Presentacion.IGUI;
import Presentacion.Location;
import Presentacion.Controlador.Controlador;
import Presentacion.Factoria.FactoriaPresentacion;


public class VistaAltaProducto extends JFrame implements IGUI {

	private Collection<TLineaProducto> ingredientes;

	public VistaAltaProducto() {
		setTitle("Alta Producto");
		Controlador.getInstance().registerView(Evento.ALTA_PRODUCTO, this);
		ingredientes = new ArrayList<>();
		initGUI();
	}

	private void initGUI() {

		JPanel panel = new JPanel();
		this.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JPanel upPanel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel.add(upPanel1);
		upPanel1.add(new JLabel("Introduzca los datos del producto que quiere añadir"));

		JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,5, 5));
		panel.add(centerPanel);

		centerPanel.add(new JLabel("Nombre:"));
		final JTextField tNombre = new JTextField(20);
		centerPanel.add(tNombre);

		centerPanel.add(new JLabel("Precio"));
		final JTextField tPrecio = new JTextField(20);
		centerPanel.add(tPrecio);
 
		centerPanel.add(new JLabel("Chef:"));
		final JTextField tChef = new JTextField(20);
		centerPanel.add(tChef);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		JRadioButton bebidaButton = new JRadioButton("Bebida", true);
		JRadioButton comidaButton = new JRadioButton("Comida");
		buttonGroup.add(bebidaButton);
		buttonGroup.add(comidaButton); 

		//Bebida
		JPanel bebidaPanel = new JPanel();

		JLabel lMarca = new JLabel("Marca");
		final JTextField tMarca = new JTextField(20);
		bebidaPanel.add(lMarca);
		bebidaPanel.add(tMarca);
		panel.add(bebidaPanel);

		//Comida
		JPanel comidaPanel = new JPanel();
		JButton mostrarIn = new JButton("Mostrar Ingredientes");
		comidaPanel.add(mostrarIn);
		
		comidaPanel.add(new JLabel("Id ingrediente y cantidad"));
		final JTextField tIngrediente = new JTextField(10);
		JSpinner sCantidad = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
		comidaPanel.add(tIngrediente);
		comidaPanel.add(sCantidad);
		
		
		JButton add = new JButton("ADD");
		comidaPanel.add(add);
		JButton remove = new JButton("REMOVE");
		comidaPanel.add(remove);
		
		JTextField tShow = new JTextField(10);
		tShow.setEditable(false);
		JScrollBar scroll = new JScrollBar(JScrollBar.VERTICAL);
		scroll.setModel(tShow.getHorizontalVisibility());
		comidaPanel.add(tShow);
		comidaPanel.add(scroll);
		
		mostrarIn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				FactoriaPresentacion.getInstance().createView(Evento.MOSTRAR_TODOS_INGREDIENTES);
			}
			
		});
		
		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int ID = Integer.parseInt(tIngrediente.getText());
					if (tIngrediente.getText().equals("")) throw new Exception("No se ha proporcionado ningun ingrediente");
					int cantidad = (int) sCantidad.getValue();
					sCantidad.setValue(1);
					tIngrediente.setText(null);			
					
					TIngredientes ingrediente = FactoriaAbstractaNegocio.getInstance().createSAIngredientes().read(ID);
					if(ingrediente == null)throw new Exception("El ingrediente no existe");
					
					TLineaProducto i =  new TLineaProducto(ID, cantidad,ingrediente.getGluten());
					if(!ingredientes.contains(i))ingredientes.add(i); 
					else{throw new Exception("El producto ya contiene el ingredienete");}
					
					String s = "";
					for(TLineaProducto in: ingredientes) {s += in.getIdIngrediente() + "-" + in.getCantidad() + " "; }
					tShow.setText(s);
				}
				catch(Exception ex){
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		
		remove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int ID = Integer.parseInt(tIngrediente.getText());
					if (tIngrediente.getText().equals("")) throw new Exception("No se ha proporcionado ningun ingrediente");
					if(FactoriaAbstractaNegocio.getInstance().createSAIngredientes().read(ID)==null) {
						throw new Exception("ID del Ingrediente no existente");
					}
					tIngrediente.setText(null);					

					ingredientes.remove( new TLineaProducto(ID, 0,false) );//cambio

					String s = "";
					for(TLineaProducto in: ingredientes) {s += in.getIdIngrediente() + "-" + in.getCantidad() + " "; }
					tShow.setText(s);
				}
				catch(Exception ex){
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		
		
		panel.add(comidaPanel);

		//Cards
		JPanel cardPanel = new JPanel();
		CardLayout cardLayout = new CardLayout();
		cardPanel.setLayout(cardLayout);
		cardPanel.add(comidaPanel, "comida");
		cardPanel.add(bebidaPanel, "bebida");
		cardPanel.add(bebidaButton);
		cardPanel.add(comidaButton);

		bebidaButton.addActionListener(e -> cardLayout.show(cardPanel, "bebida"));
		comidaButton.addActionListener(e -> cardLayout.show(cardPanel, "comida"));
		bebidaButton.setVisible(true);
		comidaButton.setVisible(true);
		panel.add(bebidaButton);
		panel.add(comidaButton);
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
					TProducto emp = null;
				
					String nombre = tNombre.getText();
					
					if(tPrecio.getText().equals("")|| tChef.getText().equals("")||nombre.equals(""))
						throw new IllegalArgumentException("Ningun apartado pueden ser vacio");
					
					int precio = Integer.parseInt(tPrecio.getText());
					int chef =  Integer.parseInt(tChef.getText());
					TEmpleado em = FactoriaNegocioImp.getInstance().createSAEmpleado().read(chef);
					if(em == null || !em.getTipoEmpleado().equals("chef")) 
						throw new IllegalArgumentException("Id de chef no existente");
					
					if (bebidaButton.isSelected()) {
						String marca = tMarca.getText();
						if(marca.equals("")) throw new IllegalArgumentException("Ningun apartado pueden ser vacio");
						emp = new TBebida(0, precio, nombre, chef, marca, true);
					} else if (comidaButton.isSelected()) {
						emp = new TComida(0, precio, nombre, chef, ingredientes, true);
					}
					Controlador.getInstance().accion(Evento.ALTA_PRODUCTO, emp);
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
		if (evento == Evento.RES_ALTA_PRODUCTO_OK) {
			JOptionPane.showMessageDialog(this, "Se ha creado correctamente el producto con id " + (Integer) datos);
		} else if (evento == Evento.RES_ALTA_PRODUCTO_KO) {
			JOptionPane.showMessageDialog(this, "No se ha podido crear el producto");
		}
		// end-user-code
	}

}
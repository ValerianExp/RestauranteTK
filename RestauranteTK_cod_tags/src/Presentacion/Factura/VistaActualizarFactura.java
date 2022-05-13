
package Presentacion.Factura;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import Integracion.Factura.Tipodepago;
import Negocio.Empleado.TEmpleado;
import Negocio.FactoriaAbstractaNegocio.FactoriaAbstractaNegocio;
import Negocio.FactoriaAbstractaNegocio.FactoriaNegocioImp;
import Negocio.Factura.TCarrito;
import Negocio.Factura.TFacturaConProductos;
import Negocio.Factura.TLineaFactura;
import Presentacion.Evento;
import Presentacion.IGUI;
import Presentacion.Location;
import Presentacion.Controlador.Controlador;
import Presentacion.Factoria.FactoriaPresentacion;


public class VistaActualizarFactura extends JFrame implements IGUI {

private Collection<TLineaFactura> productos; //producto + cantidad
	
	public VistaActualizarFactura() {
		super("Alta Factura");
		Controlador.getInstance().registerView(Evento.ACTUALIZAR_FACTURA, this);
		productos= new ArrayList<TLineaFactura>();
		initGUI();
	}

	private void initGUI() {
		
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
		upPanel2.add(new JLabel("Introduzca los datos de la factura que quiere añadir"));

		JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel.add(centerPanel);
		centerPanel.add(new JLabel("ID Cliente"));
		final JTextField tCliente = new JTextField(10);
		centerPanel.add(tCliente);
		centerPanel.add(new JLabel("ID Camarero:"));
		final JTextField tCamarero = new JTextField(10);
		centerPanel.add(tCamarero);
		
		centerPanel.add(new JLabel("Tipo de pago:"));
		JComboBox tPago = new JComboBox();
		tPago.addItem(Tipodepago.Efectivo);
		tPago.addItem(Tipodepago.Tarjeta);
		centerPanel.add(tPago);
		
		centerPanel.add(new JLabel(" (por defecto se marcará Efectivo)"));
		
		//Productos
		JPanel centerPanel2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel.add(centerPanel2);
		
		JButton mostrarPro = new JButton("Mostrar Productos"); 
		centerPanel2.add(mostrarPro);
				
		JButton load = new JButton("LOAD");
		centerPanel2.add(load);
		
		centerPanel2.add(new JLabel("ID Producto y cantidad"));
		final JTextField tProducto = new JTextField(10);
		JSpinner sCantidad = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
		centerPanel2.add(tProducto);
		centerPanel2.add(sCantidad);
		
		JButton add = new JButton("ADD");
		centerPanel2.add(add);
		JButton remove = new JButton("REMOVE");
		centerPanel2.add(remove);
		
				
		JTextField tShow = new JTextField(10);
		tShow.setEditable(false);
		JScrollBar scroll = new JScrollBar(JScrollBar.VERTICAL);
		scroll.setModel(tShow.getHorizontalVisibility());
		centerPanel2.add(tShow);
		centerPanel2.add(scroll);
				
		mostrarPro.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
				FactoriaPresentacion.getInstance().createView(Evento.MOSTRAR_TODOS_PRODUCTOS);
			}
					
		});
	
		load.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (tID.getText().equals("")) throw new Exception("No se ha proporcionado ningun ID");
					int ID = Integer.parseInt(tID.getText());
					if(FactoriaAbstractaNegocio.getInstance().createSAFactura().read(ID)==null) {
						throw new Exception("ID inexistente");
					}
					productos = FactoriaAbstractaNegocio.getInstance().createSAFactura().read(ID).getLineaFactura();
					
					String s="";
					for(TLineaFactura p: productos) {s += p.getIdProducto() + "-" + p.getCantidad()+ " ";}
					tShow.setText(s);
				}
				catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		
		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int id = Integer.parseInt(tProducto.getText());
					if(tProducto.getText().equals("")) throw new Exception("No hay producto");
					if(FactoriaAbstractaNegocio.getInstance().createSAProducto().read(id)==null) {
						throw new Exception("El ID del producto no existe");
					}
					int precio = FactoriaAbstractaNegocio.getInstance().createSAProducto().read(id).getPrecio();
					int cantidad = (int) sCantidad.getValue();
					tProducto.setText(null);
					sCantidad.setValue(1);
					
					TLineaFactura l = new TLineaFactura(new TCarrito(id,cantidad), cantidad*precio);
					if(!productos.contains(l)) productos.add(l);
					else{throw new Exception("La factura ya contiene el producto");}
					
					String s="";
					for(TLineaFactura p: productos) {s += p.getIdProducto() + "-" + p.getCantidad()+ " ";}
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
					int id = Integer.parseInt(tProducto.getText());
					if(tProducto.getText().equals("")) throw new Exception("No hay producto");
					tProducto.setText(null);					
					sCantidad.setValue(1);
					
					productos.remove(new TLineaFactura(new TCarrito(id,0), 0));

					String s="";
					for(TLineaFactura p: productos) {s += p.getIdProducto() + "-" + p.getCantidad()+ " ";}
					tShow.setText(s);
				}
				catch(Exception ex){
					JOptionPane.showMessageDialog(null, "Ingrediente no valido");
				}
			}
		});
		
		

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
					
					int cliente, camarero; 
					Tipodepago t = (Tipodepago) tPago.getSelectedItem(); 
					
					
					if (tCliente.getText().equals("")) {
						if(FactoriaAbstractaNegocio.getInstance().createSAFactura().read(ID).getFactura()==null) {
							throw new Exception("El id de la factura no existe");
						}
						cliente = FactoriaAbstractaNegocio.getInstance().createSAFactura().read(ID).getFactura().getIDCliente();
					}
					else {	
						cliente = Integer.parseInt(tCliente.getText());
						if(FactoriaNegocioImp.getInstance().createSACliente().read(cliente) == null) 
							throw new IllegalArgumentException("Id de clinte no existente");
					}
					if (tCamarero.getText().equals("")) {
						if(FactoriaAbstractaNegocio.getInstance().createSAFactura().read(ID).getFactura()==null) {
							throw new Exception("El id de la factura no existe");
						}
						camarero = FactoriaAbstractaNegocio.getInstance().createSAFactura().read(ID).getFactura().getIDCamarero();
					}
					else {
						camarero = Integer.parseInt(tCamarero.getText());
						TEmpleado em = FactoriaNegocioImp.getInstance().createSAEmpleado().read(camarero);
						if(em == null || !em.getTipoEmpleado().equals("camarero")) 
							throw new IllegalArgumentException("Id de camarero no existente");
					}
					
					if (productos.isEmpty())productos = FactoriaAbstractaNegocio.getInstance().createSAFactura().read(ID).getLineaFactura();
					
					TFacturaConProductos toc = new TFacturaConProductos(productos, cliente, ID, camarero, t);
					Controlador.getInstance().accion(Evento.ACTUALIZAR_FACTURA, toc);
				} catch (Exception ex) {
					ex.printStackTrace();
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
		if (evento == Evento.RES_ACTUALIZAR_FACTURA_OK)
			JOptionPane.showMessageDialog(this, "Se ha actualizado la factura con id " + (Integer) datos);
		else if (evento == Evento.RES_ACTUALIZAR_CLIENTE_KO)
			JOptionPane.showMessageDialog(this, "No se ha podido actualizar la factura");
	}
}
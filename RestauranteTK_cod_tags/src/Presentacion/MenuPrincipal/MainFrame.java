package Presentacion.MenuPrincipal;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Presentacion.Evento;
import Presentacion.IGUI;
import Presentacion.Location;
import Presentacion.Factoria.FactoriaAbstractaPresentacion;

public class MainFrame extends JFrame implements IGUI{
	
	public MainFrame() {
		super("Gestión");
		initGUI();
		
	}
	
	private void initGUI() {
		JPanel miPanel = new JPanel();
		miPanel.setLayout( new BoxLayout(miPanel, BoxLayout.Y_AXIS));
		this.add(miPanel);
		
		JPanel p1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		miPanel.add(p1);
		p1.add( new JLabel("Seleccione el modulo que desee gestionar:"));
		
		JPanel p2 = new JPanel(new FlowLayout(10,26, FlowLayout.CENTER));
		miPanel.add(p2);
		p2.add(new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().createImage("resources/imagenes/TAKO_logo.png"))));
		
		JPanel p3 = new JPanel(new GridLayout(6,1,5,20));
		p2.add(p3);
		
		JButton empleados = new JButton("Gestión Empleados");
		p3.add(empleados);
		empleados.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FactoriaAbstractaPresentacion.getInstance().createView(Evento.EMPLEADO);
			}
		});
		JButton clientes = new JButton("Gestión Clientes");
		p3.add(clientes);
		clientes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FactoriaAbstractaPresentacion.getInstance().createView(Evento.CLIENTE);
			}
		});		
		JButton facturas = new JButton("Gestión Facturas");
		p3.add(facturas);
		facturas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FactoriaAbstractaPresentacion.getInstance().createView(Evento.FACTURA);
			}
		});
		
		JButton proovedores = new JButton("Gestión Proveedores");
		p3.add(proovedores);
		proovedores.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FactoriaAbstractaPresentacion.getInstance().createView(Evento.PROVEEDOR);
			}
		});
		JButton ingredientes = new JButton("Gestión Ingredientes");
		p3.add(ingredientes);
		ingredientes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FactoriaAbstractaPresentacion.getInstance().createView(Evento.INGREDIENTES);
			}
		});
		JButton productos = new JButton("Gestión Productos");
		p3.add(productos);
		productos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FactoriaAbstractaPresentacion.getInstance().createView(Evento.PRODUCTO);
			}
		});
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE); 
		this.setMinimumSize(new Dimension(610, 450));
		this.pack();
		this.setLocation(Location.LOCATION_GESTION_W, Location.LOCATION_GESTION_H);
		this.setVisible(true);
		
	}

	@Override
	public void actualizar(int evento, Object datos) {}
	
	
}

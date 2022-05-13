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
import Presentacion.Factoria.FactoriaPresentacion;

public class VistaGestionCliente extends JFrame implements IGUI {

	public VistaGestionCliente() {
		super("Gestión Clientes");
		initGUI();
		
	}
	
	private void initGUI() {
		
		
		JPanel miPanel = new JPanel();
		miPanel.setLayout( new BoxLayout(miPanel, BoxLayout.Y_AXIS));
		this.add(miPanel);
		
		JPanel p1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		miPanel.add(p1);
		p1.add( new JLabel("Seleccione la operación que desee realizar:"));

		
		JPanel p2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		miPanel.add(p2);
		p2.add(new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().createImage("resources/imagenes/cliente.png"))));
		
		JPanel p3 = new JPanel(new GridLayout(5,1,5,20));
		p2.add(p3);
		

		JButton alta = new JButton("Alta Cliente");
		p3.add(alta);
		alta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FactoriaPresentacion.getInstance().createView(Evento.ALTA_CLIENTE);
			}
		});
		JButton baja = new JButton("Baja Cliente");
		p3.add(baja);
		baja.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FactoriaPresentacion.getInstance().createView(Evento.BAJA_CLIENTE);
			}
		});		
		JButton leer = new JButton("Leer Cliente");
		p3.add(leer);
		leer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FactoriaPresentacion.getInstance().createView(Evento.MOSTRAR_CLIENTE);
			}
		});
		JButton mostrar = new JButton("Mostrar Clientes");
		p3.add(mostrar);
		mostrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FactoriaPresentacion.getInstance().createView(Evento.MOSTRAR_TODOS_CLIENTES);
			}
		});
		JButton actualizar = new JButton("Actualizar Cliente");
		p3.add(actualizar);
		actualizar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FactoriaPresentacion.getInstance().createView(Evento.ACTUALIZAR_CLIENTE);
			}
		});

		this.setMinimumSize(new Dimension(610, 450));
		this.pack();
		this.setLocation(Location.LOCATION_GESTION_W, Location.LOCATION_GESTION_H);
		this.setVisible(true);
		
		
	}

	@Override
	public void actualizar(int evento, Object datos) {}
	
	
}

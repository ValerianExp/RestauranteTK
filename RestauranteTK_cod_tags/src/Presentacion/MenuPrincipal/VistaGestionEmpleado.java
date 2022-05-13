package Presentacion.MenuPrincipal;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Presentacion.Evento;
import Presentacion.IGUI;
import Presentacion.Location;
import Presentacion.Factoria.FactoriaPresentacion;

public class VistaGestionEmpleado extends JFrame implements IGUI {

	public VistaGestionEmpleado() {
		super("Gestión Empleados");
		initGUI();

	}

	private void initGUI() {

		JPanel miPanel = new JPanel();
		miPanel.setLayout(new BoxLayout(miPanel, BoxLayout.Y_AXIS));
		this.add(miPanel);

		JPanel p1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		miPanel.add(p1);
		p1.add(new JLabel("Seleccione la operación que desee realizar:"));

		JPanel p2 = new JPanel(new FlowLayout(10, 26, FlowLayout.CENTER));
		miPanel.add(p2);
		p2.add(new JLabel(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage("resources/imagenes/empleado.png"))));

		JPanel p3 = new JPanel(new GridLayout(5, 1, 5, 20));
		p2.add(p3);

		JButton alta = new JButton("Alta Empleado");
		p3.add(alta);
		alta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FactoriaPresentacion.getInstance().createView(Evento.ALTA_EMPLEADO);
			}
		});
		JButton baja = new JButton("Baja Empleado");
		p3.add(baja);
		baja.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FactoriaPresentacion.getInstance().createView(Evento.BAJA_EMPLEADO);
			}
		});
		JButton leer = new JButton("Leer Empleado");
		p3.add(leer);
		leer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FactoriaPresentacion.getInstance().createView(Evento.MOSTRAR_EMPLEADO);
			}
		});
		JButton mostrar = new JButton("Mostrar Empleados");
		p3.add(mostrar);
		mostrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FactoriaPresentacion.getInstance().createView(Evento.MOSTRAR_TODOS_EMPLEADOS);
			}
		});
		JButton actualizar = new JButton("Actualizar Empleado");
		p3.add(actualizar);
		actualizar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FactoriaPresentacion.getInstance().createView(Evento.ACTUALIZAR_EMPLEADO);
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

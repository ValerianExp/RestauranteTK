package Presentacion.Proveedores;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Negocio.Proveedores.TProveedores;
import Presentacion.Evento;
import Presentacion.IGUI;
import Presentacion.Location;
import Presentacion.Controlador.Controlador;

public class VistaMostrarTodosProveedores extends JFrame implements IGUI {

	public VistaMostrarTodosProveedores(){
		super("Mostrar todos los Proveedores");
		Controlador.getInstance().registerView(Evento.MOSTRAR_TODOS_PROVEEDORES, this);
		initGui();
	}
	
	private void initGui() {

		JPanel panel = new JPanel();
		this.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JPanel upPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel.add(upPanel);
		upPanel.add(new JLabel("Confirme si quiere mostrar proveedores"));

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
					Controlador.getInstance().accion(Evento.MOSTRAR_TODOS_PROVEEDORES, null);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "ERROR",
							JOptionPane.ERROR_MESSAGE);
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
    	if (evento == Evento.RES_MOSTRAR_TODOS_PROVEEDORES_OK) {
    		String s = ""; 
			for(TProveedores t: (List<TProveedores>) datos){
				s += t.toString() + "------ \n";	
			}
			JTextArea textArea = new JTextArea(20,10);
		    textArea.setText(s);
		    textArea.setEditable(false);
			
			JScrollPane scrollPane = new JScrollPane(textArea);
			JOptionPane.showMessageDialog(this, scrollPane);
			
		} else if (evento == Evento.RES_MOSTRAR_TODOS_PROVEEDORES_KO)
			JOptionPane.showMessageDialog(this, "No se han podido leer los poveedores");	
    	
    }

}

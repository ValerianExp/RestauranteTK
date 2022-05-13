/**
 * 
 */
package Presentacion.Factura;

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
import javax.swing.JTextField;

import Negocio.FactoriaAbstractaNegocio.FactoriaAbstractaNegocio;
import Negocio.Factura.TFactura;
import Negocio.Factura.TFacturaConProductos;
import Presentacion.Evento;
import Presentacion.IGUI;
import Presentacion.Location;
import Presentacion.Controlador.Controlador;


public class VistaMostrarPorCliente extends JFrame implements IGUI {
	
	public VistaMostrarPorCliente() {
		super("Mostrar Facturas Por Cliente");
		Controlador.getInstance().registerView(Evento.MOSTRAR_FACTURA_CLIENTE, this);
		initGui();
	}

	private void initGui() {
 
		JPanel panel = new JPanel();
		this.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JPanel upPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel.add(upPanel);
		upPanel.add(new JLabel("Introduzca el ID del clinte del cual desee ver sus facturas"));

		JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel.add(centerPanel);
		centerPanel.add(new JLabel("ID:"));
		final JTextField tID = new JTextField(20);
		centerPanel.add(tID);
		

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
					int ID = Integer.parseInt(tID.getText());
					if(FactoriaAbstractaNegocio.getInstance().createSACliente().read(ID)==null) {
						throw new Exception("El id del cliente no existe o ha sido dado de baja");
					}
					Controlador.getInstance().accion(Evento.MOSTRAR_FACTURA_CLIENTE, ID);
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
		if (evento == Evento.RES_MOSTRAR_FACTURA_CLIENTE_OK) {
			
			String s = ""; 
			for(TFacturaConProductos t: (List<TFacturaConProductos>) datos){
				s += t.toString() + "------ \n";	
			}
			
			JTextArea textArea = new JTextArea(20,10);
		    textArea.setText(s);
		    textArea.setEditable(false);
			
			JScrollPane scrollPane = new JScrollPane(textArea);
			JOptionPane.showMessageDialog(this, scrollPane);
			
		} else if (evento == Evento.RES_MOSTRAR_FACTURA_CLIENTE_KO)
			JOptionPane.showMessageDialog(this, "No se han podido leer las facturas");
	}
}
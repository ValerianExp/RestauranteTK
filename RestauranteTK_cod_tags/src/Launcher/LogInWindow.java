package Launcher;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator; 
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Presentacion.Evento;
import Presentacion.Location;
import Presentacion.Factoria.FactoriaAbstractaPresentacion;

public class LogInWindow extends JFrame {

	public LogInWindow() {
		super("T&Y");
		initGUI();
	}

	private void initGUI() {

		JPanel panel = new JPanel();
		this.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JPanel p2 = new JPanel();
		panel.add(p2);
		p2.add(new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().createImage("resources/imagenes/TAKO_nombre.png"))));

		
		JPanel p5 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panel.add(p5);
		JButton shortcut = new JButton("Shortcut");
		p5.add(shortcut);
		shortcut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				FactoriaAbstractaPresentacion.getInstance().createView(Evento.MAIN);
			}
		});
		
		JPanel p3 = new JPanel();
		panel.add(p3);
		p3.add(new JLabel("USER: "));
		JTextField user = new JTextField(20);
		p3.add(user);

		JPanel p4 = new JPanel();
		panel.add(p4);
		p4.add(new JLabel("PASSWORD: "));
		JPasswordField pas = new JPasswordField(20);
		p4.add(pas);

		JSeparator s = new JSeparator(SwingConstants.HORIZONTAL);
		s.setPreferredSize(new Dimension(10, 10));
		panel.add(s);

		JPanel downPanel = new JPanel();
		panel.add(downPanel);
		JButton aceptar = new JButton("Aceptar");
		JButton cancelar = new JButton("Cancelar");
		downPanel.add(aceptar);
		downPanel.add(cancelar);

		aceptar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				login(user, pas);
			}
		});

		pas.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (KeyEvent.VK_ENTER == e.getKeyCode()) {
					login(user, pas);
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {

			}

		});

		cancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setMinimumSize(new Dimension(380, 550));
		this.pack();
		this.setLocation(Location.LOCATION_LOGIN_W, Location.LOCATION_LOGIN_H);
		this.setVisible(true);
	}

	void login(JTextField user, JPasswordField pas) {
		try {
			String sUser = user.getText();
			String sPassword = String.valueOf(pas.getPassword());
			if (sUser.equals("admin") && sPassword.equals("admin")) {
				setVisible(false);
				FactoriaAbstractaPresentacion.getInstance().createView(Evento.MAIN);
			} else {
				JOptionPane.showMessageDialog(null, "Usuario y/o contraseña incorrecto");
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "ERROR",JOptionPane.ERROR_MESSAGE);
		}
	}
}

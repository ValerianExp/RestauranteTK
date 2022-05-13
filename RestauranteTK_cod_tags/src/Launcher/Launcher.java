package Launcher;

import javax.swing.SwingUtilities;

public class Launcher {
	// public static final String URL = "jdbc:mysql://tk-is2.mysql.database.azure.com";
	// public static final String USER = "restaurante_admin";
	// public static final String PASSWORD = "NfBff6ZrLsxgHYP";
	// public static final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";

	public static void main(String[] args) {
		try {
			SwingUtilities.invokeAndWait(new Runnable() {

				@Override
				public void run() {
					new LogInWindow(); 
				}
			});
		} catch (Exception e) {
			e.getMessage();
		}
	}
}

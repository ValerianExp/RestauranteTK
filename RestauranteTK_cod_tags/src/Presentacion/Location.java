package Presentacion;

import java.awt.Toolkit;

public class Location {
	
	private static int width =  Toolkit.getDefaultToolkit().getScreenSize().width;
	private static int height =  Toolkit.getDefaultToolkit().getScreenSize().height;
	
	public static int LOCATION_GESTION_W = width/14;
	public static int LOCATION_GESTION_H = height/5;
	
	public static int LOCATION_VIEW_W = LOCATION_GESTION_W + 616;
	public static int LOCATION_VIEW_H = height/5;
	
	public static int LOCATION_LOGIN_W = width/2 - 190;
	public static int LOCATION_LOGIN_H = height/6;

}

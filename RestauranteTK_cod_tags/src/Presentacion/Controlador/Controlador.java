
package Presentacion.Controlador;

import Presentacion.IGUI;

public abstract class Controlador {

	private static Controlador instance;

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param evento
	* @param datos
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public abstract void accion(int evento, Object datos);

	public static Controlador getInstance() {
		if (instance == null) {
			instance = new ControladorImp();
		}
		return instance;
	}

	public abstract void registerView(int operacion, IGUI i);

}
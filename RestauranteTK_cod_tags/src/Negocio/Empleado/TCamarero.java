/**
 * 
 */
package Negocio.Empleado;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author RestauranteTK
 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class TCamarero extends TEmpleado {

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	private String idiomas;

	public TCamarero(int id, String nombre, String DNI, String idiomas, int salario, boolean activo) {
		super(id, "camarero", nombre, DNI, salario, activo);
		this.idiomas = idiomas;
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public String getIdiomas() {
		// begin-user-code
		return idiomas;
		// end-user-code
	}

	@Override
	public String toString() {
		// @formatter: off
		return super.toString() +
				"idiomas: " + this.idiomas + '\n';
		// @formatter: on
	}

}
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
public class TChef extends TEmpleado {

	private int estrellas;

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public int getEstrellas() {
		// begin-user-code
		return estrellas;
		// end-user-code
	}

	public void setEstrellas(int estrellas) {
		this.estrellas = estrellas;
	}

	public TChef(int id, String nombre, String DNI, int salario, int estrellas, boolean activo) {
		super(id, "chef", nombre, DNI, salario, activo);
		this.estrellas = estrellas;
	}

	@Override
	public String toString() {
		// @formatter: off
		return super.toString() +
				"estrellas: " + this.estrellas + '\n';
		// @formatter: on
	}
}
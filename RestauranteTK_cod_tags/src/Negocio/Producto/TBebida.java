/**
 * 
 */
package Negocio.Producto;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author RestauranteTK
 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class TBebida extends TProducto {
	public TBebida(int id, int precio, String nombre, int idChef, String marca, boolean activo) {
		super(id, nombre, precio, idChef, "bebida", activo);
		this.marca = marca;
	}

	@Override
	public String toString() {
		return super.toString() + "marca: " + this.marca +'\n'; 
	}
	
	private String marca;

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public String getMarca() {
		// begin-user-code
		return marca;
		// end-user-code
	}

	public void setMarca(String marca) {
		// begin-user-code
		this.marca = marca;
		// end-user-code
	}
}
/**
 * 
 */
package Negocio.Producto;

import Negocio.Ingredientes.TIngredientes;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author RestauranteTK
 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class TProducto {
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	private int id;
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	private int precio;
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	private String nombre;
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	private int idChef;

	private String tipoProducto;
	
	private boolean activo;

	public TProducto(int id, String nombre, int precio, int idChef, String tipoProducto, boolean activo) {
		this.id = id;
		this.precio = precio;
		this.nombre = nombre;
		this.idChef = idChef;
		this.tipoProducto = tipoProducto;
		this.activo = activo;
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	 * @return 
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	
	@Override
	public String toString() {
		return "id: " + this.id + '\n' +
				"nombre: " + this.nombre + '\n' 
				+ "precio: " + this.precio + '\n'
				+ "tipo: " + this.tipoProducto + "\n"
				+"idChef: "+ this.idChef +"\n"; 
	}
	

	public int getId() {
		// begin-user-code
		return id;
		// end-user-code
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public void setId(int id) {
		// begin-user-code
		this.id = id;
		// end-user-code
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public int getPrecio() {
		// begin-user-code
		return precio;
		// end-user-code
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public void setPrecio(int precio) {
		// begin-user-code
		this.precio = precio;
		// end-user-code
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public String getNombre() {
		// begin-user-code
		return nombre;
		// end-user-code
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public void setNombre(String nombre) {
		// begin-user-code
		this.nombre = nombre;
		// end-user-code
	}

	public int getIdChef() {
		return idChef;
	}

	public void setIdChef(int idChef) {
		this.idChef = idChef;
	}
	
	public String getTipoProducto(){
		return tipoProducto;
	}
	
	public void setTipoProducto(String tipoProducto){
		this.tipoProducto = tipoProducto;
	}
	
	
	public boolean getActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
}
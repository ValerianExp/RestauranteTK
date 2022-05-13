/**
 * 
 */
package Negocio.Ingredientes;


/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author RestauranteTK
 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class TIngredientes {
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
	private boolean tieneGluten;
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
	private int cantidad;
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	private boolean activo;
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	private int idProveedor;

	public void setIdProveedor(int idProveedor) {
		this.idProveedor = idProveedor;
	}

	public void setID(int iD) {
		this.id = iD;
	}

	public void setGluten(Boolean tieneGluten) {
		this.tieneGluten = tieneGluten;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	public TIngredientes(int id, Boolean tieneGluten, String nombre, int cantidad, int idProveedor, boolean activo) {
		this.id = id;
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.activo = activo;
		this.idProveedor = idProveedor;
		this.tieneGluten = tieneGluten;
	}
	
	public int getIdProveedor() {
		return idProveedor;
	}

	public int getID() {
		return id;
	}

	public boolean getGluten() {
		return tieneGluten;
	}

	public String getNombre() {
		return nombre;
	}

	public int getCantidad() {
		return cantidad;
	}

	public boolean getActivo() {
		return activo;
	}
	

	@Override
	public String toString() {
		// @formatter:off
		return "id: " + this.id + '\n' +
				"nombre: " + this.nombre + '\n' 
				+ "gluten: " + this.tieneGluten + '\n'
				+"cantidad: "+ this.cantidad +"\n"
				+"id proveedor: "+ this.idProveedor+"\n";
		// @formatter:on
	}
}
/**
 * 
 */
package Negocio.FactoriaAbstractaNegocio;

import Negocio.Cliente.SACliente;
import Negocio.Empleado.SAEmpleado;
import Negocio.Factura.SAFactura;
import Negocio.Ingredientes.SAIngredientes;
import Negocio.Producto.SAProducto;
import Negocio.Proveedores.SAProveedores;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author RestauranteTK
 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public abstract class FactoriaAbstractaNegocio {
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	private static FactoriaAbstractaNegocio instance;

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	 * @return 
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public static FactoriaAbstractaNegocio getInstance() {
		// begin-user-code

		if (instance == null) {
			instance = new FactoriaNegocioImp();
		}
		return instance;
		// end-user-code
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	 * @return 
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public abstract SACliente createSACliente();

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	 * @return 
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public abstract SAEmpleado createSAEmpleado();

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public abstract SAFactura createSAFactura();

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public abstract SAIngredientes createSAIngredientes();

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public abstract SAProducto createSAProducto();

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public abstract SAProveedores createSAProveedores();
}
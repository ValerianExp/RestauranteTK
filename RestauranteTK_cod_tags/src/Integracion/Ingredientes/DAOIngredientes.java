/**
 * 
 */
package Integracion.Ingredientes;

import Negocio.Ingredientes.TIngredientes;

import java.util.Collection;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author RestauranteTK
 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public interface DAOIngredientes {
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param i
	* @return
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public int create(TIngredientes i);

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param IDIngrediente
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public int delete(int IDIngrediente);

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param IDIngrediente
	* @return
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public TIngredientes read(int IDIngrediente);

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @return
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public Collection<TIngredientes> readAll();

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param i
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public int update(TIngredientes i);

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public Collection<TIngredientes> readByProducto(int idProducto);

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public Collection<TIngredientes> readByProveedor(int idProveedor);


}
/**
 * 
 */
package Integracion.Producto;

import Negocio.Producto.TProducto;

import java.util.Collection;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author RestauranteTK
 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public interface DAOProducto {
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param p
	* @return
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public int create(TProducto p);

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param idProducto
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public int delete(int idProducto);

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param idProducto
	* @return
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public TProducto read(int idProducto);

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param p
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public int update(TProducto p);

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @return
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public Collection<TProducto> readAll();

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
}
/**
 * 
 */
package Integracion.Proveedores;

import Negocio.Proveedores.TProveedores;

import java.util.Collection;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author RestauranteTK
 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public interface DAOProveedores {
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param p
	* @return
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public int create(TProveedores p);

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param IDProveedor
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public TProveedores read(int IDProveedor);

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param IDProveedor
	 * @return 
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public int delete(int IDProveedor);

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @return
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public Collection<TProveedores> readAll();

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param p
	 * @return 
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public int update(TProveedores p);

	public TProveedores readByNIF(String string);
	
	public int deleteFisico(int idProveedor);

}
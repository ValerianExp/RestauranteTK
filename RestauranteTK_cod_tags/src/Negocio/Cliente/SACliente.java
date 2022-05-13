/**
 * 
 */
package Negocio.Cliente;

import java.util.Collection;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author RestauranteTK
 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public interface SACliente {
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	 * @param tCliente
	 * @return 
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public int create(TCliente tCliente);

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	 * @return 
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public int delete(int idCliente);

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	 * @return 
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public TCliente read(int idCliente);

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	 * @return 
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public int update(TCliente tCliente);

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	 * @return 
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public Collection<TCliente> readAll();

	public int deleteFisico(int idCliente);
}
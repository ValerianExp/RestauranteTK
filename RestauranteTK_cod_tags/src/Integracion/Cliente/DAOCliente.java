/**
 * 
 */
package Integracion.Cliente;

import Negocio.Cliente.TCliente;

import java.util.Collection;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author RestauranteTK
 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public interface DAOCliente {
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param c
	* @return
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public int create(TCliente c);

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param idCliente
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public int delete(int idCliente);

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param idCliente
	* @return
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public TCliente read(int idCliente);

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param c
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public int update(TCliente c);

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @return
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public Collection<TCliente> readAll();

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param dniCliente
	* @return
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public TCliente readByDni(String dniCliente);

	public int deleteFisico(int idCliente);
}
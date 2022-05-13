/**
 * 
 */
package Integracion.Empleado;

import Negocio.Empleado.TEmpleado;
import java.util.Collection;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author RestauranteTK
 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public interface DAOEmpleado {
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param e
	* @return
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public int create(TEmpleado e);

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param idEmpleado
	 * @return 
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public int delete(int idEmpleado);

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param idEmpleado
	* @return
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public TEmpleado read(int idEmpleado);

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param e
	 * @return 
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public int update(TEmpleado e);

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @return
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public Collection<TEmpleado> readAll();

	public int deleteFisico(int idEmpleado);
}
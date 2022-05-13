/**
 * 
 */
package Negocio.Ingredientes;

import java.util.Collection;

import Integracion.Factoria.FactoriaAbstractaIntegracion;
import Integracion.Ingredientes.DAOIngredientes;
import Integracion.Proveedores.DAOProveedores;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author RestauranteTK
 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class SAIngredientesImp implements SAIngredientes {
	/** 
	* (non-Javadoc)
	* @see SAIngredientes#create()
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public int create(TIngredientes ingredientes) {
		// begin-user-code
		int id = -1;
		DAOIngredientes daoIngredientes;
		
		daoIngredientes = FactoriaAbstractaIntegracion.getInstance().createDAOIngredientes();
		
		
		if (ingredientes != null){
			TIngredientes tIngredientes = daoIngredientes.read(ingredientes.getID());
			if(tIngredientes == null){
				id = daoIngredientes.create(ingredientes);
			}
			
			
		}
		
		return id;
		// end-user-code
	}

	/** 
	* (non-Javadoc)
	* @see SAIngredientes#delete()
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public int delete (int idIngrediente) {
		// begin-user-code
		int id = -1;
		DAOIngredientes daoIngredientes = FactoriaAbstractaIntegracion.getInstance().createDAOIngredientes();
		if(daoIngredientes.read(idIngrediente) != null){
			id = daoIngredientes.delete(idIngrediente);
		}
		
		
		return id;
		// end-user-code
	}

	/** 
	* (non-Javadoc)
	* @see SAIngredientes#read()
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public TIngredientes read(int idIngredientes) {
		// begin-user-code
		return FactoriaAbstractaIntegracion.getInstance().createDAOIngredientes().read(idIngredientes);
		
		
		
		// end-user-code
	}

	/** 
	* (non-Javadoc)
	* @see SAIngredientes#readAll()
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public Collection<TIngredientes> readAll() {
		// begin-user-code
		DAOIngredientes daoIngredientes = FactoriaAbstractaIntegracion.getInstance().createDAOIngredientes();
		if(daoIngredientes != null){
			return daoIngredientes.readAll();
		}
		
		return null;
		// end-user-code
	}
	
	
	public Collection<TIngredientes> readByProveedor(int idProveedor){
		
		return FactoriaAbstractaIntegracion.getInstance().createDAOIngredientes().readByProveedor(idProveedor);
		
	}

	/** 
	* (non-Javadoc)
	* @see SAIngredientes#update()
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public int update(TIngredientes tIngredientes) {
		// begin-user-code

		DAOIngredientes daoIngredientes = FactoriaAbstractaIntegracion.getInstance().createDAOIngredientes();
		DAOProveedores daoProveedores = FactoriaAbstractaIntegracion.getInstance().createDAOProveedores();
		if(daoProveedores.read(tIngredientes.getIdProveedor()) != null){
			
			if(daoIngredientes != null){
				daoIngredientes.update(tIngredientes);
			}
			
		}
		else{
			throw new RuntimeException("Id de proveedor no existe");
		}
		
		
		
		
		return tIngredientes.getID();
		
		
		// end-user-code
	}


}
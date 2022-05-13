/**
 * 
 */
package Negocio.Proveedores;

import java.util.Collection;

import Integracion.Cliente.DAOCliente;
import Integracion.Factoria.FactoriaAbstractaIntegracion;
import Integracion.Proveedores.DAOProveedores;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author RestauranteTK
 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class SAProveedoresImp implements SAProveedores {
	/** 
	* (non-Javadoc)
	* @see SAProveedores#create()
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	@Override
	public int create(TProveedores proveedores) {
		// begin-user-code

		int id = -1;
		DAOProveedores daoProveedores;
		//Acceso a la implementacion DAO
		daoProveedores = FactoriaAbstractaIntegracion.getInstance().createDAOProveedores();

		if (proveedores != null) {
			TProveedores tProveedores = daoProveedores.read(proveedores.getID());
			if (tProveedores == null) {
				id = daoProveedores.create(proveedores);
			}
		}

		// end-user-code

		return id;
	}

	/** 
	* (non-Javadoc)
	* @see SAProveedores#delete()
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	@Override
	public int delete(int nifProveedor) {
		// begin-user-code
		int nif = -1;
		DAOProveedores daoProveedores;
		daoProveedores = FactoriaAbstractaIntegracion.getInstance().createDAOProveedores();

		if (daoProveedores.read(nifProveedor) != null) {

			nif = daoProveedores.delete(nifProveedor);

		}

		// end-user-code

		return nif;
	}

	/** 
	* (non-Javadoc)
	* @see SAProveedores#read()
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	@Override
	public TProveedores read(int idProveedores) {
		// begin-user-code
		return FactoriaAbstractaIntegracion.getInstance().createDAOProveedores().read(idProveedores);

		// end-user-code
	}

	/** 
	* (non-Javadoc)
	* @see SAProveedores#readAll()
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	@Override
	public Collection<TProveedores> readAll() {
		// begin-user-code
		DAOProveedores daoProveedores = FactoriaAbstractaIntegracion.getInstance().createDAOProveedores();

		if (daoProveedores != null) {
			return daoProveedores.readAll();
		}

		return null;
		// end-user-code
	}

	/** 
	* (non-Javadoc)
	* @see SAProveedores#update()
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	@Override
	public int update(TProveedores tProveedores) {
		// begin-user-code
		DAOProveedores daoProveedores = FactoriaAbstractaIntegracion.getInstance().createDAOProveedores();
		TProveedores prov_id = daoProveedores.read(tProveedores.getID());
		TProveedores prov_nif = daoProveedores.readByNIF(tProveedores.getNIF());
		if (prov_id == null) {
			return -3;
		} else if (prov_nif != null) {
			if (prov_id.getID() == prov_nif.getID() &&
					prov_nif.getNIF().equals(prov_id.getNIF())) {
				return daoProveedores.update(tProveedores);
			}
			return -2;
		} else {
			return daoProveedores.update(tProveedores);
		}
		// end-user-code
	}
	
	@Override
	public int deleteFisico(int idProveedor) {
		int id = -1;
		DAOProveedores daoProveedores;
		daoProveedores = FactoriaAbstractaIntegracion.getInstance().createDAOProveedores();

		if (daoProveedores.read(idProveedor) != null) {
			id = daoProveedores.deleteFisico(idProveedor);
		}

		return id;
	}
}
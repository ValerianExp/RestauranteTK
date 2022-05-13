/**
 * 
 */
package Negocio.Cliente;

import java.util.Collection;

import Integracion.Cliente.DAOCliente;
import Integracion.Factoria.FactoriaAbstractaIntegracion;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author RestauranteTK
 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class SAClienteImp implements SACliente {
	/** 
	* (non-Javadoc)
	* @see SACliente#create()
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	@Override
	public int create(TCliente cliente) {
		// begin-user-code

		int id = -1;
		DAOCliente daoCliente;
		//Acceso a la implemetacion del DAO
		daoCliente = FactoriaAbstractaIntegracion.getInstance().createDAOCliente();

		//Comprobaciones de regla de negocio
		//Comprueba si es sintacticamente valido


		if (cliente != null) {
			TCliente tCliente = daoCliente.readByDni(cliente.getDNI());
			if (tCliente == null) {
				id = daoCliente.create(cliente);
			} else if (!tCliente.getActivo()) {
				cliente.setId(tCliente.getId());
				id = daoCliente.update(cliente);
			}
		}
		return id;
		// end-user-code
	}

	/** 
	* (non-Javadoc)
	* @see SACliente#delete()
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	@Override
	public int delete(int idCliente) {
		// begin-user-code
		int id = -1;
		DAOCliente daoCliente;
		TCliente tCliente;
		daoCliente = FactoriaAbstractaIntegracion.getInstance().createDAOCliente();

		tCliente = daoCliente.read(idCliente);
		if (tCliente != null && tCliente.getActivo()) {

			id = daoCliente.delete(idCliente);
		}

		return id;
		// end-user-code
	}

	/** 
	* (non-Javadoc)
	* @see SACliente#read()
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	@Override
	public TCliente read(int idCliente) {
		// begin-user-code
		TCliente tCliente = null;
		DAOCliente daoCliente;
		daoCliente = FactoriaAbstractaIntegracion.getInstance().createDAOCliente();

		tCliente = daoCliente.read(idCliente);
		if (tCliente != null && tCliente.getActivo()) {
			return tCliente;
		}
		return null;
		// end-user-code
	}

	/** 
	* (non-Javadoc)
	* @see SACliente#update()
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	@Override
	public int update(TCliente tCliente) {
		// begin-user-code
		DAOCliente daoCliente = FactoriaAbstractaIntegracion.getInstance().createDAOCliente();
		TCliente cliente_by_ID = daoCliente.read(tCliente.getId());
		TCliente cliente_by_DNI = daoCliente.readByDni(tCliente.getDNI());
		// if (cliente_by_ID.getId() == cliente_by_DNI.getId() && cliente_by_DNI.getDNI().equals(tCliente.getDNI()))
		if (cliente_by_ID == null) {
			return -3;
		} else if (cliente_by_DNI != null) {
			if (cliente_by_DNI.getDNI().equals(cliente_by_ID.getDNI())
					&& cliente_by_ID.getId() == cliente_by_DNI.getId()) {
				return daoCliente.update(tCliente);
			}
			return -2;
		} else {
			return daoCliente.update(tCliente);
		}
		// end-user-code
	}

	/** 
	* (non-Javadoc)
	* @see SACliente#readAll()
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public Collection<TCliente> readAll() {
		// begin-user-code
		DAOCliente daoCliente = FactoriaAbstractaIntegracion.getInstance().createDAOCliente();
		if (daoCliente != null) {
			return daoCliente.readAll();
		}
		return null;
		// end-user-code
	}

	@Override
	public int deleteFisico(int idCliente) {
		int id = -1;
		DAOCliente daoCliente;
		daoCliente = FactoriaAbstractaIntegracion.getInstance().createDAOCliente();

		if (daoCliente.read(idCliente) != null) {
			id = daoCliente.deleteFisico(idCliente);
		}

		return id;
	}

}
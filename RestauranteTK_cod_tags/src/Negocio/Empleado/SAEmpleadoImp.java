/**
 * 
 */
package Negocio.Empleado;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

import Integracion.Empleado.DAOEmpleado;
import Integracion.Factoria.FactoriaAbstractaIntegracion;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author RestauranteTK
 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class SAEmpleadoImp implements SAEmpleado {

	@Override
	public int create(TEmpleado tEmpleado) {
		int id = -1;
		DAOEmpleado daoEmpleado;
		daoEmpleado = FactoriaAbstractaIntegracion.getInstance().createDAOEmpleado();
		if (tEmpleado != null) {
			TEmpleado leido = daoEmpleado.read(tEmpleado.getId());
			if (leido == null) {
				id = daoEmpleado.create(tEmpleado);
			} else if (!leido.getActivo()) {
				tEmpleado.setId(leido.getId());
				id = daoEmpleado.update(tEmpleado);
			}
		}
		return id;
	}

	@Override
	public int delete(int idEmpleado) {
		int id = -1;
		DAOEmpleado daoEmpleado = FactoriaAbstractaIntegracion.getInstance().createDAOEmpleado();

		if (daoEmpleado.read(idEmpleado) != null) {
			id = daoEmpleado.delete(idEmpleado);

		}

		return id;
	}

	@Override
	public TEmpleado read(int idEmpleado) {

		return FactoriaAbstractaIntegracion.getInstance().createDAOEmpleado().read(idEmpleado);
	}

	@Override
	public int update(TEmpleado tEmpleado) {
		DAOEmpleado daoEmpleado = FactoriaAbstractaIntegracion.getInstance().createDAOEmpleado();

		if (daoEmpleado != null) {
			int aux = daoEmpleado.update(tEmpleado);
			if (aux == -1) {
				return -1;
			}
		}

		return tEmpleado.getId();
	}

	@Override
	public Collection<TEmpleado> readAll() {

		DAOEmpleado daoEmpleado = FactoriaAbstractaIntegracion.getInstance().createDAOEmpleado();

		if (daoEmpleado != null) {
			return daoEmpleado.readAll();
		}

		return null;
	}

	@Override
	public int deleteFisico(int idEmpleado) {
		int id = -1;
		DAOEmpleado daoEmpleado = FactoriaAbstractaIntegracion.getInstance().createDAOEmpleado();

		id = daoEmpleado.deleteFisico(idEmpleado);

		return id;
	}

}
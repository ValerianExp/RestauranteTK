/**
 * 
 */
package Integracion.Factoria;

import Integracion.Cliente.DAOCliente;
import Integracion.Cliente.DAOClienteImp;
import Integracion.Empleado.DAOEmpleado;
import Integracion.Empleado.DAOEmpleadoImp;
import Integracion.Factura.DAOFactura;
import Integracion.Factura.DAOFacturaImp;
import Integracion.Factura.DAOLineaFactura;
import Integracion.Factura.DAOLineaFacturaImp;
import Integracion.Ingredientes.DAOIngredientes;
import Integracion.Ingredientes.DAOIngredientesImp;
import Integracion.Producto.DAOProducto;
import Integracion.Producto.DAOProductoImp;
import Integracion.Proveedores.DAOProveedores;
import Integracion.Proveedores.DAOProveedoresImp;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author RestauranteTK
 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class FactoriaAbtractaIntegracionImp extends FactoriaAbstractaIntegracion {
    @Override
    public DAOCliente createDAOCliente() {
        return new DAOClienteImp();
    }

    @Override
    public DAOFactura createDAOFactura() {

        return new DAOFacturaImp();
    }

    @Override
    public DAOIngredientes createDAOIngredientes() {

        return new DAOIngredientesImp();
    }

    @Override
    public DAOProveedores createDAOProveedores() {

        return new DAOProveedoresImp();
    }

    @Override
    public DAOEmpleado createDAOEmpleado() {

        return new DAOEmpleadoImp();
    }

    @Override
    public DAOProducto createDAOProducto() {

        return new DAOProductoImp();
    }

	@Override
	public DAOLineaFactura createDAOLineaFactura() {
		return new DAOLineaFacturaImp();
	}

}
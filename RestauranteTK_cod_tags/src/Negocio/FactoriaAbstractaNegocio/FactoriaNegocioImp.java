/**
 * 
 */
package Negocio.FactoriaAbstractaNegocio;

import Negocio.Cliente.SACliente;
import Negocio.Cliente.SAClienteImp;
import Negocio.Empleado.SAEmpleado;
import Negocio.Empleado.SAEmpleadoImp;
import Negocio.Factura.SAFactura;
import Negocio.Factura.SAFacturaImp;
import Negocio.Ingredientes.SAIngredientes;
import Negocio.Ingredientes.SAIngredientesImp;
import Negocio.Producto.SAProducto;
import Negocio.Producto.SAProductoImp;
import Negocio.Proveedores.SAProveedores;
import Negocio.Proveedores.SAProveedoresImp;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author RestauranteTK
 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class FactoriaNegocioImp extends FactoriaAbstractaNegocio {
    public SACliente createSACliente() {
        return new SAClienteImp();
    }

    @Override
    public SAEmpleado createSAEmpleado() {
        return new SAEmpleadoImp();
    }

    @Override
    public SAFactura createSAFactura() {
        return new SAFacturaImp();
    }

    @Override
    public SAIngredientes createSAIngredientes() {
        return new SAIngredientesImp();
    }

    @Override
    public SAProducto createSAProducto() {
        return new SAProductoImp();
    }

    @Override
    public SAProveedores createSAProveedores() {
        return new SAProveedoresImp();
    }
}
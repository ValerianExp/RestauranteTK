/**
 * 
 */
package Presentacion.Factoria;

import Presentacion.Evento;
import Presentacion.IGUI;
import Presentacion.Cliente.*;
import Presentacion.Empleado.*;
import Presentacion.Factura.*;
import Presentacion.Ingredientes.*;
import Presentacion.MenuPrincipal.*;
import Presentacion.Producto.*;
import Presentacion.Proveedores.*;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author RestauranteTK
 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class FactoriaPresentacion extends FactoriaAbstractaPresentacion {

    @Override
    public IGUI createView(int ID) {
        switch (ID) {
            //Cliente
            case Evento.ALTA_CLIENTE:
                return new VistaAltaCliente();
            case Evento.BAJA_CLIENTE:
                return new VistaBajaCliente();
            case Evento.MOSTRAR_CLIENTE:
                return new VistaMostrarCliente();
            case Evento.MOSTRAR_TODOS_CLIENTES:
                return new VistaMostrarTodosClientes();
            case Evento.ACTUALIZAR_CLIENTE:
                return new VistaActualizarCliente();

            //Empleado
            case Evento.ALTA_EMPLEADO:
                return new VistaAltaEmpleado();
            case Evento.BAJA_EMPLEADO:
                return new VistaBajaEmpleado();
            case Evento.MOSTRAR_EMPLEADO:
                return new VistaMostrarEmpleado();
            case Evento.MOSTRAR_TODOS_EMPLEADOS:
                return new VistaMostrarTodosEmpleados();
            case Evento.ACTUALIZAR_EMPLEADO:
                return new VistaActualizarEmpleado();

            //Factura
            case Evento.ALTA_FACTURA:
                return new VistaAltaFactura();
            case Evento.BAJA_FACTURA:
                return new VistaBajaFactura();
            case Evento.MOSTRAR_FACTURA:
                return new VistaMostrarFactura();
            case Evento.MOSTRAR_TODAS_FACTURAS:
                return new VistaMostrarTodasFacturas();
            case Evento.ACTUALIZAR_FACTURA:
                return new VistaActualizarFactura();
            case Evento.MOSTRAR_FACTURA_CLIENTE:
                return new VistaMostrarPorCliente();

            //Ingredientes
            case Evento.ALTA_INGREDIENTE:
                return new VistaAltaIngredientes();
            case Evento.BAJA_INGREDIENTE:
                return new VistaBajaIngredientes();
            case Evento.MOSTRAR_INGREDIENTE:
                return new VistaMostrarIngredientes();
            case Evento.MOSTRAR_TODOS_INGREDIENTES:
                return new VistaMostrarTodasIngredientes();
            case Evento.ACTUALIZAR_INGREDIENTE:
                return new VistaActualizarIngredientes();
            case Evento.MOSTRAR_INGREDIENTES_PROVEEDOR:
                return new VistaMostrarPorProveedor();
            

            //Producto
            case Evento.ALTA_PRODUCTO:
                return new VistaAltaProducto();
            case Evento.BAJA_PRODUCTO:
                return new VistaBajaProducto();
            case Evento.MOSTRAR_PRODUCTO:
                return new VistaMostrarProducto();
            case Evento.MOSTRAR_TODOS_PRODUCTOS:
                return new VistaMostrarTodosProductos();
            case Evento.ACTUALIZAR_PRODUCTO:
                return new VistaActualizarProducto();

            //Proveedores
            case Evento.ALTA_PROVEEDOR:
                return new VistaAltaProveedores();
            case Evento.BAJA_PROVEEDOR:
                return new VistaBajaProveedores();
            case Evento.MOSTRAR_PROVEEDOR:
                return new VistaMostrarProveedor();
            case Evento.MOSTRAR_TODOS_PROVEEDORES:
                return new VistaMostrarTodosProveedores();
            case Evento.ACTUALIZAR_PROVEEDOR:
                return new VistaActualizarProveedores();
                
                //Proveedores
            case Evento.CLIENTE:
                return new VistaGestionCliente();
            case Evento.EMPLEADO:
                return new VistaGestionEmpleado();
            case Evento.FACTURA:
                return new VistaGestionFactura();
            case Evento.PROVEEDOR:
                return new VistaGestionProveedores();
            case Evento.INGREDIENTES:
                return new VistaGestionIngredientes();
            case Evento.PRODUCTO:
                return new VistaGestionProducto();
            case Evento.MAIN:
                return new MainFrame();
        }
        return null;
    }

}
package Presentacion.Controlador;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import Negocio.Cliente.TCliente;
import Negocio.Empleado.TEmpleado;
import Negocio.FactoriaAbstractaNegocio.FactoriaAbstractaNegocio;
import Negocio.Factura.TFactura;
import Negocio.Factura.TFacturaConProductos;
import Negocio.Ingredientes.TIngredientes;
import Negocio.Producto.TProducto;
import Negocio.Proveedores.TProveedores;
import Presentacion.Evento;
import Presentacion.IGUI;

public class ControladorImp extends Controlador {

	private Map<Integer, IGUI> listIGui;

	public ControladorImp() {
		listIGui = new HashMap<>();
	}

	@Override
	public void accion(int evento, Object datos) {
		switch (evento) {
			//CLIENTE
			case Evento.ALTA_CLIENTE:
				TCliente tCliente_alta = (TCliente) datos;
				int datosAC = FactoriaAbstractaNegocio.getInstance().createSACliente().create(tCliente_alta);

				if (datosAC > 0) {
					listIGui.get(Evento.ALTA_CLIENTE).actualizar(Evento.RES_ALTA_CLIENTE_OK, datosAC);

				} else {
					listIGui.get(Evento.ALTA_CLIENTE).actualizar(Evento.RES_ALTA_CLIENTE_KO, datosAC);
				}
				break;
			case Evento.MOSTRAR_CLIENTE:

				TCliente datosMC = FactoriaAbstractaNegocio.getInstance().createSACliente().read((int) datos);

				if (datosMC != null) {
					listIGui.get(Evento.MOSTRAR_CLIENTE).actualizar(Evento.RES_MOSTRAR_CLIENTE_OK, datosMC);
				} else {
					listIGui.get(Evento.MOSTRAR_CLIENTE).actualizar(Evento.RES_MOSTRAR_CLIENTE_KO, datosMC);
				}
				break;
			case Evento.BAJA_CLIENTE:
				int datosBC = FactoriaAbstractaNegocio.getInstance().createSACliente().delete((int) datos);

				if (datosBC != -1) {
					listIGui.get(Evento.BAJA_CLIENTE).actualizar(Evento.RES_BAJA_CLIENTE_OK, datosBC);

				} else {
					listIGui.get(Evento.BAJA_CLIENTE).actualizar(Evento.RES_BAJA_CLIENTE_KO, datosBC);
				}

				break;

			case Evento.MOSTRAR_TODOS_CLIENTES:

				Collection<TCliente> datosMTC = FactoriaAbstractaNegocio.getInstance().createSACliente().readAll();

				if (datosMTC != null) {

					listIGui.get(Evento.MOSTRAR_TODOS_CLIENTES).actualizar(Evento.RES_MOSTRAR_TODOS_CLIENTES_OK,
							datosMTC);
				} else {
					listIGui.get(Evento.MOSTRAR_TODOS_CLIENTES).actualizar(Evento.RES_MOSTRAR_TODOS_CLIENTES_KO, null);
				}
				break;

			case Evento.ACTUALIZAR_CLIENTE:
				TCliente tCliente_actualizar = (TCliente) datos;
				int datosUC = FactoriaAbstractaNegocio.getInstance().createSACliente().update(tCliente_actualizar);

				if (datosUC > 0) {
					listIGui.get(Evento.ACTUALIZAR_CLIENTE).actualizar(Evento.RES_ACTUALIZAR_CLIENTE_OK, datosUC);
				} else if (datosUC == -2) {
					listIGui.get(Evento.ACTUALIZAR_CLIENTE).actualizar(Evento.RES_CLIENTE_DNI_REPETIDO, null);
				} else if (datosUC == -3) {
					listIGui.get(Evento.ACTUALIZAR_CLIENTE).actualizar(Evento.RES_CLIENTE_ID_NO_EXISTENTE, null);
				} else {
					listIGui.get(Evento.ACTUALIZAR_CLIENTE).actualizar(Evento.RES_ACTUALIZAR_CLIENTE_KO, null);
				}
				break;
			//PROVEEDOR
			case Evento.ALTA_PROVEEDOR:
				TProveedores tProveedores_alta = (TProveedores) datos;
				int datosAP = FactoriaAbstractaNegocio.getInstance().createSAProveedores().create(tProveedores_alta);

				if (datosAP > 0) {
					listIGui.get(Evento.ALTA_PROVEEDOR).actualizar(Evento.RES_ALTA_PROVEEDOR_OK, datosAP);

				} else {
					listIGui.get(Evento.ALTA_PROVEEDOR).actualizar(Evento.RES_ALTA_PROVEEDOR_KO, datosAP);
				}
				break;
			case Evento.MOSTRAR_PROVEEDOR:

				TProveedores datosMP = FactoriaAbstractaNegocio.getInstance().createSAProveedores().read((int) datos);

				if (datosMP != null) {
					listIGui.get(Evento.MOSTRAR_PROVEEDOR).actualizar(Evento.RES_MOSTRAR_PROVEEDOR_OK, datosMP);

				} else {
					listIGui.get(Evento.MOSTRAR_PROVEEDOR).actualizar(Evento.RES_MOSTRAR_PROVEEDOR_KO, null);
				}
				break;
			case Evento.BAJA_PROVEEDOR:
				int datosBP = FactoriaAbstractaNegocio.getInstance().createSAProveedores().delete((int) datos);

				if (datosBP != -1) {

					listIGui.get(Evento.BAJA_PROVEEDOR).actualizar(Evento.RES_BAJA_PROVEEDOR_OK, datosBP);

				} else {
					listIGui.get(Evento.BAJA_PROVEEDOR).actualizar(Evento.RES_BAJA_PROVEEDOR_KO, datosBP);
				}

				break;

			case Evento.MOSTRAR_TODOS_PROVEEDORES:

				Collection<TProveedores> datosMTP = FactoriaAbstractaNegocio.getInstance().createSAProveedores()
						.readAll();

				if (datosMTP != null) {

					listIGui.get(Evento.MOSTRAR_TODOS_PROVEEDORES).actualizar(Evento.RES_MOSTRAR_TODOS_PROVEEDORES_OK,
							datosMTP);
				} else {
					listIGui.get(Evento.MOSTRAR_TODOS_PROVEEDORES).actualizar(Evento.RES_MOSTRAR_TODOS_PROVEEDORES_KO,
							null);
				}
				break;

			case Evento.ACTUALIZAR_PROVEEDOR:
				TProveedores tProveedor_actualizar = (TProveedores) datos;
				int datosUP = FactoriaAbstractaNegocio.getInstance().createSAProveedores()
						.update(tProveedor_actualizar);

				if (datosUP > 0) {
					listIGui.get(Evento.ACTUALIZAR_PROVEEDOR).actualizar(Evento.RES_ACTUALIZAR_PROVEEDOR_OK, datosUP);
				} else if (datosUP == -2) {
					listIGui.get(Evento.ACTUALIZAR_PROVEEDOR).actualizar(Evento.RES_PROVEEDOR_NIF_REPETIDO, null);
				} else if (datosUP == -3) {
					listIGui.get(Evento.ACTUALIZAR_PROVEEDOR).actualizar(Evento.RES_PROVEEDOR_ID_NO_EXISTENTE, null);
				} else {
					listIGui.get(Evento.ACTUALIZAR_PROVEEDOR).actualizar(Evento.RES_ACTUALIZAR_PROVEEDOR_KO, null);
				}
				break;

			//EMPLEADO
			case Evento.ALTA_EMPLEADO:
				TEmpleado tEmpleado_alta = (TEmpleado) datos;
				int datosAE = FactoriaAbstractaNegocio.getInstance().createSAEmpleado().create(tEmpleado_alta);

				if (datosAE > 0) {
					listIGui.get(Evento.ALTA_EMPLEADO).actualizar(Evento.RES_ALTA_EMPLEADO_OK, datosAE);
				} else {
					listIGui.get(Evento.ALTA_EMPLEADO).actualizar(Evento.RES_ALTA_EMPLEADO_KO, null);
				}
				break;
			case Evento.MOSTRAR_EMPLEADO:
				TEmpleado datosME = FactoriaAbstractaNegocio.getInstance().createSAEmpleado().read((int) datos);

				if (datosME != null) {
					listIGui.get(Evento.MOSTRAR_EMPLEADO).actualizar(Evento.RES_MOSTRAR_EMPLEADO_OK, datosME);
				} else {
					listIGui.get(Evento.MOSTRAR_EMPLEADO).actualizar(Evento.RES_MOSTRAR_EMPLEADO_KO, null);
				}

				break;
			case Evento.BAJA_EMPLEADO:
				int datosBE = FactoriaAbstractaNegocio.getInstance().createSAEmpleado().delete((int) datos);

				if (datosBE != -1) {

					listIGui.get(Evento.BAJA_EMPLEADO).actualizar(Evento.RES_BAJA_EMPLEADO_OK, datosBE);

				} else {
					listIGui.get(Evento.BAJA_EMPLEADO).actualizar(Evento.RES_BAJA_EMPLEADO_KO, null);
				}

				break;

			case Evento.MOSTRAR_TODOS_EMPLEADOS:

				Collection<TEmpleado> datosMTE = FactoriaAbstractaNegocio.getInstance().createSAEmpleado().readAll();

				if (datosMTE != null) {

					listIGui.get(Evento.MOSTRAR_TODOS_EMPLEADOS).actualizar(Evento.RES_MOSTRAR_TODOS_EMPLEADOS_OK,
							datosMTE);
				} else {
					listIGui.get(Evento.MOSTRAR_TODOS_EMPLEADOS).actualizar(Evento.RES_MOSTRAR_TODOS_EMPLEADOS_KO,
							null);
				}
				break;

			case Evento.ACTUALIZAR_EMPLEADO:
				TEmpleado tEmpleado_alctualizr = (TEmpleado) datos;
				int datosUE = FactoriaAbstractaNegocio.getInstance().createSAEmpleado().update(tEmpleado_alctualizr);

				if (datosUE > 0) {
					listIGui.get(Evento.ACTUALIZAR_EMPLEADO).actualizar(Evento.RES_ACTUALIZAR_EMPLEADO_OK, datosUE);
				} else {
					listIGui.get(Evento.ACTUALIZAR_EMPLEADO).actualizar(Evento.RES_ACTUALIZAR_EMPLEADO_KO, null);
				}
				break;
			//PRODUCTO
			case Evento.ALTA_PRODUCTO:
				TProducto tProducto_alta = (TProducto) datos;
				int datosAPR = FactoriaAbstractaNegocio.getInstance().createSAProducto().create(tProducto_alta);

				if (datosAPR > 0) {
					listIGui.get(Evento.ALTA_PRODUCTO).actualizar(Evento.RES_ALTA_PRODUCTO_OK, datosAPR);
				} else {
					listIGui.get(Evento.ALTA_PRODUCTO).actualizar(Evento.RES_ALTA_PRODUCTO_KO, null);
				}
				break;
			case Evento.MOSTRAR_PRODUCTO:
				TProducto datosMPR = FactoriaAbstractaNegocio.getInstance().createSAProducto().read((int) datos);

				if (datosMPR != null) {
					listIGui.get(Evento.MOSTRAR_PRODUCTO).actualizar(Evento.RES_MOSTRAR_PRODUCTO_OK, datosMPR);
				} else {
					listIGui.get(Evento.MOSTRAR_PRODUCTO).actualizar(Evento.RES_MOSTRAR_PRODUCTO_KO, null);
				}

				break;
			case Evento.BAJA_PRODUCTO:
				int datosBPR = FactoriaAbstractaNegocio.getInstance().createSAProducto().delete((int) datos);

				if (datosBPR != -1) {

					listIGui.get(Evento.BAJA_PRODUCTO).actualizar(Evento.RES_BAJA_PRODUCTO_OK, datosBPR);

				} else {
					listIGui.get(Evento.BAJA_PRODUCTO).actualizar(Evento.RES_BAJA_PRODUCTO_KO, null);
				}

				break;

			case Evento.MOSTRAR_TODOS_PRODUCTOS:

				Collection<TProducto> datosMTPR = FactoriaAbstractaNegocio.getInstance().createSAProducto().readAll();

				if (datosMTPR != null) {

					listIGui.get(Evento.MOSTRAR_TODOS_PRODUCTOS).actualizar(Evento.RES_MOSTRAR_TODOS_PRODUCTOS_OK,
							datosMTPR);
				} else {
					listIGui.get(Evento.MOSTRAR_TODOS_PRODUCTOS).actualizar(Evento.RES_MOSTRAR_TODOS_PRODUCTOS_KO,
							null);
				}
				break;

			case Evento.ACTUALIZAR_PRODUCTO:
				TProducto tProducto_actualizar = (TProducto) datos;
				int datosUPR = FactoriaAbstractaNegocio.getInstance().createSAProducto().update(tProducto_actualizar);

				if (datosUPR > 0) {
					listIGui.get(Evento.ACTUALIZAR_PRODUCTO).actualizar(Evento.RES_ACTUALIZAR_PRODUCTO_OK, datosUPR);
				} else {
					listIGui.get(Evento.ACTUALIZAR_PRODUCTO).actualizar(Evento.RES_ACTUALIZAR_PRODUCTO_KO, null);
				}
				break;
			//INGREDIENTE
			case Evento.ALTA_INGREDIENTE:
				TIngredientes tIngrediente_alta = (TIngredientes) datos;
				int datosAI = FactoriaAbstractaNegocio.getInstance().createSAIngredientes().create(tIngrediente_alta);

				if (datosAI > 0) {
					listIGui.get(Evento.ALTA_INGREDIENTE).actualizar(Evento.RES_ALTA_INGREDIENTE_OK, datosAI);
				} else {
					listIGui.get(Evento.ALTA_INGREDIENTE).actualizar(Evento.RES_ALTA_INGREDIENTE_KO, null);
				}
				break;
			case Evento.MOSTRAR_INGREDIENTE:
				TIngredientes datosMI = FactoriaAbstractaNegocio.getInstance().createSAIngredientes().read((int) datos);

				if (datosMI != null) {
					listIGui.get(Evento.MOSTRAR_INGREDIENTE).actualizar(Evento.RES_MOSTRAR_INGREDIENTE_OK, datosMI);
				} else {
					listIGui.get(Evento.MOSTRAR_INGREDIENTE).actualizar(Evento.RES_MOSTRAR_INGREDIENTE_KO, null);
				}

				break;
			case Evento.BAJA_INGREDIENTE:
				int datosBI = FactoriaAbstractaNegocio.getInstance().createSAIngredientes().delete((int) datos);

				if (datosBI != -1) {

					listIGui.get(Evento.BAJA_INGREDIENTE).actualizar(Evento.RES_BAJA_INGREDIENTE_OK, datosBI);

				} else {
					listIGui.get(Evento.BAJA_INGREDIENTE).actualizar(Evento.RES_BAJA_INGREDIENTE_KO, null);
				}

				break;

			case Evento.MOSTRAR_TODOS_INGREDIENTES:

				Collection<TIngredientes> datosMTI = FactoriaAbstractaNegocio.getInstance().createSAIngredientes()
						.readAll();

				if (datosMTI != null) {
					listIGui.get(Evento.MOSTRAR_TODOS_INGREDIENTES).actualizar(Evento.RES_MOSTRAR_TODOS_INGREDIENTES_OK,
							datosMTI);
				} else {
					listIGui.get(Evento.MOSTRAR_TODOS_INGREDIENTES).actualizar(Evento.RES_MOSTRAR_TODOS_INGREDIENTES_KO,
							null);
				}
				break;

			case Evento.ACTUALIZAR_INGREDIENTE:
				TIngredientes tIngrediente_actualizar = (TIngredientes) datos;
				int datosUI = FactoriaAbstractaNegocio.getInstance().createSAIngredientes()
						.update(tIngrediente_actualizar);

				if (datosUI > 0) {
					listIGui.get(Evento.ACTUALIZAR_INGREDIENTE).actualizar(Evento.RES_ACTUALIZAR_INGREDIENTE_OK,
							datosUI);
				} else {
					listIGui.get(Evento.ACTUALIZAR_INGREDIENTE).actualizar(Evento.RES_ACTUALIZAR_INGREDIENTE_KO, null);
				}
				break;
			case Evento.MOSTRAR_INGREDIENTES_PROVEEDOR:
				Collection<TIngredientes> datosMPI = FactoriaAbstractaNegocio.getInstance().createSAIngredientes()
						.readByProveedor((int) datos);

				if (datosMPI != null) {
					listIGui.get(Evento.MOSTRAR_INGREDIENTES_PROVEEDOR)
							.actualizar(Evento.RES_MOSTRAR_INGREDIENTES_PROVEEDOR_OK, datosMPI);
				} else {
					listIGui.get(Evento.MOSTRAR_INGREDIENTES_PROVEEDOR)
							.actualizar(Evento.RES_MOSTRAR_INGREDIENTES_PROVEEDOR_KO, null);
				}
				break;
			//FACTURA
			case Evento.ALTA_FACTURA:
				TFacturaConProductos tFactura_alta = (TFacturaConProductos) datos;
				int datosAF = FactoriaAbstractaNegocio.getInstance().createSAFactura()
						.create(tFactura_alta);

				if (datosAF > 0) {
					listIGui.get(Evento.ALTA_FACTURA).actualizar(Evento.RES_ALTA_FACTURA_OK, datosAF);
				} else {
					listIGui.get(Evento.ALTA_FACTURA).actualizar(Evento.RES_ALTA_FACTURA_KO, null);
				}
				break;
			case Evento.MOSTRAR_FACTURA:

				TFacturaConProductos datosMF = FactoriaAbstractaNegocio.getInstance().createSAFactura()
						.read((int) datos);

				if (datosMF != null) {
					listIGui.get(Evento.MOSTRAR_FACTURA).actualizar(Evento.RES_MOSTRAR_FACTURA_OK, datosMF);
				} else {
					listIGui.get(Evento.MOSTRAR_FACTURA).actualizar(Evento.RES_MOSTRAR_FACTURA_KO, null);
				}

				break;
			case Evento.BAJA_FACTURA:
				int datosBF = FactoriaAbstractaNegocio.getInstance().createSAFactura().delete((int) datos);

				if (datosBF != -1) {

					listIGui.get(Evento.BAJA_FACTURA).actualizar(Evento.RES_BAJA_FACTURA_OK, datosBF);

				} else {
					listIGui.get(Evento.BAJA_FACTURA).actualizar(Evento.RES_BAJA_FACTURA_KO, null);
				}

				break;

			case Evento.MOSTRAR_TODAS_FACTURAS:

				Collection<TFacturaConProductos> datosMTF = FactoriaAbstractaNegocio.getInstance().createSAFactura()
						.readAll();

				if (datosMTF != null) {
					listIGui.get(Evento.MOSTRAR_TODAS_FACTURAS).actualizar(Evento.RES_MOSTRAR_TODAS_FACTURAS_OK,
							datosMTF);
				} else {
					listIGui.get(Evento.MOSTRAR_TODAS_FACTURAS).actualizar(Evento.RES_MOSTRAR_TODAS_FACTURAS_KO, null);
				}
				break;

			case Evento.ACTUALIZAR_FACTURA:
				TFacturaConProductos tFactura_actualizar = (TFacturaConProductos) datos;
				int datosUF = FactoriaAbstractaNegocio.getInstance().createSAFactura().update(tFactura_actualizar);

				if (datosUF > 0) {
					listIGui.get(Evento.ACTUALIZAR_FACTURA).actualizar(Evento.RES_ACTUALIZAR_FACTURA_OK, datosUF);
				} else {
					listIGui.get(Evento.ACTUALIZAR_FACTURA).actualizar(Evento.RES_ACTUALIZAR_FACTURA_KO, null);
				}
				break;
			case Evento.MOSTRAR_FACTURA_CLIENTE:
				Collection<TFacturaConProductos> datosMCF = FactoriaAbstractaNegocio.getInstance().createSAFactura()
						.readByCliente((int) datos);

				if (datosMCF != null) {
					listIGui.get(Evento.MOSTRAR_FACTURA_CLIENTE).actualizar(Evento.RES_MOSTRAR_FACTURA_CLIENTE_OK,
							datosMCF);
				} else {
					listIGui.get(Evento.MOSTRAR_FACTURA_CLIENTE).actualizar(Evento.RES_MOSTRAR_FACTURA_CLIENTE_KO,
							null);
				}
				break;

		}
	}

	@Override
	public void registerView(int operacion, IGUI i) {
		listIGui.put(operacion, i);
	}

}
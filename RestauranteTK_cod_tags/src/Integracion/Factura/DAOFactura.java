/**
 * 
 */
package Integracion.Factura;

import Negocio.Factura.TFactura;

import java.util.Collection;

public interface DAOFactura {
	
	/**@return Valor int id de la factura creada, -1 en caso fallido*/
	public int create(TFactura tFactura);

	/**@return Valor int id de la factura borrada, -1 en caso fallido*/
	public int delete(int idFactura);

	/**@return TFactura de la factura con el id introducido, null en caso fallido*/
	public TFactura read(int idFactura);

	/**@return Collection de las facturas, Collection vacía en caso fallido*/
	public Collection<TFactura> readAll();

	/**@return Valor int id de la factura actualizada, -1 en caso fallido*/
	public int update(TFactura tFactura);

	/**@return Collection de las facturas con el idCliente introducido, Collection vacía en caso fallido*/
	public Collection<TFactura> readByCliente(int idCliente);
}
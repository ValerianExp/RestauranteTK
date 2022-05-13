/**
 * 
 */
package Integracion.Factura;

import java.util.Collection;

import Negocio.Factura.TLineaFactura;

public interface DAOLineaFactura {

	/**@param idFactura Id de la factura a la que corrsponde la linea	 
	 * @return Valor int id de la factura a la que corresponde la linea, -1 en caso fallido
	*/
	public int create(TLineaFactura tLineaFactura, int idFactura);

	/**@param idFactura Id de la factura de la que se quiere borrar la línea
	 * @param idProducto Id del producto que se quiere borrar
	 * @return Valor int id de la factura a la que corresponde la linea, -1 en caso fallido
	*/
	public int delete(int idFactura,int idProducto);

	/**@param idFactura Id de la factura correspondiente a la línea a leer
	 * @param idProducto Id del producto correspondiente a la línea a leer
	 * @return TLineaFactura de la línea con los id correspondientes, null en caso de que no exista 
	*/
	public TLineaFactura read(int idFactura, int idProducto);

	/**@return Valor int id de la factura correspondiente a la línea actualizada, -1 en caso fallido*/
	public int update(TLineaFactura tLineaFactura);

	/**@return Collection de los TLineaFactura correspondientes a una factura, Collection vacía en caso fallido */
	public Collection<TLineaFactura> readByFactura(int idFactura);

	/**@return Valor int id de la factura de la que se han borrado las líneas, -1 en caso fallido*/
	public int deleteByFactura(int idFactura);
}
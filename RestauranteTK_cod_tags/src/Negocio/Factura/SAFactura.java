/**
 * 
 */
package Negocio.Factura;

import java.util.Collection;

public interface SAFactura {

	public int create(TFacturaConProductos tFactura);

	public int delete(int idFactura);

	public TFacturaConProductos read(int idFactura);
	
	public Collection<TFacturaConProductos> readByCliente(int idCliente);

	public Collection<TFacturaConProductos> readAll();

	public int update(TFacturaConProductos tFactura);
	
}

package Negocio.Factura;

public class TCarrito {

	private int idProducto;

	private int cantidad;
	
	public TCarrito(int idProducto,int cantidad){
		this.idProducto = idProducto;
		this.cantidad = cantidad;
	}

	public int getIdProducto(){
		return idProducto;
	}
	public int getCantidad() {
		return cantidad;
	}
}
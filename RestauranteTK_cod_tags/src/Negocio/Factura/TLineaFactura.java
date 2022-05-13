
package Negocio.Factura;

public class TLineaFactura {
	
	private int idFactura;
	private TCarrito tCarrito;
	private int precio;
	private boolean activo;
	
	public TLineaFactura(TCarrito tCarrito, int precio) {
		this.tCarrito = tCarrito;
		this.precio = precio;
		this.activo=true;
	}
	
	@Override
	public boolean equals(Object object) {
		 return this.getIdProducto() == ((TLineaFactura)  object).getIdProducto();
	}
	

	public int getIdFactura() {
		return idFactura;
	}
	public int getIdProducto() {
		return tCarrito.getIdProducto();
	}
	
	public int getCantidad() {
		return tCarrito.getCantidad();
	}
	public TCarrito gettCarrito() {
		return tCarrito;
	}
	
	public int getPrecio() {
		return precio;
	}
	public boolean getActivo(){
		return activo;
	}
	
	public void setIdFactura(int idFactura){
		this.idFactura=idFactura;
	}
	
	public void setActivo(boolean activo){
		this.activo=activo;
	}
	
	
	@Override
	public String toString() {
		// @formatter:off
		return "producto: " + this.getIdProducto() + ' ' +
				"cantidad: " + this.getCantidad() + ' '+
				"precio:  " + this.getPrecio() +"\n"; 

				
		// @formatter:on
	}
}
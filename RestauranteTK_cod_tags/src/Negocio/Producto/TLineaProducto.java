package Negocio.Producto;

public class TLineaProducto {
		
	private int idIngrediente;
	
	private int Cantidad;
	
	private boolean tieneGluten;
	
	
	public TLineaProducto( int idIngrediente, int Cantidad, boolean tieneGluten){
		this.idIngrediente = idIngrediente;
		this.Cantidad = Cantidad;
		this.tieneGluten = tieneGluten;
	}
	
	@Override
	public boolean equals(Object object) {
		return this.getIdIngrediente() == ((TLineaProducto)  object).getIdIngrediente();
	}
	
	
	public int getIdIngrediente(){
		return idIngrediente;
	}
	
	public void setIdIbgrediente(int idIngrediente){
		this.idIngrediente = idIngrediente;
	}
	
	
	public int getCantidad(){
		return Cantidad;
	}
	
	public void setCantidad(int Cantidad){
		this.Cantidad = Cantidad;
		
	}
	
	public boolean getGluten(){
		return tieneGluten;
	}
	
	public void setGluten(boolean tieneGluten){
		this.tieneGluten = tieneGluten;
	}
	
	
}

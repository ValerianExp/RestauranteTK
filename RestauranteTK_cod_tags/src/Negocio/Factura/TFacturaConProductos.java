package Negocio.Factura;

import java.sql.Date;
import java.util.Collection;


import Integracion.Factura.Tipodepago;

public class TFacturaConProductos {
	private Collection<TLineaFactura> tLineasFactura;
	//private Collection<TProducto> productos; para mostrar nombres
	private TFactura factura;
	
	public TFacturaConProductos(Collection<TLineaFactura> comandas, TFactura factura){
		this.tLineasFactura=comandas;
		this.factura= factura;
	}
	public TFacturaConProductos(Collection<TLineaFactura> comandas, int idCliente, int idFactura, int idCamarero, Tipodepago pago){
		this.tLineasFactura=comandas;
		Date fecha = new Date(System.currentTimeMillis());
		this.factura= new TFactura( idFactura,fecha,  pago, 0, idCamarero, idCliente);
		
	}
	
	public Collection<TLineaFactura> getLineaFactura(){
		return this.tLineasFactura;
	}
	
	public void setLineFactura(Collection<TLineaFactura> tLineasFactura){
		this.tLineasFactura=tLineasFactura;
	} 
	
	public TFactura getFactura(){
		return this.factura;
	}
	public void setFactura(TFactura factura){
		this.factura=factura;
	}
	
	
	private String lineasToString(){
		StringBuffer lineas = new StringBuffer();
		for(TLineaFactura l: tLineasFactura)
			lineas.append(l.toString());
		return lineas.toString();
		
	}
	
	@Override
	public String toString() {
		// @formatter:off
		return this.factura.toString()+ lineasToString();
		// @formatter:on
	}
	
}

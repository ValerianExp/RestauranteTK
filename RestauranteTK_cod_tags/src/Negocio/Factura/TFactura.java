package Negocio.Factura;

import java.sql.Date;
import Integracion.Factura.Tipodepago;


public class TFactura {

	private int id;
	private Date fecha;
	private Tipodepago pago;
	private int importe;
	private int IDCamarero;
	private int IDCliente;
	private boolean activo;

	public int getID() {
		return id;
	}

	public Date getFecha() {
		return fecha;
	}

	public Tipodepago getPago() {
		return pago;
	}

	public int getImporte() {
		return importe;
	}

	public int getIDCamarero() {
		return IDCamarero;
	}

	public int getIDCliente() {
		return IDCliente;
	}
	
	public boolean getActivo(){
		return activo;
	}

	public void setID(int id) {
		this.id = id;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public void setPago(Tipodepago pago) {
		this.pago = pago;
	}

	public void setImporte(int importe) {
		this.importe = importe;
	}

	public void setIDCamarero(int IDCamarero) {
		this.IDCamarero = IDCamarero;
	}

	public void setIDCliente(int IDCliente) {
		this.IDCliente = IDCliente;
	}
	
	public void setActivo(boolean activo){
		this.activo=activo;
	}
	
	/**
	 * Constructor TFactura que usa la clase Tipodepago
	 * @see #TFactura(int, Date, String, int, int, int) Constructor que usa String
	 */
	public TFactura(int id, Date fecha, Tipodepago pago, int importe, int IDCamarero, int IDCliente) {
		this.id = id;
		this.fecha = fecha;
		this.pago = pago;
		this.importe = importe;
		this.IDCamarero = IDCamarero;
		this.IDCliente = IDCliente;
		this.activo=true;
	}
	
	/**
	 * @see #TFactura(int, Date, Tipodepago, int, int, int)
	  
	 */
	public TFactura(int id, Date fecha, String pago, int importe, int IDCamarero, int IDCliente) {
		/* Se puede envolver el valueOf en un try catch para enviar una excepcion propia, se puede
		  hacer por fuera */
		this(id,fecha, Tipodepago.valueOf(pago),importe,IDCamarero,IDCliente);
	}
	
	
	@Override
	public String toString() {
		// @formatter:off
		return "id: " + this.id + '\n' +
				"fecha: " + this.fecha + '\n' 
				+ "pago: " + this.pago + '\n'
				+"importe: "+ this.importe +"\n"
				+"id camarero: "+ this.IDCamarero+"\n"
				+"id Cliente: "+ this.IDCliente+"\n";
				
		// @formatter:on
	}
}
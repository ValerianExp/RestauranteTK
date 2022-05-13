/**
 * 
 */
package Negocio.Producto;

import java.util.Collection;
import java.util.Iterator;

import Negocio.Ingredientes.TIngredientes;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author RestauranteTK
 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class TComida extends TProducto {
	
	private boolean tieneGluten;

	public TComida(int id, int precio, String nombre, int idChef, Collection<TLineaProducto> ingredientes,
			boolean activo) {
		super(id, nombre, precio, idChef, "comida", activo);
		tieneGluten = false;
		this.ingredientes = ingredientes;
		Iterator <TLineaProducto> it= this.ingredientes.iterator();
		while(it.hasNext() && !tieneGluten) {
			  if(it.next().getGluten()) 
				  tieneGluten = true;
		}
	}
	
	@Override
	public String toString() {
		String gluten = "No";
		if(tieneGluten) gluten = "Si";
		String ingre="";
		for(TLineaProducto i: ingredientes) ingre += i.getIdIngrediente() + " ";
		
		return super.toString() + 
				"Tiene gluten: " + gluten + '\n' + 
				"ingredientes: " + ingre + '\n' ;
				
	}
	

	private Collection<TLineaProducto> ingredientes;

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public Collection<TLineaProducto> getIngredientes() {
		// begin-user-code 
		return ingredientes;
		// end-user-code
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public void setIngredientes(Collection<TLineaProducto> ingredientes) {
		// begin-user-code
		this.ingredientes = ingredientes;
		Iterator <TLineaProducto> it= this.ingredientes.iterator();
		while(it.hasNext() && !tieneGluten) {
			  if(it.next().getGluten())
				  tieneGluten = true;
		}
		// end-user-code
	}
	
	public boolean getTieneGluten(){
		return tieneGluten;
	}
	
	public void setTieneGluten(boolean tieneGluten){
		this.tieneGluten = tieneGluten;
	}
}
/**
 * 
 */
package Negocio.Producto;

import java.util.Collection;

import Integracion.Factoria.FactoriaAbstractaIntegracion;
import Integracion.Producto.DAOProducto;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author RestauranteTK
 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class SAProductoImp implements SAProducto {
	/** 
	* (non-Javadoc)
	* @see SAProducto#create()
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public int create(TProducto producto) {
		// begin-user-code

		int id = -1;
		DAOProducto daoProducto;
		//Acceso a la implemetacion del DAO
		daoProducto = FactoriaAbstractaIntegracion.getInstance().createDAOProducto();


		if (producto != null) {
			TProducto tProducto = daoProducto.read(id);
			if (tProducto == null) {
				id = daoProducto.create(producto);
			}
		}
		return id;
		// end-user-code
	}

	/** 
	* (non-Javadoc)
	* @see SAProducto#delete()
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public int delete(int idProducto ) {
		// begin-user-code
		int id=-1;
		DAOProducto daoProducto;
		daoProducto=FactoriaAbstractaIntegracion.getInstance().createDAOProducto();

		if(daoProducto.read(idProducto) != null){
			
			id = daoProducto.delete(idProducto);

		}

		return id;
		// end-user-code
	}

	/** 
	* (non-Javadoc)
	* @see SAProducto#read()
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public TProducto read(int idProducto) {
		// begin-user-code
		return FactoriaAbstractaIntegracion.getInstance().createDAOProducto()
				.read(idProducto);
		// end-user-code
	}

	/** 
	* (non-Javadoc)
	* @see SAProducto#update()
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public int update(TProducto tProducto) {
		// begin-user-code
		DAOProducto daoProducto= FactoriaAbstractaIntegracion.getInstance().createDAOProducto();
		if(daoProducto!=null){
			int aux = daoProducto.update(tProducto);
			if (aux == -1) {
				return -1;
			}
		}
		return tProducto.getId();
		// end-user-code
	}

	/** 
	* (non-Javadoc)
	* @see SAProducto#readAll()
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public Collection<TProducto> readAll() {
		// begin-user-code
		DAOProducto daoProducto = FactoriaAbstractaIntegracion.getInstance().createDAOProducto();
		if(daoProducto != null){
			
			return daoProducto.readAll();
			
		}
		
		
		return null;
		// end-user-code
	}
}
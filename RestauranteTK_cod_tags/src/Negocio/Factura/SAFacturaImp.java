package Negocio.Factura;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

import Integracion.Factoria.FactoriaAbstractaIntegracion;
import Integracion.Factura.DAOFactura;
import Integracion.Factura.DAOLineaFactura;
import Integracion.Ingredientes.DAOIngredientes;
import Integracion.Producto.DAOProducto;
import Negocio.Ingredientes.TIngredientes;
import Negocio.Producto.TComida;
import Negocio.Producto.TLineaProducto;
import Negocio.Producto.TProducto;

public class SAFacturaImp implements SAFactura { 
	
	/*  Opciones para mejorar las operaciones,
		1.Gestionar transacciones desde la SA haciendo que los DAO compartan una conexion y hacer commit al final
			o rollback segun se necesite. Esta sería la solución ideal
		2.Gestionarlo desde la base de datos con triggers-> la lógica de negocio pasaría a estar en la BD ):
		3. Tambien se puede hacer un DAO muy enriquecido que haga todas las operaciones necesarias,
    	-> la lógica del negocio pasaria a este DAO ):	
	 */ 
	@Override
	public int create(TFacturaConProductos facturaConProd) {
	
		int id = -1;
		DAOFactura daoFactura = FactoriaAbstractaIntegracion.getInstance().createDAOFactura();
		DAOLineaFactura daoLineaFactura =FactoriaAbstractaIntegracion.getInstance().createDAOLineaFactura();
		DAOIngredientes daoIngredientes= FactoriaAbstractaIntegracion.getInstance().createDAOIngredientes();
		
		//Clave:idIngrediente Valor:cantidad
		HashMap<Integer,Integer> necesarios= this.getIngredientesNec(facturaConProd);
		
		//Clave:idIngrediente Valor:cantidad
		HashMap<Integer,Integer> faltantes= new HashMap<Integer,Integer>();
		
		//para guardar los TIngredientes con la cantidad nueva
		ArrayList<TIngredientes> listaActualizar=new ArrayList<TIngredientes>();
		
		/*se puede hacer con un while + iterator de entry set y acabar cuando se
		  encuentra un ingrediente que no tiene cantidad suficiente, entonces no 
		  eliminariamos el mapa faltantes
		  */
		for(Entry<Integer, Integer> e:necesarios.entrySet()){
			int idIng =e.getKey();
			int cantNec=e.getValue();
			TIngredientes ing= daoIngredientes.read(idIng);
			if(ing!=null){
				int cantDisp= ing.getCantidad();
				if(cantDisp>=cantNec){
					ing.setCantidad(cantDisp-cantNec);
					listaActualizar.add(ing);
				}
				else{
					faltantes.put(idIng, cantNec-cantDisp);
				}
			}else{
				faltantes.put(idIng, cantNec);
			}
		}
		if(!(faltantes.size()>0)){ 
			//Factura
			TFactura factura= facturaConProd.getFactura();
			if(factura!=null){
					id= daoFactura.create(factura);
				if(id>0){
					for(TLineaFactura lf: facturaConProd.getLineaFactura()){
						if(lf!= null){
							daoLineaFactura.create(lf,id);
							for(TIngredientes i: listaActualizar)
								daoIngredientes.update(i);
						}
					}
				}
			}
		}else throw new IllegalArgumentException("Faltan los ingredientes"+ faltantes.toString());
		
		return id;
	}

	@Override
	public int delete(int idFactura) {
		DAOFactura daoFactura = FactoriaAbstractaIntegracion.getInstance().createDAOFactura();
		DAOLineaFactura daoLineaProducto =FactoriaAbstractaIntegracion.getInstance().createDAOLineaFactura();
		DAOIngredientes daoIngredientes= FactoriaAbstractaIntegracion.getInstance().createDAOIngredientes();

		TFacturaConProductos facturaConProd = this.read(idFactura);
		if(facturaConProd!=null){
			//Clave:idIngrediente Valor:cantidad
			HashMap<Integer,Integer> usados= this.getIngredientesNec(facturaConProd);
			if(daoFactura.delete(idFactura) > 0){
				daoLineaProducto.deleteByFactura(idFactura);

				for(Entry<Integer, Integer> e:usados.entrySet()){
					int idIng =e.getKey();
					int cantUsada=e.getValue();
					TIngredientes ing= daoIngredientes.read(idIng);
					if(ing!=null){ //Ignora los ingredientes no activos
						ing.setCantidad(ing.getCantidad()+cantUsada);
						daoIngredientes.update(ing);
					}
				}
				
			}
		}
		
		else{
			return -1;
		}
		 return idFactura;
	}

	@Override
	public TFacturaConProductos read(int idFactura) {
		
		TFactura factura= FactoriaAbstractaIntegracion.getInstance().createDAOFactura().read(idFactura); 
		Collection<TLineaFactura> lineasFactura= FactoriaAbstractaIntegracion.getInstance().createDAOLineaFactura()
				.readByFactura(idFactura);
		return new TFacturaConProductos(lineasFactura, factura);

	}
	
	@Override
	public Collection<TFacturaConProductos> readByCliente(int idCliente) {
		Collection<TFacturaConProductos> facturasConProd = new ArrayList<TFacturaConProductos>();
		Collection<TFactura> facturas = FactoriaAbstractaIntegracion.getInstance().createDAOFactura().readByCliente(idCliente);
		for(TFactura f:facturas){
			Collection<TLineaFactura> lineas = FactoriaAbstractaIntegracion.getInstance().createDAOLineaFactura().readByFactura(f.getID());
			facturasConProd.add(new TFacturaConProductos(lineas,f));
		}
		return facturasConProd;
	}

	@Override
	public int update(TFacturaConProductos tFacturaConProd) {
		DAOFactura daoFactura =  FactoriaAbstractaIntegracion.getInstance().createDAOFactura();
		DAOLineaFactura daoLineaFactura =FactoriaAbstractaIntegracion.getInstance().createDAOLineaFactura();
		DAOIngredientes daoIngredientes= FactoriaAbstractaIntegracion.getInstance().createDAOIngredientes();

		//COMPROBACIONES
		int id = -1;
		TFacturaConProductos original = this.read(tFacturaConProd.getFactura().getID());

		HashMap<Integer,Integer> faltantes= new HashMap<Integer,Integer>();
		ArrayList<TIngredientes> listaActualizar=new ArrayList<TIngredientes>();
		HashMap<Integer,Integer> necesitados= this.getIngredientesNec(tFacturaConProd);
		HashMap<Integer,Integer> usados= new HashMap<Integer,Integer>();
		if(original!=null){
			usados= this.getIngredientesNec(original);
		}
		HashSet<Integer> total= new HashSet<Integer>();
		total.addAll(necesitados.keySet());
		total.addAll(usados.keySet());
	
		for(Integer i: total){
			TIngredientes ing= daoIngredientes.read(i);
			int cantNec= necesitados.getOrDefault(i,0)-usados.getOrDefault(i,0);
			if(ing!=null){ //Ignora los ingredientes no activos
				int cantDisp= ing.getCantidad();
				if(cantNec<cantDisp){
					ing.setCantidad(cantDisp-(necesitados.getOrDefault(i,0)-usados.getOrDefault(i,0)));
					listaActualizar.add(ing);
				}else faltantes.put(i, cantNec-cantDisp);
				
			}else faltantes.put(i, cantNec);
		}
		
		if( faltantes.size()<=0){
			//OPERACIONES
			id=daoFactura.update(tFacturaConProd.getFactura());
			if(id>0){
				daoLineaFactura.deleteByFactura(id);
				for(TLineaFactura lf: tFacturaConProd.getLineaFactura()){
					lf.setIdFactura(id);
					if(daoLineaFactura.read(lf.getIdFactura(), lf.getIdProducto())==null){
						daoLineaFactura.create(lf, id);
					}else daoLineaFactura.update(lf);
				}
				for(TIngredientes i: listaActualizar)
					daoIngredientes.update(i);
			}
		} else throw new IllegalArgumentException("Faltan los ingredientes"+ faltantes.toString());
		return id;
	}

	@Override
	public Collection<TFacturaConProductos> readAll() {	
		
		Collection<TFacturaConProductos> facturasConProd = new ArrayList<TFacturaConProductos>();
		Collection<TFactura> facturas = FactoriaAbstractaIntegracion.getInstance().createDAOFactura().readAll();
		for(TFactura f:facturas){
			Collection<TLineaFactura> lineas = FactoriaAbstractaIntegracion.getInstance().createDAOLineaFactura().readByFactura(f.getID());
			facturasConProd.add(new TFacturaConProductos(lineas,f));
		}
		
		return facturasConProd;
	}
	

	private HashMap<Integer,Integer> getIngredientesNec(TFacturaConProductos facturaConProd){
		DAOProducto daoProducto =FactoriaAbstractaIntegracion.getInstance().createDAOProducto();

		//Clave:idIngrediente Valor:cantidad
		HashMap<Integer,Integer> necesarios= new HashMap<Integer,Integer>();
		
		for(TLineaFactura lf: facturaConProd.getLineaFactura()){// para cada producto
			TProducto producto= daoProducto.read(lf.getIdProducto()); 
			if (producto instanceof TComida){ //si es comida
				Collection<TLineaProducto>Ingredientes = ((TComida) producto).getIngredientes(); 
				for(TLineaProducto lp:Ingredientes){ //añadimos a cada ingrediente y su cantidad 
					int idIng=lp.getIdIngrediente();
					necesarios.put(idIng, necesarios.getOrDefault(idIng, 0) + (lp.getCantidad()*lf.getCantidad()) );
				}
			}
		}
		return necesarios;
	}


}
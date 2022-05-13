/**
 * 
 */
package Integracion.Producto;

import Negocio.Empleado.TChef;
import Negocio.Ingredientes.TIngredientes;
import Negocio.Producto.TBebida;
import Negocio.Producto.TComida;
import Negocio.Producto.TLineaProducto;
import Negocio.Producto.TProducto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author RestauranteTK
 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class DAOProductoImp implements DAOProducto {
	public static final String URL = "jdbc:mysql://tk-is2.mysql.database.azure.com";
	public static final String USER = "restaurante_admin";
	public static final String PASSWORD = "NfBff6ZrLsxgHYP";

	/** 
	* (non-Javadoc)
	* @see DAOProducto#create(TProducto p)
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public int create(TProducto p) {
		// begin-user-code
		Connection connection = null;
		int id = -1;
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement ps = connection.prepareStatement(
					"INSERT INTO restaurante.producto (id, nombre, precio, idChef, tipoProducto, activo) VALUES(?, ?, ?, ?, ?,?)",
					Statement.RETURN_GENERATED_KEYS); 
			ps.setInt(1, p.getId());
			ps.setString(2, p.getNombre());
			ps.setInt(3, p.getPrecio());
			ps.setInt(4, p.getIdChef());
		    ps.setString(5, p.getTipoProducto());
			ps.setBoolean(6, true);
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getInt(1);
			}
			if(p.getTipoProducto().equals("bebida")){
				ps = connection.prepareStatement(
						"INSERT INTO restaurante.bebida (id, marca, activo) VALUES(?, ?, ?)",
						Statement.RETURN_GENERATED_KEYS);
				TBebida tBebida = (TBebida) p;
				ps.setInt(1, id);
				ps.setString(2, tBebida.getMarca());
				ps.setBoolean(3, true);
				ps.executeUpdate();
			}
			else if(p.getTipoProducto().equals("comida")){
				TComida tComida = (TComida) p;
				boolean gluten = false;
				for(TLineaProducto i : tComida.getIngredientes()){
					ps = connection.prepareStatement(
							"INSERT INTO restaurante.tienen (idProducto, idIngrediente, cantidad, activo) VALUES(?, ?, ?, ?)",
							Statement.RETURN_GENERATED_KEYS);
					ps.setInt(1, id);
					ps.setInt(2, i.getIdIngrediente());
					ps.setInt(3, i.getCantidad());
					ps.setBoolean(4, true);
					
					PreparedStatement psg = connection.prepareStatement(
							"SELECT tieneGluten FROM restaurante.ingredientes WHERE id = ?",
							Statement.RETURN_GENERATED_KEYS);
					psg.setInt(1, i.getIdIngrediente());
					ResultSet rsg = psg.executeQuery();
					
					while(rsg.next()){
						if(rsg.getBoolean(1) == true){
							gluten = true;
							System.out.println("tiene");
						}
						
					}
					
					ps.executeUpdate();
				}
				
				
				
				ps = connection.prepareStatement(
						"INSERT INTO restaurante.comida (id, activo, idChef, tieneGluten) VALUES(?, ?, ?, ?)",
						Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, id);
				ps.setBoolean(2, true);
				ps.setInt(3, tComida.getIdChef());
				ps.setBoolean(4, gluten);
				ps.executeUpdate();
			
			}

			ps.close();
			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
		return id;
		// end-user-code
	}

	/** 
	* (non-Javadoc)
	* @see DAOProducto#delete(int idProducto)
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public int delete(int idProducto) {
		// begin-user-code
		Connection connection = null;

		try {

			connection = DriverManager.getConnection(URL, USER, PASSWORD);

			PreparedStatement ps = connection.prepareStatement(
					"SELECT tipoProducto FROM restaurante.producto WHERE id = ?",
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, idProducto);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
			

				if(rs.getString("tipoProducto").equals("bebida")){
	
					ps = connection.prepareStatement(
							"UPDATE restaurante.bebida SET activo = false WHERE id = ? AND activo = true",
							Statement.RETURN_GENERATED_KEYS);
					ps.setInt(1, idProducto);
					ps.executeUpdate();
					
	
				}
				else if(rs.getString("tipoProducto").equals("comida")){
	
					ps = connection.prepareStatement(
							"UPDATE restaurante.comida SET activo = false WHERE id = ? AND activo = true",
							Statement.RETURN_GENERATED_KEYS);
					ps.setInt(1, idProducto);
					ps.executeUpdate();
	
					
					ps = connection.prepareStatement(
							"DELETE FROM restaurante.tienen WHERE idProducto = ?",
							Statement.RETURN_GENERATED_KEYS);
					ps.setInt(1, idProducto);
					ps.executeUpdate();
	
				}
				
				
				ps = connection.prepareStatement(
						"UPDATE restaurante.producto SET activo = false WHERE id = ? AND activo = true", //false
						Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, idProducto);
				ps.executeUpdate();
				
			}

			
			ps.close();
			connection.close();	
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}

		return idProducto;
		// end-user-code
	}

	/** 
	* (non-Javadoc)
	* @see DAOProducto#read(int idProducto)
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public TProducto read(int idProducto) {
		// begin-user-code
		TProducto tProducto = null;
		Connection connection = null;

		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement ps = connection.prepareStatement(
					"SELECT * FROM restaurante.producto WHERE id = ? AND activo = true",
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, idProducto);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				if (rs.getString("tipoProducto").equals("comida")) {
					ps = connection.prepareStatement("SELECT * FROM restaurante.comida WHERE id = ? AND activo = true",
							Statement.RETURN_GENERATED_KEYS);
					ps.setInt(1, idProducto);
					ResultSet rsComida = ps.executeQuery();
					
					Collection<TLineaProducto> aux = new ArrayList<>();
					
					ps = connection.prepareStatement("SELECT * FROM restaurante.tienen WHERE idProducto = ?",
							Statement.RETURN_GENERATED_KEYS);
					ps.setInt(1, idProducto);
					ResultSet rsTienen = ps.executeQuery();
					
					while(rsTienen.next()){
						
						TLineaProducto tIngredientes = new TLineaProducto(rsTienen.getInt("idIngrediente"),rsTienen.getInt("cantidad"),false);
						aux.add(tIngredientes);
					}
					
					if (rsComida.next()) {
						// @autoformatter: off
						tProducto = new TComida(idProducto, 
								rs.getInt("precio"),
								rs.getString("nombre"),
								rs.getInt("idChef"),
								aux, 
								rs.getBoolean("activo"));
						// @autoformatter: on
					}
					

					ps.close();
				}
				else if (rs.getString("tipoProducto").equals("bebida")) {
					ps = connection.prepareStatement("SELECT * FROM restaurante.bebida WHERE id = ? AND activo = true",
							Statement.RETURN_GENERATED_KEYS);
					ps.setInt(1, idProducto);
					ResultSet rsBebida = ps.executeQuery();
					
					if (rsBebida.next()) {
						

						// @autoformatter: off
						tProducto = new TBebida(idProducto, 
								rs.getInt("precio"),
								rs.getString("nombre"),
								rs.getInt("idChef"),
								rsBebida.getString("marca"),
								rs.getBoolean("activo"));
						// @autoformatter: on
					}
					
				}
				else{
					throw new RuntimeException("Tipo de producto no encontrado");
				}
				
				
			}
			
			
			ps.close();
			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
		return tProducto;
		// end-user-code
	}

	/** 
	* (non-Javadoc)
	* @see DAOProducto#update(TProducto p)
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public int update(TProducto p) {
		// begin-user-code
		int id = -1;
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			String tipo="";
			PreparedStatement check = connection.prepareStatement(
					"SELECT * FROM restaurante.producto WHERE id = ?",
					Statement.RETURN_GENERATED_KEYS);
			check.setInt(1, p.getId());
			ResultSet checkRs = check.executeQuery();
			if(checkRs.next()){
				tipo=checkRs.getString("tipoProducto");
			}check.close();
			if(tipo=="")
				throw new IllegalArgumentException("No existe ningún producto con este id");
			if(!tipo.equalsIgnoreCase(p.getTipoProducto()))
				throw new IllegalArgumentException("No se puede cambiar el tipo de producto");

			
			
			PreparedStatement ps = connection.prepareStatement(
					"UPDATE restaurante.producto SET nombre = ?, precio = ?, idChef = ?, tipoProducto = ?, activo = ? WHERE id = ?",
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, p.getNombre());
			ps.setInt(2, p.getPrecio());
			ps.setInt(3, p.getIdChef());
			ps.setString(4, p.getTipoProducto());
			ps.setBoolean(5, true);
			id = p.getId();
			ps.setInt(6, id);
			ps.executeUpdate();
			
			if(p.getTipoProducto().equals("bebida")){
				ps = connection.prepareStatement(
						"UPDATE restaurante.bebida SET marca = ?, activo = ? WHERE id = ?",
						Statement.RETURN_GENERATED_KEYS);
				TBebida tBebida = (TBebida) p;
				ps.setInt(3, id);
				ps.setString(1, tBebida.getMarca());
				ps.setBoolean(2, true);
				ps.executeUpdate();
				id = tBebida.getId();
			}
			else if(p.getTipoProducto().equals("comida")){
				ps = connection.prepareStatement(
						"UPDATE restaurante.comida SET idChef = ?, tieneGluten = ? WHERE id = ?",
						Statement.RETURN_GENERATED_KEYS);
				TComida tComida = (TComida) p;
				ps.setInt(3, id);
				ps.setBoolean(2, true);
				ps.setInt(1, tComida.getIdChef());
				ps.executeUpdate();
					ps = connection.prepareStatement(
							"DELETE FROM restaurante.tienen WHERE idProducto = ?",
							Statement.RETURN_GENERATED_KEYS);
					ps.setInt(1, id);
					ps.executeUpdate();
					for(TLineaProducto i : tComida.getIngredientes()){
						ps = connection.prepareStatement(
								"INSERT INTO restaurante.tienen (idProducto, idIngrediente, cantidad, activo) VALUES(?, ?, ?, ?)",
								Statement.RETURN_GENERATED_KEYS);
						ps.setInt(1, id);
						ps.setInt(2, i.getIdIngrediente());
						ps.setInt(3, i.getCantidad());
						ps.setBoolean(4, true);
						ps.executeUpdate();
					}
			
			}
			

			
			ps.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return -1;
		}
		return id;
		// end-user-code
	}

	/** 
	* (non-Javadoc)
	* @see DAOProducto#readAll()
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public Collection<TProducto> readAll() {
		// begin-user-code
		Collection<TProducto> list = new ArrayList<TProducto>();
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement ps = connection.prepareStatement(
					"SELECT * FROM restaurante.producto WHERE activo = true",
					Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				//TProducto tProducto = new TProducto(rs.getInt("id"), rs.getString("nombre"), rs.getInt("precio"), rs.getInt("idChef"), rs.getString("tipoProducto") ,rs.getBoolean("activo"));
				TProducto tProducto = null;
				
				
				
				if (rs.getString("tipoProducto").equals("comida")) {
					ps = connection.prepareStatement("SELECT * FROM restaurante.comida WHERE id = ? AND activo = true",
							Statement.RETURN_GENERATED_KEYS);
					ps.setInt(1, rs.getInt("id"));
					ResultSet rsComida = ps.executeQuery();
					
					Collection<TLineaProducto> aux = new ArrayList<>();
					
					ps = connection.prepareStatement("SELECT * FROM restaurante.tienen WHERE idProducto = ?",
							Statement.RETURN_GENERATED_KEYS);
					ps.setInt(1, rs.getInt("id"));
					ResultSet rsTienen = ps.executeQuery();
					
					
					if (rsComida.next()) {
						
						while(rsTienen.next()){
							
							TLineaProducto tIngredientes = new TLineaProducto(rsTienen.getInt("idIngrediente"), rsTienen.getInt("cantidad"), rsComida.getBoolean("tieneGluten"));
							aux.add(tIngredientes);
							
						}						
						
						// @autoformatter: off
						tProducto = new TComida(rs.getInt("id"), 
								rs.getInt("precio"),
								rs.getString("nombre"),
								rs.getInt("idChef"),
								aux, 
								rs.getBoolean("activo"));
						// @autoformatter: on
					}
					

					ps.close();
				}
				else if (rs.getString("tipoProducto").equals("bebida")) {
					ps = connection.prepareStatement("SELECT * FROM restaurante.bebida WHERE id = ? AND activo = true",
							Statement.RETURN_GENERATED_KEYS);
					ps.setInt(1, rs.getInt("id"));
					ResultSet rsBebida = ps.executeQuery();
					
					if (rsBebida.next()) {
						

						// @autoformatter: off
						tProducto = new TBebida(rs.getInt("id"), 
								rs.getInt("precio"),
								rs.getString("nombre"),
								rs.getInt("idChef"),
								rsBebida.getString("marca"),
								rs.getBoolean("activo"));
						// @autoformatter: on
					}
					
				}
				else{
					throw new RuntimeException("Tipo de producto no encontrado");
				}
				
				
				
				list.add(tProducto);
			
			}
			ps.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return new ArrayList<TProducto>();
		}
		return list;
		// end-user-code
	}

	/** 
	* (non-Javadoc)
	* @see DAOProducto#prepararPlato()
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/

}
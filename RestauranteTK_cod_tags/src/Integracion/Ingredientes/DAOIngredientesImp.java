/**
 * 
 */
package Integracion.Ingredientes;

import Negocio.Ingredientes.TIngredientes;

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
public class DAOIngredientesImp implements DAOIngredientes {
	public static final String URL = "jdbc:mysql://tk-is2.mysql.database.azure.com";
	public static final String USER = "restaurante_admin";
	public static final String PASSWORD = "NfBff6ZrLsxgHYP";

	/** 
	* (non-Javadoc)
	* @see DAOIngredientes#create(TIngredientes i)
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public int create(TIngredientes i) {
		// begin-user-code
		Connection connection = null;
		int id = -1;
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement ps = connection.prepareStatement(
					"INSERT INTO restaurante.ingredientes (id, tieneGluten, nombre, cantidad, idProveedor ,activo) VALUES(?, ?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, i.getID());
			ps.setBoolean(2, i.getGluten());
			ps.setString(3, i.getNombre());
			ps.setInt(4, i.getCantidad());
			ps.setInt(5, i.getIdProveedor());
			ps.setBoolean(6, i.getActivo());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getInt(1);
			}
			ps.close();
			connection.close();
		} catch (SQLException e) {
			return -1;
		}
		return id;
		// end-user-code
	}

	/** 
	* (non-Javadoc)
	* @see DAOIngredientes#delete(int IDIngrediente)
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public int delete(int IDIngrediente) {

		Connection connection = null;

		try {

			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement ps = connection.prepareStatement(
					"UPDATE restaurante.ingredientes SET activo = false WHERE id = ? AND activo = true",
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, IDIngrediente);
			ps.executeUpdate();
			ps.close();
			connection.close();
		} catch (SQLException e) {
			System.out.print("-1");
			return -1;
		}

		return IDIngrediente;
		// end-user-code
	}

	/** 
	* (non-Javadoc)
	* @see DAOIngredientes#read(int IDIngrediente)
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public TIngredientes read(int IDIngrediente) {
		// begin-user-code
		TIngredientes tIngredientes = null;
		Connection connection = null;

		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement ps = connection.prepareStatement(
					"SELECT * FROM restaurante.ingredientes WHERE id = ? AND activo = true",
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, IDIngrediente);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				tIngredientes = new TIngredientes(IDIngrediente, rs.getBoolean("tieneGluten"), rs.getString("nombre"),
						rs.getInt("cantidad"), rs.getInt("idProveedor"), rs.getBoolean("activo"));
			}
			ps.close();
			connection.close();
		} catch (SQLException e) {
			return null;
		}
		return tIngredientes;
		// end-user-code
	}


	public Collection<TIngredientes> readAll() {
		// begin-user-code
		Collection<TIngredientes> list = new ArrayList<TIngredientes>();
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement ps = connection.prepareStatement(
					"SELECT * FROM restaurante.ingredientes WHERE activo = true",
					Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				TIngredientes tIngrediente = new TIngredientes(rs.getInt("id"), rs.getBoolean("tieneGluten"),
						rs.getString("nombre"), rs.getInt("cantidad"), rs.getInt("idProveedor"),
						rs.getBoolean("activo"));
				list.add(tIngrediente);
			}
			ps.close();
			connection.close();
		} catch (SQLException e) {
			return new ArrayList<TIngredientes>();
		}
		return list;
		// end-user-code
	}

	/** 
	* (non-Javadoc)
	* @see DAOIngredientes#update(TIngredientes i)
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public int update(TIngredientes i) {
		// begin-user-code
		int id = -1;
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement ps = connection.prepareStatement(
					"UPDATE restaurante.ingredientes SET tieneGluten = ?, nombre = ?, cantidad = ?, idProveedor = ?, activo = ? WHERE id = ?",
					Statement.RETURN_GENERATED_KEYS);
			ps.setBoolean(1, i.getGluten());
			ps.setString(2, i.getNombre());
			ps.setInt(3, i.getCantidad());
			ps.setInt(4, i.getIdProveedor());
			ps.setBoolean(5, true);
			id = i.getID();
			ps.setInt(6, id);
			ps.executeUpdate();
			ps.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());//no hace uptade por una excepcion de la base de datos
			return -1;
		}
		return id;
		// end-user-code
	}

	/** 
	* (non-Javadoc)
	* @see DAOIngredientes#readByProducto()
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public Collection<TIngredientes> readByProducto(int IdProducto) {
		// begin-user-code
		Connection connection = null;
		Collection<TIngredientes> list = new ArrayList<>();
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement ps = connection.prepareStatement(
					"SELECT * FROM restaurante.ingredientes WHERE IdProducto = ?",
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, IdProducto);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				TIngredientes tIngrediente = new TIngredientes(rs.getInt("id"), rs.getBoolean("tieneGluten"),
						rs.getString("nombre"), rs.getInt("cantidad"), rs.getInt("idProveedor"),
						rs.getBoolean("activo"));
				list.add(tIngrediente);
			}
			ps.close();
			connection.close();
		} catch (SQLException e) {
			return new ArrayList<>();
		}
		return list;
		// end-user-code
	}

	/** 
	* (non-Javadoc)
	* @see DAOIngredientes#readByProveedor()
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public Collection<TIngredientes> readByProveedor(int IdProveedor) {
		// begin-user-code
		Connection connection = null;
		Collection<TIngredientes> list = new ArrayList<>();
		try {

			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement ps = connection.prepareStatement(
					"SELECT * FROM restaurante.ingredientes WHERE IdProveedor = ?",
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, IdProveedor);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				TIngredientes tIngrediente = new TIngredientes(rs.getInt("id"), rs.getBoolean("tieneGluten"),
						rs.getString("nombre"), rs.getInt("cantidad"), rs.getInt("idProveedor"),
						rs.getBoolean("activo"));
				list.add(tIngrediente);

			}

			ps.close();
			connection.close();
		} catch (SQLException e) {
			return new ArrayList<>();
		}
		return list;
		// end-user-code
	}

}
/**
 * 
 */
package Integracion.Proveedores;

import Negocio.Proveedores.TProveedores;

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
public class DAOProveedoresImp implements DAOProveedores {
	public static final String URL = "jdbc:mysql://tk-is2.mysql.database.azure.com";
	public static final String USER = "restaurante_admin";
	public static final String PASSWORD = "NfBff6ZrLsxgHYP";

	/** 
	* (non-Javadoc)
	* @see DAOProveedores#create(TProveedores p)
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	@Override
	public int create(TProveedores p) {
		// begin-user-code
		Connection connection = null;
		int id = -1;
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement ps = connection.prepareStatement(
					"INSERT INTO restaurante.proveedores (id, nombre, NIF, activo) VALUES(?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, p.getID());
			ps.setString(2, p.getNombre());
			ps.setString(3, p.getNIF());
			ps.setBoolean(4, p.getActivo());
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
	* @see DAOProveedores#read(int IDProveedor)
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	@Override
	public TProveedores read(int IDProveedor) {
		// begin-user-code
		TProveedores tProveedor = null;
		Connection connection = null;

		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement ps = connection.prepareStatement(
					"SELECT * FROM restaurante.proveedores WHERE id = ? AND activo = true",
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, IDProveedor);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				tProveedor = new TProveedores(rs.getInt("id"),
						rs.getString("nombre"),
						rs.getString("NIF"),
						rs.getBoolean("activo"));
			}
			ps.close();
			connection.close();
		} catch (SQLException e) {
			return null;
		}
		return tProveedor;
		// end-user-code
	}

	/** 
	* (non-Javadoc)
	 * @return 
	* @see DAOProveedores#delete(int IDProveedor)
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	@Override
	public int delete(int IDProveedor) {
		// begin-user-code
		Connection connection = null;

		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement ps = connection.prepareStatement(
					"UPDATE restaurante.proveedores SET activo = false WHERE id = ? AND activo = true",
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, IDProveedor);
			ps.executeUpdate();
			ps.close();
			connection.close();
		} catch (SQLException e) {
			return -1;
		}

		return IDProveedor;
		// end-user-code
	}

	/** 
	* (non-Javadoc)
	* @see DAOProveedores#readAll()
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	@Override
	public Collection<TProveedores> readAll() {
		// begin-user-code
		Collection<TProveedores> list = new ArrayList<TProveedores>();
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement ps = connection.prepareStatement(
					"SELECT * FROM restaurante.proveedores WHERE activo = true",
					Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				TProveedores tProveedor = new TProveedores(rs.getInt("id"), rs.getString("nombre"), rs.getString("NIF"),
						rs.getBoolean("activo"));
				list.add(tProveedor);
			}
			ps.close();
			connection.close();
		} catch (SQLException e) {
			return new ArrayList<TProveedores>();
		}
		return list;
		// end-user-code
	}

	/** 
	* (non-Javadoc)
	 * @return 
	* @see DAOProveedores#update(TProveedores p)
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	@Override
	public int update(TProveedores p) {
		// begin-user-code
		int id = -1;
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement ps = connection.prepareStatement(
					"UPDATE restaurante.proveedores SET nombre = ?, NIF = ?, activo = ? WHERE id = ?",
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, p.getNombre());
			ps.setString(2, p.getNIF());
			ps.setBoolean(3, true);
			id = p.getID();
			ps.setInt(4, id);
			ps.executeUpdate();
			ps.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return -1;
		}
		return id;
		// end-user-code
	}

	@Override
	public TProveedores readByNIF(String nifProveedor) {
		TProveedores tProveedor = null;
		Connection connection = null;

		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement ps = connection.prepareStatement(
					"SELECT * FROM restaurante.proveedores WHERE nif = ? AND activo = true",
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, nifProveedor);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				tProveedor = new TProveedores(rs.getInt("id"),
						rs.getString("nombre"),
						rs.getString("NIF"),
						rs.getBoolean("activo"));
			}
			ps.close();
			connection.close();
		} catch (SQLException e) {
			return null;
		}
		return tProveedor;
	}
	
	public int deleteFisico(int idProveedor){
		Connection connection = null;
		int id = -1;
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement ps = connection.prepareStatement("DELETE FROM restaurante.proveedores WHERE id = ?",
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, idProveedor);
			ps.executeUpdate();
			ps.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return -1;
		}
		return id;
	}
}
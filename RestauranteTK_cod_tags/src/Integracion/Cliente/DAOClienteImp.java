/**
 * 
 */
package Integracion.Cliente;

import Negocio.Cliente.TCliente;

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
public class DAOClienteImp implements DAOCliente {
	public static final String URL = "jdbc:mysql://tk-is2.mysql.database.azure.com";
	public static final String USER = "restaurante_admin";
	public static final String PASSWORD = "NfBff6ZrLsxgHYP";

	/** 
	* (non-Javadoc)
	* @see DAOCliente#create(TCliente c)
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public int create(TCliente c) {
		// begin-user-code
		Connection connection = null;
		int id = -1;
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement ps = connection.prepareStatement(
					"INSERT INTO restaurante.cliente (id, nombre, dni, activo) VALUES(?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, c.getId());
			ps.setString(2, c.getNombre());
			ps.setString(3, c.getDNI());
			ps.setBoolean(4, true);
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getInt(1);
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
	* @see DAOCliente#delete(int idCliente)
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public int delete(int idCliente) {
		// begin-user-code
		Connection connection = null;

		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement ps = connection.prepareStatement(
					"UPDATE restaurante.cliente SET activo = false WHERE id = ? AND activo = true",
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, idCliente);
			ps.executeUpdate();
			ps.close();
			connection.close();
		} catch (SQLException e) {
			return -1;
		}

		return idCliente;
		// end-user-code
	}

	/** 
	* (non-Javadoc)
	* @see DAOCliente#read(int idCliente)
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public TCliente read(int idCliente) {
		// begin-user-code
		TCliente tCliente = null;
		Connection connection = null;

		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement ps = connection.prepareStatement(
					"SELECT * FROM restaurante.cliente WHERE id = ?",
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, idCliente);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				tCliente = new TCliente(idCliente,
						rs.getString("nombre"),
						rs.getString("dni"),
						rs.getBoolean("activo"));
			}
			ps.close();
			connection.close();
		} catch (SQLException e) {
			return null;
		}
		return tCliente;
		// end-user-code
	}

	/** 
	* (non-Javadoc)
	* @see DAOCliente#update(TCliente c)
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public int update(TCliente c) {
		// begin-user-code
		int id = -1;
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement ps = connection.prepareStatement(
					"UPDATE restaurante.cliente SET nombre = ?, dni = ?, activo = ? WHERE id = ?",
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, c.getNombre());
			ps.setString(2, c.getDNI());
			ps.setBoolean(3, true);
			ps.setInt(4, c.getId());
			id = c.getId();
			ps.executeUpdate();
			// ResultSet rs = ps.executeQuery();

			ps.close();
			connection.close();

		} catch (SQLException e) {
			System.out.println(e);
			return -1;
		}
		return id;
		// end-user-code
	}

	/** 
	* (non-Javadoc)
	* @see DAOCliente#readAll()
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public Collection<TCliente> readAll() {
		// begin-user-code
		Collection<TCliente> list = new ArrayList<>();
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement ps = connection.prepareStatement(
					"SELECT * FROM restaurante.cliente WHERE activo = true",
					Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				TCliente tCliente = new TCliente(rs.getInt("id"), rs.getString("nombre"), rs.getString("DNI"),
						rs.getBoolean("activo"));
				list.add(tCliente);
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
	* @see DAOCliente#readByDni(String dniCliente)
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public TCliente readByDni(String dniCliente) {
		// begin-user-code
		Connection connection = null;
		TCliente tCliente = null;
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement ps = connection.prepareStatement(
					"SELECT * FROM restaurante.Cliente WHERE dni = ?",
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, dniCliente);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				tCliente = new TCliente(rs.getInt("id"),
						rs.getString("nombre"),
						rs.getString("dni"),
						rs.getBoolean("activo"));
			}
			ps.close();
			connection.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return tCliente;
		// end-user-code
	}

	@Override
	public int deleteFisico(int idCliente) {
		Connection connection = null;
		int id = -1;
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement ps = connection.prepareStatement("DELETE FROM restaurante.cliente WHERE id = ?",
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, idCliente);
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
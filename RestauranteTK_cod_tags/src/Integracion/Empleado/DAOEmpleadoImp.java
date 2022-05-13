/**
 * 
 */
package Integracion.Empleado;

import Negocio.Empleado.TCamarero;
import Negocio.Empleado.TChef;
import Negocio.Empleado.TEmpleado;

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
public class DAOEmpleadoImp implements DAOEmpleado {
	public static final String URL = "jdbc:mysql://tk-is2.mysql.database.azure.com";
	public static final String USER = "restaurante_admin";
	public static final String PASSWORD = "NfBff6ZrLsxgHYP";

	/** 
	* (non-Javadoc)
	* @see DAOEmpleado#create(TEmpleado e)
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public int create(TEmpleado e) {
		// begin-user-code
		Connection connection = null;
		int id = -1;
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement ps = connection.prepareStatement(
					"INSERT INTO restaurante.empleado (id, tipoEmpleado, nombre, dni, salario, activo) VALUES(?, ?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, e.getId());
			ps.setString(2, e.getTipoEmpleado());
			ps.setString(3, e.getNombre());
			ps.setString(4, e.getDNI());
			ps.setInt(5, e.getSalario());
			ps.setBoolean(6, true);
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();

			if (rs.next()) {
				id = rs.getInt(1);
			}
			ps.close();
			if (e.getTipoEmpleado().equals("chef")) {
				TChef tchef = (TChef) e;
				ps = connection.prepareStatement(
						"INSERT INTO restaurante.chef (id, activo, estrellas) VALUES (? ,?, ?)",
						Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, id);
				ps.setBoolean(2, true);
				ps.setInt(3, tchef.getEstrellas());
				ps.executeUpdate();
				ps.close();
			} else if (e.getTipoEmpleado().equals("camarero")) {
				TCamarero tCamarero = (TCamarero) e;
				ps = connection.prepareStatement(
						"INSERT INTO restaurante.camarero (id, activo, idiomas) VALUES (?, ?, ?)",
						Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, id);
				ps.setBoolean(2, true);
				ps.setString(3, tCamarero.getIdiomas());
				ps.executeUpdate();
				ps.close();

			}
			connection.close();
		} catch (SQLException exception) {
			System.out.println(exception.getMessage());
			return -1;
		}
		return id;
		// end-user-code
	}

	/** 
	* (non-Javadoc)
	* @see DAOEmpleado#delete(int idEmpleado)
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public int delete(int idEmpleado) {
		// begin-user-code
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement ps = connection.prepareStatement(
					"SELECT tipoEmpleado FROM restaurante.empleado WHERE id = ?",
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, idEmpleado);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				if (rs.getString("tipoEmpleado").equals("chef")) {
					ps = connection.prepareStatement(
							"UPDATE restaurante.chef SET activo = false WHERE id = ?",
							Statement.RETURN_GENERATED_KEYS);
					ps.setInt(1, idEmpleado);
					ps.executeUpdate();
					ps.close();

				} else if (rs.getString("tipoEmpleado").equals("camarero")) {
					ps = connection.prepareStatement(
							"UPDATE restaurante.camarero SET activo = false WHERE id = ?",
							Statement.RETURN_GENERATED_KEYS);
					ps.setInt(1, idEmpleado);
					ps.executeUpdate();
					ps.close();

				}
				ps = connection.prepareStatement(
						"UPDATE restaurante.empleado SET activo = false WHERE id = ?",
						Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, idEmpleado);
				ps.executeUpdate();
				ps.close();

			}
		} catch (SQLException exception) {
			System.out.println(exception.getMessage());
			return -1;
		}

		// try {
		// 	connection = DriverManager.getConnection(URL, USER, PASSWORD);
		// 	PreparedStatement ps = connection.prepareStatement(
		// 			"UPDATE restaurante.empleado SET activo = false WHERE id = ?",
		// 			Statement.RETURN_GENERATED_KEYS);
		// 	ps.setInt(1, idEmpleado);
		// 	ps.executeUpdate();
		// 	ps.close();
		// 	connection.close();
		// } catch (SQLException e) {
		// 	System.out.println(e.getMessage());
		// 	return -1;
		// }

		return idEmpleado;
		// end-user-code
	}

	/** 
	* (non-Javadoc)
	* @see DAOEmpleado#read(int idEmpleado)
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public TEmpleado read(int idEmpleado) {
		// begin-user-code
		TEmpleado tEmpleado = null;
		Connection connection = null;

		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement ps = connection.prepareStatement(
					"SELECT * FROM restaurante.empleado WHERE id = ? AND activo = true",
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, idEmpleado);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				if (rs.getString("tipoEmpleado").equals("chef")) {
					ps = connection.prepareStatement("SELECT * FROM restaurante.chef WHERE id = ? AND activo = true",
							Statement.RETURN_GENERATED_KEYS);
					ps.setInt(1, idEmpleado);
					ResultSet rsChef = ps.executeQuery();

					if (rsChef.next()) {
						// @autoformatter: off
						tEmpleado = new TChef(idEmpleado, rs.getString("nombre"),
								rs.getString("dni"),
								rs.getInt("salario"),
								rsChef.getInt("estrellas"),
								rs.getBoolean("activo"));
						// @autoformatter: on
					}

					ps.close();
				}

				else if (rs.getString("tipoEmpleado").equals("camarero")) {
					ps = connection.prepareStatement(
							"SELECT * FROM restaurante.camarero WHERE id = ? AND activo = true",
							Statement.RETURN_GENERATED_KEYS);
					ps.setInt(1, idEmpleado);
					ResultSet rsCamarero = ps.executeQuery();

					if (rsCamarero.next()) {
						// @autoformatter:re off
						tEmpleado = new TCamarero(idEmpleado, rs.getString("nombre"),
								rs.getString("dni"),
								rsCamarero.getString("idiomas"),
								rs.getInt("salario"),
								rsCamarero.getBoolean("activo"));
						// @autorformatter: on
					}

					ps.close();
				}
			}
			ps.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}

		return tEmpleado;

		// end-user-code
	}

	/** 
	* (non-Javadoc)
	 * @return 
	* @see DAOEmpleado#update(TEmpleado e)
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public int update(TEmpleado e) {
		// begin-user-code
		int id = -1;
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			String tipo = "";
			PreparedStatement check = connection.prepareStatement(
					"SELECT tipoEmpleado FROM restaurante.empleado WHERE id = ?",
					Statement.RETURN_GENERATED_KEYS);
			check.setInt(1, e.getId());
			ResultSet checkRs = check.executeQuery();
			if (checkRs.next()) {
				tipo = checkRs.getString("tipoEmpleado");
			}
			check.close();
			if (tipo == "")
				throw new IllegalArgumentException("No existe ningun producto con este id");
			if (!tipo.equalsIgnoreCase(e.getTipoEmpleado()))
				throw new IllegalArgumentException("No se puede cambiar el tipo de empleado");

			// restaurante.empleado (id, tipoEmpleado, nombre, dni, salario, activo)
			PreparedStatement ps = connection.prepareStatement(
					"UPDATE restaurante.empleado SET nombre = ?, dni = ?, salario = ?, activo = ? WHERE id = ?",
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, e.getNombre());
			ps.setString(2, e.getDNI());
			ps.setInt(3, e.getSalario());
			ps.setBoolean(4, true);
			ps.setInt(5, e.getId());
			ps.executeUpdate();
			ps.close();
			if (e.getTipoEmpleado().equals("chef")) {
				TChef chef = (TChef) e;
				ps = connection.prepareStatement(
						"UPDATE restaurante.chef SET estrellas = ?, activo = ? WHERE id = ?",
						Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, chef.getEstrellas());
				ps.setBoolean(2, true);
				ps.setInt(3, chef.getId());
				ps.executeUpdate();
				id = chef.getId();
				ps.close();
			} else if (e.getTipoEmpleado().equals("camarero")) {
				TCamarero camarero = (TCamarero) e;
				ps = connection.prepareStatement("UPDATE restaurante.camarero SET idiomas = ?, activo = ? WHERE id = ?",
						Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, camarero.getIdiomas());
				ps.setBoolean(2, true);
				ps.setInt(3, camarero.getId());
				ps.executeUpdate();
				id = camarero.getId();
				ps.close();
			}

		} catch (SQLException exception) {
			System.out.println(exception.getMessage());
			return -1;
		}
		return id;
		// end-user-code
	}

	/** 
	* (non-Javadoc)
	* @see DAOEmpleado#readAll()
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public Collection<TEmpleado> readAll() {
		// begin-user-code
		Collection<TEmpleado> listaEmpleados = new ArrayList<>();

		Connection connection = null;
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement ps = connection.prepareStatement(
					"SELECT * FROM restaurante.empleado WHERE activo = true",
					Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = ps.executeQuery();
			// ps.close();
			while (rs.next()) {
				String tipoEmp = rs.getString("tipoEmpleado");

				if (tipoEmp.equals("chef")) {
					ps = connection.prepareStatement("SELECT * FROM restaurante.chef WHERE id = ?",
							Statement.RETURN_GENERATED_KEYS);
					ps.setInt(1, rs.getInt("id"));

					ResultSet rsChef = ps.executeQuery();
					// ps.close();
					while (rsChef.next()) {
						// @autoformatter: off
						TChef tchef = new TChef(rs.getInt("id"),
								rs.getString("nombre"),
								rs.getString("dni"),
								rs.getInt("salario"),
								rsChef.getInt("estrellas"),
								rs.getBoolean("activo"));
						// @autoformatter: on
						// ps.close();
						listaEmpleados.add(tchef);
					}
					ps.close();

				} else if (tipoEmp.equals("camarero")) {
					ps = connection.prepareStatement("SELECT * FROM restaurante.camarero WHERE id = ?",
							Statement.RETURN_GENERATED_KEYS);
					ps.setInt(1, rs.getInt("id"));

					ResultSet rsCamarero = ps.executeQuery();
					// ps.close();
					while (rsCamarero.next()) {
						// @autoformatter: off
						TCamarero tCamarero = new TCamarero(rs.getInt("id"),
								rs.getString("nombre"),
								rs.getString("dni"),
								rsCamarero.getString("idiomas"),
								rs.getInt("salario"),
								rs.getBoolean("activo"));
						// ps.close();
						listaEmpleados.add(tCamarero);
						// @autoformatter: on
					}
					ps.close();
				}
			}
			ps.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return new ArrayList<TEmpleado>();
		}
		return listaEmpleados;
		// end-user-code
	}

	@Override
	public int deleteFisico(int idEmpleado) {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement ps = null;

			ps = connection.prepareStatement("DELETE FROM restaurante.camarero WHERE id = ?",
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, idEmpleado);
			ps.executeUpdate();
			ps.close();

			ps = connection.prepareStatement("DELETE FROM restaurante.chef WHERE id = ?",
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, idEmpleado);
			ps.executeUpdate();
			ps.close();

			ps = connection.prepareStatement(
					"DELETE FROM restaurante.empleado WHERE id = ?",
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, idEmpleado);
			ps.executeUpdate();
			ps.close();

			connection.close();

		} catch (SQLException exception) {
			System.out.println(exception.getMessage());
			return -1;
		}
		return idEmpleado;
	}
}
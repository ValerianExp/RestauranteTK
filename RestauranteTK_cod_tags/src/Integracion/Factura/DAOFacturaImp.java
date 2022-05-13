/**
 * 
 */
package Integracion.Factura;

import Negocio.Factura.TFactura;
import Negocio.Ingredientes.TIngredientes;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Set;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;


public class DAOFacturaImp implements DAOFactura {
	public static final String URL = "jdbc:mysql://tk-is2.mysql.database.azure.com";
	public static final String USER = "restaurante_admin";
	public static final String PASSWORD = "NfBff6ZrLsxgHYP";


	@Override
	public int create(TFactura tFactura) {
		Connection connection = null;
		int id = -1;
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement ps = connection.prepareStatement(
					"INSERT INTO restaurante.factura (idFactura, fecha, pago, IDCamarero, IDCliente) VALUES(?, ?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, tFactura.getID());
			ps.setDate(2, tFactura.getFecha());
			ps.setString(3, tFactura.getPago().toString());
			ps.setInt(4, tFactura.getIDCamarero());
			ps.setInt(5, tFactura.getIDCliente());
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
	}

	@Override
	public int delete(int idFactura) {
		 Connection connection = null;

		 try {
		 	connection = DriverManager.getConnection(URL, USER, PASSWORD);
		 	PreparedStatement ps = connection.prepareStatement(
		 			"UPDATE restaurante.factura SET activo = false WHERE idFactura = ? AND activo = true",
		 			Statement.RETURN_GENERATED_KEYS);
		 	ps.setInt(1, idFactura);
		 	ps.executeUpdate();
		 	ps.close();
		 	connection.close();
		 } catch (SQLException e) {
		 	return -1;
		 }

		return idFactura;
		
	}

	@Override
	public TFactura read(int idFactura) {
		TFactura tFactura = null;
		Connection connection = null;

		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement ps = connection.prepareStatement(
					"SELECT * FROM restaurante.factura WHERE idFactura = ? AND activo = true",
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, idFactura);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				tFactura = new TFactura(idFactura, rs.getDate("fecha"), rs.getString("pago"), rs.getInt("importe"),
						rs.getInt("IDCamarero"), rs.getInt("IDCliente"));
			}
			ps.close();
			connection.close();
		} catch (SQLException e) {
			return null;
		}
		return tFactura;
	}

	@Override
	public Collection<TFactura> readAll() {
		
		Collection<TFactura> list = new ArrayList<TFactura>();
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement ps = connection.prepareStatement(
					"SELECT * FROM restaurante.factura WHERE activo = true",
					Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				TFactura tFactura = new TFactura(rs.getInt("idFactura"), rs.getDate("fecha"), rs.getString("pago"), rs.getInt("importe"),
						rs.getInt("IDCamarero"), rs.getInt("IDCliente"));
				list.add(tFactura);
			}
			ps.close();
			connection.close();
		} catch (SQLException e) {
			return new ArrayList<>();
		}
		return list;
	}

	@Override
	public int update(TFactura tFactura) {
		int id = -1;
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement ps = connection.prepareStatement(
					"UPDATE restaurante.factura SET fecha = ?, pago = ?, idCamarero = ?, idCliente = ?, activo = ? WHERE idFactura = ?",
					Statement.RETURN_GENERATED_KEYS);
			ps.setDate(1, tFactura.getFecha());
			ps.setString(2, tFactura.getPago().toString());
			ps.setInt(3, tFactura.getIDCamarero());
			ps.setInt(4, tFactura.getIDCliente());
			ps.setBoolean(5, true);
			ps.setInt(6, tFactura.getID());
			ps.executeUpdate();
			id=tFactura.getID();
			ps.close();
			connection.close();
		} catch (SQLException e) {
			return -1;
		}
		return id;
	}

	@Override
	public Collection<TFactura> readByCliente(int idCliente) {
		Connection connection = null;
		Collection<TFactura> list = new ArrayList<>();
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement ps = connection.prepareStatement(
					"SELECT * FROM restaurante.factura WHERE idCliente = ?",
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, idCliente);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				TFactura tFactura = new TFactura(rs.getInt("idFactura"), rs.getDate("fecha"), rs.getString("pago"), rs.getInt("importe"),
						rs.getInt("IDCamarero"), rs.getInt("IDCliente"));
				list.add(tFactura);
			}
			ps.close();
			connection.close();
		} catch (SQLException e) {
			return new ArrayList<>();
		}
		return list;
	}
}
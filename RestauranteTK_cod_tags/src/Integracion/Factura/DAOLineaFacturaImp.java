package Integracion.Factura;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import Negocio.Cliente.TCliente;
import Negocio.Factura.TCarrito;
import Negocio.Factura.TLineaFactura;
import Negocio.Proveedores.TProveedores;

public class DAOLineaFacturaImp implements DAOLineaFactura{
	
	public static final String URL = "jdbc:mysql://tk-is2.mysql.database.azure.com";
	public static final String USER = "restaurante_admin";
	public static final String PASSWORD = "NfBff6ZrLsxgHYP";


	@Override
	public int create(TLineaFactura f, int idFactura) {
		Connection connection = null;
		int id = -1;
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement ps = connection.prepareStatement(
					"INSERT INTO restaurante.lineafactura (idFactura,idProducto,cantidad,precio) VALUES(?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, idFactura);
			ps.setInt(2, f.getIdProducto());
			ps.setInt(3, f.getCantidad());
			ps.setInt(4, f.getPrecio());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getInt("idFactura");
			}
			ps.close();
			connection.close();
			
		} catch (SQLException e) {
			return -1;
		}
		return id;		
	}

	@Override
	public int delete(int idFactura,int idProducto) {
		 Connection connection = null;

		 try {
		 	connection = DriverManager.getConnection(URL, USER, PASSWORD);
		 	PreparedStatement ps = connection.prepareStatement(
		 			"UPDATE restaurante.lineafactura SET activo = false WHERE idProducto = ? AND idFactura = ? AND activo = true",
		 			Statement.RETURN_GENERATED_KEYS);
		 	ps.setInt(1, idProducto);
		 	ps.setInt(2, idFactura);
		 	ps.executeUpdate();
		 	ps.close();
		 	connection.close();
		 	
		 } catch (SQLException e) {
		 	return -1;
		 }

		 return idProducto;
		
	}
	
	@Override
	public int deleteByFactura(int idFactura) {
		 Connection connection = null;

		 try {
		 	connection = DriverManager.getConnection(URL, USER, PASSWORD);
		 	PreparedStatement ps = connection.prepareStatement(
		 			"UPDATE restaurante.lineafactura SET activo = false WHERE idFactura = ? AND activo = true",
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
	public TLineaFactura read(int idFactura, int idProducto) {
		// begin-user-code
				TLineaFactura tLineaFactura = null;
				Connection connection = null;

				try {
					connection = DriverManager.getConnection(URL, USER, PASSWORD);
					PreparedStatement ps = connection.prepareStatement(
							"SELECT * FROM restaurante.lineafactura WHERE idFactura = ? AND idProducto = ?",
							Statement.RETURN_GENERATED_KEYS);
					ps.setInt(1, idFactura);
					ps.setInt(2, idProducto);
					ResultSet rs = ps.executeQuery();
					while (rs.next()) {
						tLineaFactura = new TLineaFactura(new TCarrito(rs.getInt("idProducto"), rs.getInt("cantidad")), rs.getInt("precio"));
					}
					ps.close();
					connection.close();
				} catch (SQLException e) {
					return null;
				}
				return tLineaFactura;		
	}

	@Override
	public int update(TLineaFactura f) {
			int idProducto = -1;
			int idFactura=-1;
			Connection connection = null;
			try {
				idProducto = f.getIdProducto();
				idFactura= f.getIdFactura();
				connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = connection.prepareStatement(
						"UPDATE restaurante.lineafactura SET idFactura = ?, idProducto = ?, cantidad = ?,precio=?,activo=?  WHERE idProducto = ? and idFactura=?",
						Statement.RETURN_GENERATED_KEYS);
				
				ps.setInt(1, idFactura);
				ps.setInt(2, idProducto);
				ps.setInt(3, f.getCantidad());
				ps.setInt(4, f.getPrecio());
				ps.setBoolean(5, f.getActivo());
				ps.setInt(6, idProducto);
				ps.setInt(7, idFactura);
				ps.executeUpdate();
				ps.close();
				connection.close();
			} catch (SQLException e) {
				return -1;
			}
			return idFactura;	
	}


	@Override
	public Collection<TLineaFactura> readByFactura(int idFactura) {
		// begin-user-code
		Collection<TLineaFactura> list = new ArrayList<>();
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement ps = connection.prepareStatement(
					"SELECT * FROM restaurante.lineafactura WHERE idFactura= ? AND activo = true",
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, idFactura);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				TLineaFactura tLineaFactura = new TLineaFactura(new TCarrito(rs.getInt("idProducto"), rs.getInt("cantidad")), rs.getInt("precio"));
				list.add(tLineaFactura);
			}
			ps.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
		return list;
		// end-user-code
		
	}





}

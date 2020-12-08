package proyecto.dao;

import java.io.IOException;
import java.sql.Connection;		
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.jdbc.Blob;

import proyecto.model.ClavesProd;
import proyecto.model.Producto;

public class ProdDAO extends HttpServlet {
	private static String jdbcURL = "jdbc:mysql://localhost:3306/cadenasupermercados?serverTimezone=UTC";
	private static String jdbcUsername = "java";
	private static String jdbcPassword = "1234";

	private static final String INSERT_PRODUCT_SQL = "INSERT INTO productos" + "  (producto, precio) VALUES "
			+ " (?, ?);";

	private static final String SELECT_PRODUCT_BY_ID = "select id,producto,precio from productos where id = ?";
	private static final String SELECT_ALL_PRODUCT = "select * from productos";
	private static final String DELETE_PRODUCT_SQL = "delete from productos where id = ?;";
	private static final String UPDATE_PRODUCT_SQL = "update productos set producto = ?, precio = ? where id = ?;";
	private static double precioFinal = 0;


	public ProdDAO() {
		
	}

	protected static Connection getConnection() {
		Connection connection = null;
		try { 
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	public void insertProd(Producto Producto) throws SQLException {
		System.out.println(INSERT_PRODUCT_SQL);

		try (Connection connection = getConnection();
				PreparedStatement ps1 = connection.prepareStatement(INSERT_PRODUCT_SQL)) {
			ps1.setString(1, Producto.getProducto());
			ps1.setDouble(2, Producto.getPrecio());
			System.out.println(ps1.toString());
			ps1.executeUpdate();
			

			
		} catch (SQLException e) {
			printSQLException(e);
		}
		

	}

	public Producto selectProd(int id) {
		Producto Producto = null;
		// Establecemos la conexión
		try (Connection connection = getConnection();
				// Creamos la consulta
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Ejecutamos la consulta
			ResultSet rs = preparedStatement.executeQuery();

			
			while (rs.next()) {
				int id2 = rs.getInt(ClavesProd.ID.toString());
				String producto = rs.getString(ClavesProd.PRODUCT.toString());
				double precio = rs.getDouble(ClavesProd.PRECIO.toString());
				Producto = new Producto(id2, producto, precio);
				
			
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return Producto;
	}
	
	public Producto limpiarCesta(HttpServletRequest request, HttpServletResponse response)
		    throws SQLException, IOException, ServletException {
		HttpSession session = request.getSession();
		
		precioFinal = 0;
		
		session.setAttribute("precioFinal", precioFinal);
		
		return null;
		
		
		
	}
	
	public Producto agregarProd(int id, HttpServletRequest request, HttpServletResponse response)
		    throws SQLException, IOException, ServletException {
		HttpSession session = request.getSession();
		
		Producto Producto = null;
		// Establecemos la conexión
		try (Connection connection = getConnection();
				// Creamos la consulta
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Ejecutamos la consulta
			ResultSet rs = preparedStatement.executeQuery();

			
			while (rs.next()) {
				double precio = rs.getDouble(ClavesProd.PRECIO.toString());
				
				precioFinal += precio;
				
				
				session.setAttribute("precioFinal", precioFinal);
				
				
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return Producto;
	}

	public static List<Producto> selectAllProd() {
		List<Producto> productos = new ArrayList<>();
		
		try (Connection connection = getConnection();

		
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCT);) {
			System.out.println(preparedStatement);

			ResultSet rs = preparedStatement.executeQuery();


			while (rs.next()) {
				int id = rs.getInt(ClavesProd.ID.toString());
				String producto = rs.getString(ClavesProd.PRODUCT.toString());
				double precio = rs.getDouble(ClavesProd.PRECIO.toString());
				productos.add(new Producto(id, producto, precio));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return productos;
	}

	public boolean deleteProd(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_PRODUCT_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateProd(Producto user) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUCT_SQL);) {
			statement.setString(1, user.getProducto());
			statement.setDouble(2, user.getPrecio());
			statement.setInt(3, user.getId());
	
			
			System.out.println(statement);
			
			

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	private static void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}

}

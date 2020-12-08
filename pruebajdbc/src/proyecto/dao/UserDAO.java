package proyecto.dao;

import java.sql.Connection;		
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import proyecto.model.Claves;
import proyecto.model.Usuario;

public class UserDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/cadenasupermercados?serverTimezone=UTC";
	private String jdbcUsername = "java";
	private String jdbcPassword = "1234";

	private static final String INSERT_USERS_SQL = "INSERT INTO usuarios" + "  (usuario, email, contrasena, admin) VALUES "
			+ " (?, ?, ?, ?);";

	private static final String SELECT_USER_BY_ID = "select id,usuario,contrasena, email, admin from usuarios where id = ?";
	private static final String SELECT_ALL_USERS = "select * from usuarios";
	private static final String DELETE_USERS_SQL = "delete from usuarios where id = ?;";
	private static final String UPDATE_USERS_SQL = "update usuarios set usuario = ?,contrasena = ?, email = ?, admin = ? where id = ?;";


	public UserDAO() {
	}

	protected Connection getConnection() {
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

	public void insertUser(Usuario usuario) throws SQLException {
		System.out.println(INSERT_USERS_SQL);

		try (Connection connection = getConnection();
				PreparedStatement ps1 = connection.prepareStatement(INSERT_USERS_SQL)) {
			ps1.setString(1, usuario.getUsuario());
			ps1.setString(2, usuario.getContrasena());
			ps1.setString(3, usuario.getEmail());
			ps1.setBoolean(4, usuario.getAdmin());
			System.out.println(ps1.toString());
			ps1.executeUpdate();
			

			
		} catch (SQLException e) {
			printSQLException(e);
		}
		

	}

	public Usuario selectUser(int id) {
		Usuario usuario = null;
		// Establecemos la conexión
		try (Connection connection = getConnection();
				// Creamos la consulta
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Ejecutamos la consulta
			ResultSet rs = preparedStatement.executeQuery();

			
			while (rs.next()) {
				int id2 = rs.getInt(Claves.ID.toString());
				String nombre = rs.getString(Claves.USERNAME.toString());
				String contrasena = rs.getString(Claves.CONTRASENA.toString());
				String email = rs.getString(Claves.EMAIL.toString());
				Boolean admin = rs.getBoolean(Claves.ADMIN.toString());
				usuario = new Usuario(id2, nombre, contrasena, email, admin);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return usuario;
	}

	public List<Usuario> selectAllUsers() {


		List<Usuario> usuarios = new ArrayList<>();

		try (Connection connection = getConnection();

		
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
			System.out.println(preparedStatement);

			ResultSet rs = preparedStatement.executeQuery();


			while (rs.next()) {
				int id = rs.getInt(Claves.ID.toString());
				String nombre = rs.getString(Claves.USERNAME.toString());
				String contrasena = rs.getString(Claves.CONTRASENA.toString());
				String email = rs.getString(Claves.EMAIL.toString());
				Boolean admin = rs.getBoolean(Claves.ADMIN.toString());
				usuarios.add(new Usuario(id, nombre, contrasena, email, admin));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return usuarios;
	}

	public boolean deleteUser(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateUser(Usuario user) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {
			statement.setString(1, user.getUsuario());
			statement.setString(2, user.getContrasena());
			statement.setString(3, user.getEmail());
			statement.setBoolean(4, user.getAdmin());
			statement.setInt(5, user.getId());
	
			
			System.out.println(statement);
			
			

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	private void printSQLException(SQLException ex) {
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

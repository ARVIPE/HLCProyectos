package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Equipo;
import model.Claves;


public class EquipDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/futbol?serverTimezone=UTC";
	private String jdbcUsername = "java";
	private String jdbcPassword = "1234";


	private static final String SELECT_ALL_TEAM = "select * from equipo";


	public EquipDAO() {
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

	

	public List<Equipo> selectAllTeam() {


		List<Equipo> equipos = new ArrayList<>();

		try (Connection connection = getConnection();

		
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TEAM);) {
			System.out.println(preparedStatement);

			ResultSet rs = preparedStatement.executeQuery();


			while (rs.next()) {
				int id = rs.getInt(Claves.ID.toString());
				String nombre = rs.getString(Claves.NOMBRE.toString());
				int puntos = rs.getInt(Claves.PUNTOS.toString());
				int v = rs.getInt(Claves.V.toString());
				int e = rs.getInt(Claves.E.toString());
				int d = rs.getInt(Claves.D.toString());
				int gf = rs.getInt(Claves.GF.toString());
				int gc = rs.getInt(Claves.GC.toString());
				
				equipos.add(new Equipo(id, nombre, puntos, v, e, d, gf, gc));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return equipos;
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

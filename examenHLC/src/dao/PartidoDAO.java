/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author arvip
 */
import java.sql.Connection;		
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Claves;
import model.Partido;

public class PartidoDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/futbol?serverTimezone=UTC";
	private String jdbcUsername = "java";
	private String jdbcPassword = "1234";

	private static final String INSERT_MATCH_SQL = "INSERT INTO partido" + "  (e1, e2, g1, g2) VALUES "
			+ " (?, ?, ?, ?);";

	private static final String SELECT_MATCH_BY_ID = "select id,e1,e2, g1, g2 from partido where id = ?";
	private static final String SELECT_ALL_MATCH = "select * from partido";
	private static final String DELETE_MATCH_SQL = "delete from partido where id = ?;";
	private static final String UPDATE_MATCH_SQL = "update partido set e1 = ?,e2 = ?, g1 = ?, g2 = ? where id = ?;";


	public PartidoDAO() {
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

	public void insertMatch(Partido partido) throws SQLException {
		System.out.println(INSERT_MATCH_SQL);

		try (Connection connection = getConnection();
				PreparedStatement ps1 = connection.prepareStatement(INSERT_MATCH_SQL)) {
			ps1.setInt(1, partido.getE1());
			ps1.setInt(2, partido.getE2());
			ps1.setInt(3, partido.getG1());
			ps1.setInt(4, partido.getG2());
			System.out.println(ps1.toString());
			ps1.executeUpdate();
			

			
		} catch (SQLException e) {
			printSQLException(e);
		}
		

	}

	public Partido selectMatch(int id) {
		Partido partido = null;
		// Establecemos la conexi�n
		try (Connection connection = getConnection();
				// Creamos la consulta
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MATCH_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Ejecutamos la consulta
			ResultSet rs = preparedStatement.executeQuery();

			
			while (rs.next()) {
				int id2 = rs.getInt(Claves.ID.toString());
				int e1 = rs.getInt(Claves.E1.toString());
				int e2 = rs.getInt(Claves.E2.toString());
				int g1 = rs.getInt(Claves.G1.toString());
				int g2 = rs.getInt(Claves.G2.toString());
				partido = new Partido(id2, e1, e2, g1, g2);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return partido;
	}

	public List<Partido> selectAllMatch() {


		List<Partido> partidos = new ArrayList<>();

		try (Connection connection = getConnection();

		
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_MATCH);) {
			System.out.println(preparedStatement);

			ResultSet rs = preparedStatement.executeQuery();


			while (rs.next()) {
				int id2 = rs.getInt(Claves.ID.toString());
				int e1 = rs.getInt(Claves.E1.toString());
				int e2 = rs.getInt(Claves.E2.toString());
				int g1 = rs.getInt(Claves.G1.toString());
				int g2 = rs.getInt(Claves.G2.toString());
				partidos.add(new Partido(id2, e1, e2, g1, g2));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return partidos;
	}

	public boolean deleteMatch(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_MATCH_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}
       

	public boolean updateMatch(Partido part) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_MATCH_SQL);) {
			statement.setInt(1, part.getE1());
			statement.setInt(2, part.getE2());
			statement.setInt(3, part.getG1());
			statement.setInt(4, part.getG2());
			statement.setInt(5, part.getId());
	
			
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

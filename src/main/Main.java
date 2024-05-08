package main;

import java.sql.*;

public class Main {
	public static Connection conexao() throws SQLException {
		String connectionString = "jdbc:postgresql://localhost:5432/produtos";
		String user = "postgres";
		String pass = "dbadmin";
		Connection connection = DriverManager.getConnection(connectionString, user, pass);
		
		return connection;
	}
	
	//CREATE
	public static void inserir() throws SQLException {
		String sql = "INSERT INTO produto (nome, valor) VALUES (?, ?)";
		PreparedStatement preparedStatement = conexao().prepareStatement(sql);
		preparedStatement.setString(1, "Mouse");
		preparedStatement.setDouble(2, 100);
		preparedStatement.executeUpdate();
		preparedStatement.close();
	}

	//READ
	public static void consultar() throws SQLException {
		String sql = "SELECT * FROM produto";
		Statement statement = conexao().createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		while (resultSet.next()) {
			System.out.println("Id:" + resultSet.getString("id_produto"));
			System.out.println("Nome:" + resultSet.getString("nome"));
			System.out.println("Valor:" + resultSet.getDouble("valor"));
		}
		conexao().close();
	}
	
	//UPDATE
	public static void atualizar() throws SQLException {
		String sql = "UPDATE produto SET nome = ?, valor = ? WHERE id_produto = ?";
		PreparedStatement preparedStatement = conexao().prepareStatement(sql);
		preparedStatement.setString(1, "Mouse");
		preparedStatement.setDouble(2, 159.9);
		preparedStatement.setInt(3, 1);
		preparedStatement.executeUpdate();
		preparedStatement.close();
	}
	
	//DELETE
	public static void deletar() throws SQLException {
		String sql = "DELETE FROM produto WHERE id_produto = ?";
		PreparedStatement preparedStatement = conexao().prepareStatement(sql);
		preparedStatement.setInt(1, 1);
		preparedStatement.executeUpdate();
		conexao().close();
	}

	public static void main(String[] args) throws SQLException {
		inserir();
		atualizar();
		deletar();
		consultar();
		
	}

}

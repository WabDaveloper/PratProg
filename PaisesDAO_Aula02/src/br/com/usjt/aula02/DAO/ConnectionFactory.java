package br.com.usjt.aula02.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {


		
		static {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				System.out.println("Conected!");
			} catch (ClassNotFoundException e) {
				throw new RuntimeException(e);
			}
		}

		// Obtem conexao com o banco de dados
			public static Connection obtemConexao() throws SQLException {
				return DriverManager.getConnection("jdbc:mysql://localhost/PratProgInt?user=root&password=teste&useSSL=false&useTimezone=true&serverTimezone=UTC");
			}
	}


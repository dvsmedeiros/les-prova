package com.dvsmedeiros.core.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {

		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "les";
		String password = "les";

		Class.forName(driver);
		Connection conn = DriverManager.getConnection(url, user, password);

		return conn;
	}

}
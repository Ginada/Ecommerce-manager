package se.ginaDev.ecommerce.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class SQLConnection {
	private static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/User";

	protected Connection getConnection() throws SQLException
	{
		return DriverManager.getConnection(CONNECTION_STRING, "root", "");
	}
}

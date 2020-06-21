package helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

	private static final String JDBC_URL = "jdbc:mysql://localhost/jdbc";
	private static final String JDBC_USERNAME = "root";
	private static final String JDBC_PASSWORD = "root";

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
	}
}

package main.pashkouski.kiryl.p0.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	private static String url;
	private static String username;
	private static String password;
	private static final String DB_NAME = "kiryl_2005_db";
	
	private static ConnectionFactory cf;
	
	private ConnectionFactory () {
		url = System.getenv("2005_POSTGRESQL_URL");
		url = "jdbc:postgresql://" + url + ":5432/" + DB_NAME + "?";
		username = System.getenv("2005_POSTGRESQL_USERNAME");
		password = System.getenv("2005_POSTGRESQL_PASSWORD");
	}
	
	private Connection createConnection () {
		Connection connection = null;
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Failed to load Driver");
		}
		
		try {
			connection = DriverManager.getConnection(url, username, password);
			System.out.println("Connected to the PostgreSQL successfully");
		} catch (SQLException e) {
			System.out.println("Unable to make connection");
			e.printStackTrace();
		}
		
		return connection;
	}
	
	public static Connection getConnection () {
		if (cf == null) {
			cf = new ConnectionFactory();
		}
		
		return cf.createConnection();
	}

}


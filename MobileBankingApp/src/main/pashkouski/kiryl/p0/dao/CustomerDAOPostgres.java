package main.pashkouski.kiryl.p0.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.pashkouski.kiryl.p0.customer.Customer;
import main.pashkouski.kiryl.p0.util.ConnectionFactory;

public class CustomerDAOPostgres {
	
	private static final String SAVE_CUSTOMER_QUERY = "insert into mobile_banking.customer (last_name, first_name, birthdate) values (?, ?, ?)";
	private static final String UPDATE_CUSTOMER_QUERY = "update account set account_balance = ? where last_name = ? and first_name = ?";
	private static final String GET_CUSTOMER_QUERY = "select * from mobile_banking.customer where last_name = ? and first_name = ?";

	public void saveCustomer (Customer c) {
		Connection  connection = ConnectionFactory.getConnection();
		PreparedStatement stmt;
		
		try {
			stmt = connection.prepareStatement(SAVE_CUSTOMER_QUERY);
			stmt.setString(1, c.getLastName());
			stmt.setString(2,  c.getFirstName());
			stmt.setString(3,  c.getDateOfBirth());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public Customer getCustomer (String lastName, String firstName) {
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement stmt;
		
		Customer c = new Customer ();
		
		try {
			stmt = connection.prepareStatement(GET_CUSTOMER_QUERY);
			stmt.setString(1, lastName);
			stmt.setString(2, firstName);
			
			ResultSet result = stmt.executeQuery();
			
			if (result.next()) {
				c.setFirstName(result.getString("first_name"));
				c.setLastName(result.getString("last_name"));
			}
		} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		
		return c;
	}
}

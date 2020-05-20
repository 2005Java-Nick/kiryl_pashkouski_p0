package main.pashkouski.kiryl.p0.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.pashkouski.kiryl.p0.customer.Customer;
import main.pashkouski.kiryl.p0.util.ConnectionFactory;

public class CustomerDAOPostgres {
	
	private static final String SAVE_CUSTOMER_QUERY = "insert into mobile_banking.customer (last_name, first_name, birthdate, ssn, zip_code) values (?, ?, ?, ?, ?)";
	private static final String UPDATE_CUSTOMER_QUERY = "update mobile_banking.account set account_number = ?, account_balance = ? where customerid = ?";
	private static final String GET_CUSTOMER_QUERY = "select * from mobile_banking.customer natural join mobile_banking.account where last_name = ? and first_name = ?";

	public void saveCustomer (Customer c) {
		Connection  connection = ConnectionFactory.getConnection();
		PreparedStatement stmt;
		
		System.out.println("Saving new customer...");
		
		try {
			stmt = connection.prepareStatement(SAVE_CUSTOMER_QUERY);
			stmt.setString(1, c.getLastName());
			stmt.setString(2,  c.getFirstName());
			stmt.setString(3,  c.getDateOfBirth());
			stmt.setLong(4,  c.getSSN());
			stmt.setInt(5,  c.getZipCOde());
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
		
		connection = ConnectionFactory.getConnection();
		String SQL_ID = "select customerid from mobile_banking.customer where last_name = ? and first_name = ?";
		String SQL_BLANK = "insert into mobile_banking.account (account_number, account_balance, customerid) values (?, ?, ?)";
		try {
			
			stmt = connection.prepareStatement(SQL_ID);
			stmt.setString(1, c.getLastName());
			stmt.setString(2, c.getFirstName());
			
			ResultSet result = stmt.executeQuery();
			
			int customerid = 0;
			
			while (result.next()) {
					customerid = result.getInt("customerid");
			}
			
			stmt = connection.prepareStatement(SQL_BLANK);
			stmt.setString(1, c.getAccount().getAccountNumber());
			stmt.setDouble(2, c.getAccount().getAccountBalance());
			stmt.setInt(3, customerid);
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
		
		Customer c = new Customer();
		c.createAccount();
		
		try {
			stmt = connection.prepareStatement(GET_CUSTOMER_QUERY);
			stmt.setString(1, lastName);
			stmt.setString(2, firstName);
			
			ResultSet result = stmt.executeQuery();
			
			while (result.next()) {
				c.setFirstName(result.getString("first_name"));
				c.setLastName(result.getString("last_name"));
				c.setDateOfBirth(result.getString("birthdate"));
				c.setSSN(result.getLong("ssn"));
				c.setZipCode(result.getInt("zip_code"));
				c.getAccount().setAccountNumber(result.getString("account_number"));
				c.getAccount().setAccountBalance(result.getDouble("account_balance"));
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
	
	public void updateCustomer (Customer c) {
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement stmt;
		String SQL = "select customerid from mobile_banking.customer where last_name = ? and first_name = ?";
		System.out.println("Updating information about customer...");
		
		try {
			
			stmt = connection.prepareStatement(SQL);
			stmt.setString(1, c.getLastName());
			stmt.setString(2, c.getFirstName());
			
			ResultSet result = stmt.executeQuery();
			
			int customerid = 0;
			
			while (result.next()) {
					customerid = result.getInt("customerid");
			}
			
			stmt = connection.prepareStatement(UPDATE_CUSTOMER_QUERY);
			stmt.setString(1, c.getAccount().getAccountNumber());
			stmt.setDouble(2, c.getAccount().getAccountBalance());
			stmt.setInt(3, customerid);
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
}

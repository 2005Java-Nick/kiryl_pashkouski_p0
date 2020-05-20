package main.pashkouski.kiryl.p0.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FunctionTotalAmount {
	
	public double getTotalAmount () {
		
		double totalAmount = 0;
		Connection connection = ConnectionFactory.getConnection();
		String SQL = "select mobile_banking.function_total_amount ()";
		PreparedStatement stmt;
		
		try {
			stmt = connection.prepareStatement(SQL);
			ResultSet result = stmt.executeQuery();
			
			while (result.next()) {
				totalAmount = result.getDouble(1);
			}
			} catch (SQLException e){
			e.printStackTrace();
		}
		return totalAmount;
	}

}

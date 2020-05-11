package main.pashkouski.kiryl.p0.dao;

import main.pashkouski.kiryl.p0.customer.Customer;

public interface CustomerDAO {
	public void saveCustomer (Customer c);
	
	public Customer getCustomer (String fullName);
}



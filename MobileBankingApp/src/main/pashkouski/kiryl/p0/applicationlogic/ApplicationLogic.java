package main.pashkouski.kiryl.p0.applicationlogic;

import main.pashkouski.kiryl.p0.customer.Customer;

public interface ApplicationLogic {
	
	public void createAccount (Customer c);
	
	public void depositToAccount (Customer c, double amount);
	
	public void payFromAccount (Customer c, double amount);
}

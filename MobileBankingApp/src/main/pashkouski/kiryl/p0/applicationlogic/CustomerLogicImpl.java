package main.pashkouski.kiryl.p0.applicationlogic;

import main.pashkouski.kiryl.p0.customer.Customer;

public class CustomerLogicImpl implements CustomerLogic {

	@Override
	public void deposit(Customer c) {
		// TODO Auto-generated method stub
		if (c.getAccount().getAccountBalance() == 0) {
			System.out.println("To use this account, please, deposit money");
		}
	}

	@Override
	public void pay(Customer c) {
		// TODO Auto-generated method stub
	}

}

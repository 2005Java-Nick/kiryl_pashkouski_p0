package main.pashkouski.kiryl.p0.account;

import java.io.Serializable;

public class Account implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//fields
	private String accountNumber;
	private double accountBalance;
	
	//constructors
	public Account (String accountNumber, double accountBalance) {
		this.accountNumber = accountNumber;
		this.accountBalance = accountBalance;
	}
	public Account () {
		this("0000_0000_0000_0000", 0.00);
	}
	
	//methods
	//setter and getters for variables
	public void setAccountNumber (String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public void setAccountNumber () {
		String accountNumber = this.generateAccountNumber();
		this.accountNumber = accountNumber;
	}
	public String getAccountNumber () {
		return this.accountNumber;
	}
	
	public void setAccountBalance (double accountBalance) {
		this.accountBalance = accountBalance;
	}
	public double getAccountBalance () {
		return this.accountBalance;
	}
	
	// method which generates a random 16 digit account number and returns it as a string
	public String generateAccountNumber () {
		String str = "";
		for (int i = 0; i < 16; ++i) {
			str += "" + Math.round(Math.random() * 10);
		}
		return str;
	}
	
	// method which adds money to an account
	public void addMoney (double amount) {
		this.accountBalance += amount;
	}
	
	//method which subtract money from an account
	public void withdrawMoney (double amount) {
		if (this.getAccountBalance() - amount < 0) {
			// Exception
		} else {
			this.accountBalance -= amount;
		}
	}
	
	// method which returns account balance
	public String checkBalance () {
		return "Current amount available is: $" + this.getAccountBalance();
	}
	
	@Override
	public String toString () {
		return "Account [Account Number: " + accountNumber + ";\nAccount Balance: " + accountBalance + "]";
	}

}

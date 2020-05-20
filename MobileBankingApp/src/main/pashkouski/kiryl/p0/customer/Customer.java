package main.pashkouski.kiryl.p0.customer;

import java.io.Serializable;
import java.time.LocalDate;

import org.apache.log4j.Logger;

import main.pashkouski.kiryl.p0.account.Account;
import main.pashkouski.kiryl.p0.exception.AgeLessThanEighteenException;

public class Customer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getRootLogger();
	//fields (variables);

	
	private String lastName;
	private String firstName;
	private String dateOfBirth;
	
	private String fullName;
	private transient long ssn;
	private int zipCode;
	private Account account;
	
	
//	//constructors
//	public Customer (String fullName, int age, long ssn, int zipCode) {
//		this.fullName = fullName;
//		this.age = age;
//		this.ssn = ssn;
//		this.zipCode = zipCode;
//	}
// 	
//	public Customer () {
//		this ("", 0, 000_000_0000, 00000);
//	}

	

	//methods
	public void setLastName (String lastName) {
		this.lastName = lastName;
	}
	public String getLastName () {
		return lastName;
	}
	
	public void setFirstName (String firstName) {
		this.firstName = firstName;
	}
	public String getFirstName () {
		return firstName;
	}
	
	public void setDateOfBirth (String dateOfBirth) {
		
		this.dateOfBirth = dateOfBirth;
	}
	public String getDateOfBirth () {
		return dateOfBirth;
	}
	
	// methods2
	public void setFullName () {
		fullName = this.getFirstName() + " " + this.getLastName();
	}
	public String getFullName () {
		return fullName;
	}
//	public void setFullName (String fullName) {
//		this.fullName = fullName;
//	}
//	public String getFullName () {
//		return this.fullName;
//	}
	
//	public void setAge (int age) throws AgeLessThanEighteenException {
//		//logic to check if age > 18;
//		if (age >= 18) {
//			this.age = age;
//		} else {
//			throw new IllegalArgumentException();
//		}
//	}
//	public int getAge () {
//		return age;
//	}
	public void checkForValidAge () {
		LocalDate currentDate = LocalDate.now();
		String dob[] = this.getDateOfBirth().split("-");
		int year = Integer.parseInt(dob[0]);
		int month = Integer.parseInt(dob[1]);
		int day = Integer.parseInt(dob[2]);
		LocalDate dateOfBirth = LocalDate.of(year, month, day);
		if (currentDate.compareTo(dateOfBirth) > 18) {
			System.out.println("You're old enough to open an account");
		} else {
			System.out.println("You're too young to open an account");
		}
	}
	
	public void setSSN (long ssn) {
		this.ssn = ssn;
	}
	public long getSSN () {
		return this.ssn;
	}
	
	public void setZipCode (int zipCode) {
		this.zipCode = zipCode;
	}
	public int getZipCOde () {
		return this.zipCode;
	}
	
	// method which opens an account for this customer
	public void createAccount () {
		account = new Account();
		account.setAccountNumber();
		account.setAccountBalance(0.00);
	}
	public Account getAccount() {
		return account;
	}


	// method which deposits money on an account (provide account number to deposit and amount;)
	public void depositToAccount(Customer c, String accountNumber, double amount) {
		if (accountNumber.equals(c.getAccount().getAccountNumber())) { c.getAccount().addMoney(amount);
		}
	}
	// method which pays money from an account (provide account number to pay from and amount; check whether or not amount to pay > account balance);
	public void payFromAccount(Customer c, String accountNumber, double amount) {
		if (accountNumber.equals(c.getAccount().getAccountNumber()) && c.getAccount().getAccountBalance() >= amount) {
			c.getAccount().withdrawMoney(amount);
		} else {System.out.println("Not enough money to process this transaction");}
	}
	@Override
	public String toString() {
		return "Customer: \n[Full Name: " + this.getFullName() + ";\nDate of birth: " + this.getDateOfBirth() + 
				"\nSSN: " + this.getSSN() + "\nZip Code: " + this.getZipCOde() + "\nAccount: " + this.getAccount() + "]";
	}
}

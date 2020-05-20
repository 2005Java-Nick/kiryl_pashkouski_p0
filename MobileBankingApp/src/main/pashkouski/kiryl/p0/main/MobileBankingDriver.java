package main.pashkouski.kiryl.p0.main;
import java.util.Scanner;
import main.pashkouski.kiryl.p0.customer.Customer;
import main.pashkouski.kiryl.p0.dao.CustomerDAOPostgres;
import main.pashkouski.kiryl.p0.util.FunctionTotalAmount;

/**
 * Project_0 for Revature
 * @author Kiryl Pashkouski
 */

public class MobileBankingDriver {
	private static Scanner scanner = new Scanner (System.in);
	private static CustomerDAOPostgres customerDAO = new CustomerDAOPostgres();

	public static void main(String[] args) {
		
		System.out.println("Welcome to your Mobile Bank Account");
		System.out.println("version: 0.2.1"); //version updated on May, 20th;
		System.out.println("===================================");
/*
* Part I: Initialization
*/
		//Step1: LastName
		System.out.println("Enter last name:");
		String lastName = scanner.nextLine();
		
		//Step2: FirstName
		System.out.println("Enter first name:");
		String firstName = scanner.nextLine();
		
		//Step3: Check for customer in DB
		Customer c = customerDAO.getCustomer(lastName, firstName);
		if (c.getLastName() == null) {
			System.out.println("You're new customer");
			c = new Customer();
			//Step4: Assign Name
			c.setFirstName(firstName);
			c.setLastName(lastName);
			c.setFullName();
			System.out.println(c.getFullName());
			
			//Step5: Assign BirthDate
			System.out.println("Enter your DOB (YYYY-mm-dd):");
			String birthdate = scanner.nextLine();
			c.setDateOfBirth(birthdate);
			c.checkForValidAge();
			System.out.println(c);
			
			//Step 6: Assign SSN and ZipCode
			System.out.println("\nPlease, enter your SSN:");
			long ssn = scanner.nextLong();
			c.setSSN(ssn);
			System.out.println("Please, enter your zipCode:");
			int zip = scanner.nextInt();
			c.setZipCode(zip);
			//Step 7: Create account
			c.createAccount();
			System.out.println(c.getAccount());
			System.out.println("===================================");
			System.out.println(c.getAccount().checkBalance());
			
			customerDAO.saveCustomer(c);
		
		} else {
			System.out.println("You are existent customer");
			c.setFullName();
			System.out.println(c);
		}
		
		System.out.println("===================================");
		
/*
* Part II: Operations		
*/

		String answer = null;
		double amount = 0;
		do {
			System.out.println("What do you want to do: DEPOSIT, PAY OR EXIT:");
			answer = scanner.next();
			if (answer.equalsIgnoreCase("Deposit")) {
				System.out.println("What amount you want to deposit:");
				amount = scanner.nextDouble();
				c.depositToAccount(c, c.getAccount().getAccountNumber(), amount);
				System.out.println("Funds available for: " + c.getFullName() + " " + c.getAccount().getAccountBalance());
			}
			if (answer.equalsIgnoreCase("Pay")) {
				System.out.println("How much you'd like to pay:");
				amount = scanner.nextDouble();
				c.payFromAccount(c, c.getAccount().getAccountNumber(), amount);
				System.out.println("Funds available for: " + c.getFullName() + " " + c.getAccount().getAccountBalance());
			}
		} while (!"Exit".equalsIgnoreCase(answer));
		
		System.out.println(c.getFullName());
		System.out.println(c.getDateOfBirth());
		System.out.println(c.getAccount().getAccountNumber());
		System.out.println(c.getAccount().getAccountBalance());
		customerDAO.updateCustomer(c);
		
		scanner.close();
/*
* Part III: SQL Function
*/
		FunctionTotalAmount function1 = new FunctionTotalAmount();
		System.out.println("TOTAL AMOUNT OF MONEY INSIDE BANK IS: " + function1.getTotalAmount());
		System.out.println("=================================");
		System.out.println("=================================");
	}
}

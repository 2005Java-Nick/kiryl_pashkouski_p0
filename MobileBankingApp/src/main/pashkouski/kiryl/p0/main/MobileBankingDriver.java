package main.pashkouski.kiryl.p0.main;
import java.util.Scanner;
import main.pashkouski.kiryl.p0.customer.Customer;
import main.pashkouski.kiryl.p0.dao.CustomerDAOSerialization;
import main.pashkouski.kiryl.p0.exception.AgeLessThanEighteenException;

/**
 * @author Kiryl Pashkouski
 */

public class MobileBankingDriver {

	public static void main(String[] args) throws AgeLessThanEighteenException {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner (System.in);
		// object of class CustomerDAOSerialization
		
		CustomerDAOSerialization customerDAO = new CustomerDAOSerialization();
		
		System.out.println("Welcome to your Mobile Bank Account");
		System.out.println("version: 0.1.1");
		System.out.println("===================================");
		System.out.println("\nPlease, enter your full name:");
		String name = scanner.nextLine();
		System.out.println("\nPlease, enter you age:");
		int age = scanner.nextInt();
		System.out.println("\nPlease, enter your SSN:");
		int ssn = scanner.nextInt();
		System.out.println("Please, enter your zipCode:");
		int zip = scanner.nextInt();
		
		Customer c = new Customer(name, age, ssn, zip);
		System.out.println(c);
		c.createAccount();
		System.out.println(c.getAccount());
		System.out.println("===================================");
		System.out.println(c.getAccount().checkBalance());

		
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
		
		// Save (Serialize) Customer
		customerDAO.saveCustomer(c);

		//scanner.close();
		System.out.println("=================================");
		System.out.println("=================================");

		System.out.println("Enter name:");
		String fullName = scanner.next();
		Customer c2 = null;
		c2 = customerDAO.getCustomer(fullName);
		System.out.println(c2);
		System.out.println(c2.getAccount().getAccountBalance());
		System.out.println(c2.getAccount().getAccountNumber());

		scanner.close();
	}
}

package main.pashkouski.kiryl.p0.main;
import java.util.Scanner;
import main.pashkouski.kiryl.p0.account.Account;
import main.pashkouski.kiryl.p0.customer.Customer;
import main.pashkouski.kiryl.p0.exception.AgeLessThanEighteenException;

/*
 * P0_Kiryl Pashkouski_Mobile Banking App
- person creates an account (name, DOB, SSN, ZIP, phone) <-- data to store;
- customer creates username, password (check if unique);
- customer logs into account (check if password matches stored) (log history to store);
- customer can deposit, transfer, pay (bill alert? probably by date);
- ---- checks balance;
- app issues a statement (store sequence of statements) <-- data to store;
- account history (display chronologically) <-- data to store;
- account activity (alerts: if pay amount > 75% of remaining balance, net balance < some amount in $ or 5% of  some amount);
- ATM location based on ZIP code;

- Joint account;
 */

public class MobileBankingDriver {

	public static void main(String[] args) throws AgeLessThanEighteenException {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner (System.in);
		
		System.out.println("Welcome to your Mobile Bank Account");
		System.out.println("\nPlease,enter your full name:");
		String name = scanner.nextLine();
		System.out.println("\nPlease, enter you age:");
		int age = scanner.nextInt();
		System.out.println("\nPlease, enter your SSN:");
		int ssn = scanner.nextInt();
		System.out.println("Please, enter your zipCode:");
		int zip = scanner.nextInt();
		
		Customer c = new Customer(name, age, ssn, zip);
		System.out.println(c);
		System.out.println("Would you like to open an account:");
		String reply = scanner.nextLine();
		if (reply.equalsIgnoreCase("Yes")) {
			c.createAccount();
			System.out.println(c.getAccount());
		} else {
			System.out.println("Sorry, to see you go");
		}
		System.out.println("\nDeposit or Pay:");
		if (scanner.nextLine().equalsIgnoreCase("Deposit")) {
			System.out.println("What amount you want to deposit:");
			double amount = scanner.nextDouble();
			c.depositToAccount(c, c.getAccount().getAccountNumber(), amount);
			System.out.println("Funds available for: " + c.getFullName() + " " + c.getAccount().getAccountBalance());
		} else {
			System.out.println("How much you'd like to pay:");
			double amount = scanner.nextDouble();
			c.payFromAccount(c, c.getAccount().getAccountNumber(), 250);
			System.out.println("Funds available for: " + c.getFullName() + " " + c.getAccount().getAccountBalance());
		}
		scanner.close();



	}

}

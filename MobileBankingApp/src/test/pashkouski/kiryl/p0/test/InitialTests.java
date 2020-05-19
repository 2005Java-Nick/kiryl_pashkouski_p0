package test.pashkouski.kiryl.p0.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import main.pashkouski.kiryl.p0.account.Account;
import main.pashkouski.kiryl.p0.customer.Customer;
import main.pashkouski.kiryl.p0.dao.CustomerDAOSerialization;

public class InitialTests {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {

		
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void TestForConstructingFullName () {
		Customer c = new Customer ();
		c.setFirstName("Jane");
		c.setLastName("Doe");
		c.setFullName();
		String fullName = c.getFullName();
		assertEquals("The method returns proper full name:", "Jane Doe", fullName);
	}
//	@Test
//	public void TestForValidAge () {
//		
//	}

//	@Test
//	public void GetCustomerReturnsTheSameObject() {
//		CustomerDAOSerialization customerDAO = new CustomerDAOSerialization();
//		Customer customer = new Customer ("Bill", 21, 1231231234, 12345);
//		customerDAO.saveCustomer(customer);
//		Customer customer2 = null;
//		customer2 = customerDAO.getCustomer("Bill");
//		
//		assertTrue(customer2.getFullName().equals(customer.getFullName()));
//		assertTrue(customer2.getAge() == customer.getAge());
//		}
	@Test
	public void checkBalanceReturnsCorrectAmount () {
		Account account = new Account ();
		account.setAccountBalance(500);
		String result = account.checkBalance();
		assertEquals("Current amount available is: $500.0", result);
	}
	@Test
	public void addMoneyProperlyAddsAmountToAccount () {
		Account account = new Account ();
		account.addMoney(1000);
		assertTrue(1000 == account.getAccountBalance());
	}
	
}

package main.pashkouski.kiryl.p0.dao;

import java.io.*;

import main.pashkouski.kiryl.p0.customer.Customer;

public class CustomerDAOSerialization implements CustomerDAO {

	@Override
	public void saveCustomer(Customer c) {
		try {
			FileOutputStream fileOutputStream = new FileOutputStream ("./customer.ser");
			ObjectOutputStream objectOutputStream = new ObjectOutputStream (fileOutputStream);
			objectOutputStream.writeObject(c);
			objectOutputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Customer getCustomer(String fullName) {
		Customer c = null;
		try {
			FileInputStream fileInputStream = new FileInputStream("./customer.ser");
			ObjectInputStream objectInputStream = new ObjectInputStream (fileInputStream);
			c = (Customer) objectInputStream.readObject();
			objectInputStream.close();
		}  catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return c;
	}

}

package main.pashkouski.kiryl.p0.exception;

public class AgeLessThanEighteenException extends Exception {
	// create a message
	public static final String MSG = "A customer has to be older than 18 years old";
	
	// exception constructors
	
	public AgeLessThanEighteenException () {
		super(MSG);
	}
	
	public AgeLessThanEighteenException(Throwable cause) {
		super(MSG, cause);
	}
}
package model.payment;


import java.util.ArrayList;
import java.util.logging.Logger;

//Abstract class for other Status classes
abstract public class Status {
	//Constant to log the errors
	public static final Logger log = Logger.getLogger(Status.class.getName());
	String color;
	String description;
	
	public abstract ArrayList<Payment> getPaymentsByStatus(String type);
	
	public Status(){
		
	}
}

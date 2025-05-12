package service.payment;
import model.payment.Payment;

import java.util.logging.Logger;  // Java's built-in logger
import java.util.*;


public interface IPaymentService {

	public static final Logger log = Logger.getLogger(IPaymentService.class.getName());
	
	public void addpayment(Payment payment);
	
	public ArrayList<Payment> getArchivedPayments(int num) ;
	
	public ArrayList<Payment> getPaymentsById(int id) ;
	
	public ArrayList<Payment> getPayments();
	
	public void updatePayment(int paymentID,String status);
	
	public void removePayment(int paymentID);
	
	public boolean DeletePayment(int paymentID);
	
	
}

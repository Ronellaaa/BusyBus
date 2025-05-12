package model.customer;

public class Customer {
	
	
private int customer_id;
private String first_Name;
 private String last_Name;
 private String  email;
 private String phoneNumber;
 private String password;
 
// public Customer(String fist_Name, String last_Name, String email, String phoneNumber, String password) {
//
//		this.fist_Name = fist_Name;
//		this.last_Name = last_Name;
//		this.email = email;
//		this.phoneNumber = phoneNumber;
//		this.password = password;
//	}
 public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
 public String getFirst_Name() {
	return first_Name;
}
public void setFirst_Name(String first_Name) {
	this.first_Name = first_Name;
}
public String getLast_Name() {
	return last_Name;
}
public void setLast_Name(String last_Name) {
	this.last_Name = last_Name;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPhoneNumber() {
	return phoneNumber;
}
public void setPhoneNumber(String phoneNumber) {
	this.phoneNumber = phoneNumber;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}

 
 
 
 
 
 
}

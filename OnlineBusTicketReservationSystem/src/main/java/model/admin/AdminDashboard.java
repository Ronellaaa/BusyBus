package model.admin;

public class AdminDashboard {
	
	private AdminCus cust;
	private AdminBooking booking;
	private AdminBus bus;
	private AdminRoutes routes;
	private AdminAddTicket ticket;
	
	
	public AdminCus getCust() {
		return cust;
	}
	public void setCust(AdminCus cust) {
		this.cust = cust;
	}
	public AdminBooking getBooking() {
		return booking;
	}
	public void setBooking(AdminBooking booking) {
		this.booking = booking;
	}
	public AdminBus getBus() {
		return bus;
	}
	public void setBus(AdminBus bus) {
		this.bus = bus;
	}
	public AdminRoutes getRoutes() {
		return routes;
	}
	public void setRoutes(AdminRoutes routes) {
		this.routes = routes;
	}
	public AdminAddTicket getTicket() {
		return ticket;
	}
	public void setTicket(AdminAddTicket ticket) {
		this.ticket = ticket;
	}
}

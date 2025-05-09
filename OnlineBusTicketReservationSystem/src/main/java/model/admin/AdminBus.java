package model.admin;

import java.time.LocalTime;

public class AdminBus {
	
	
	int bus_id ;
    String bus_name ;
    String bus_type;
    double price_per_seat ;
    int total_seats ;
    LocalTime departure_time ;
    LocalTime arrival_time ;
    int seats_available;
    boolean is_active ;
    
    
    
	public int getBus_id() {
		return bus_id;
	}
	public void setBus_id(int bus_id) {
		this.bus_id = bus_id;
	}
	public String getBus_name() {
		return bus_name;
	}
	public void setBus_name(String bus_name) {
		this.bus_name = bus_name;
	}
	public String getBus_type() {
		return bus_type;
	}
	public void setBus_type(String bus_type) {
		this.bus_type = bus_type;
	}
	public double getPrice_per_seat() {
		return price_per_seat;
	}
	public void setPrice_per_seat(double price_per_seat) {
		this.price_per_seat = price_per_seat;
	}
	public int getTotal_seats() {
		return total_seats;
	}
	public void setTotal_seats(int total_seats) {
		this.total_seats = total_seats;
	}
	public LocalTime getDeparture_time() {
		return departure_time;
	}
	public void setDeparture_time(LocalTime departure_time) {
		this.departure_time = departure_time;
	}
	public LocalTime getArrival_time() {
		return arrival_time;
	}
	public void setArrival_time(LocalTime arrival_time) {
		this.arrival_time = arrival_time;
	}
	public int getSeats_available() {
		return seats_available;
	}
	public void setSeats_available(int seats_available) {
		this.seats_available = seats_available;
	}
	public boolean isIs_active() {
		return is_active;
	}
	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}
    
   
}
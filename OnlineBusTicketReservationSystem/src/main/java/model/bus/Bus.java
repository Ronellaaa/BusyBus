package model.bus;

import java.time.LocalTime;

public class Bus {

    private int busId = -1;  // Default value to indicate it's not set (auto-incremented)
    private String busName;
    private String busType;
    private double pricePerSeat;
    private int totalSeats;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private int seatsAvailable;
    private boolean isActive;
    private String routeName;

    // Default constructor
    public Bus() {
        // Empty constructor (could be used for deserialization or frameworks)
    }

    // Parameterized constructor for initializing bus object
    public Bus(String busName, String busType, double pricePerSeat, int totalSeats,
               LocalTime departureTime, LocalTime arrivalTime, int seatsAvailable, boolean isActive) {
        this.busName = busName;
        this.busType = busType;
        this.pricePerSeat = pricePerSeat;
        this.totalSeats = totalSeats;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.seatsAvailable = seatsAvailable;
        this.isActive = isActive;
    }

    // Getters and Setters
    public int getBusId() {
        return busId;
    }

    public void setBusId(int busId) {
        this.busId = busId;
    }

    public String getBusName() {
        return busName;
    }

    public void setBusName(String busName) {
        this.busName = busName;
    }

    public String getBusType() {
        return busType;
    }

    public void setBusType(String busType) {
        this.busType = busType;
    }

    public double getPricePerSeat() {
        return pricePerSeat;
    }

    public void setPricePerSeat(double pricePerSeat) {
        this.pricePerSeat = pricePerSeat;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getSeatsAvailable() {
        return seatsAvailable;
    }

    public void setSeatsAvailable(int seatsAvailable) {
        this.seatsAvailable = seatsAvailable;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "Bus ID = " + busId + "\n" + "Bus Name = " + busName + "\n" + "Bus Type = " + busType + "\n" +
               "Price per Seat = " + pricePerSeat + "\n" + "Total Seats = " + totalSeats + "\n" + 
               "Departure Time = " + departureTime + "\n" + "Arrival Time = " + arrivalTime + "\n" +
               "Seats Available = " + seatsAvailable + "\n" + "Active = " + isActive;
    }
    
    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

}

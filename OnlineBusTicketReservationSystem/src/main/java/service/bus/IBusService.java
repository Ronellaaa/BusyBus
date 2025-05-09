package service.bus;

import model.bus.Bus;
import java.util.ArrayList;

/**
 * Contract for the implementation of Bus Service.
 * @version 1.0
 */
public interface IBusService {

    /**
     * Add a bus to the database.
     * @param bus the Bus object to be added
     */
    public void addBus(Bus bus);

    /**
     * Get a particular bus by its ID.
     * @param busId the ID of the bus
     * @return the Bus object
     */
    public Bus getBusByID(int busId);

    /**
     * Get all buses.
     * @return list of all buses
     */
    public ArrayList<Bus> getBuses();

    /**
     * Update an existing bus.
     * @param busId the ID of the bus to update
     * @param bus the Bus object with updated details
     * @return the updated Bus object
     */
    public Bus updateBus(int busId, Bus bus);

    /**
     * Remove an existing bus.
     * @param busId the ID of the bus to remove
     */
    public void removeBus(int busId);
    
    /**
     * Get the count of active buses.
     * @return the number of active buses
     */
    public int getActiveBusCount();

    /**
     * Get the total count of buses.
     * @return the total number of buses
     */
    public int getTotalBusCount();

    /**
     * Get the count of upcoming departures for today.
     * @return the number of buses departing today
     */
    public int getUpcomingDeparturesTodayCount();
    
    /**
     * Retrieves a list of all buses along with their associated route names.
     * @return a list of buses with route names
     */
    public ArrayList<Bus> getBusesWithRouteNames();

}

package com.system;

/**
 *
 * @author HP
 */
import java.sql.Time;

public class Flight {
    private String flightNumber;
    private String flightName;
    private String departure;
    private String destination;
   // private int seatsAvailable;
    private Time departureTime;
    private Time arrivalTime;
    private double cost;

    public Flight(String flightNumber, String flightName, String departure, String destination, /*int seatsAvailable,*/double cost,Time departureTime,Time arrivalTime) {
        this.flightNumber = flightNumber;
        this.flightName=flightName;
        this.departure = departure;
        this.destination = destination;
        //this.seatsAvailable = seatsAvailable;
        this.departureTime=departureTime;
        this.arrivalTime=arrivalTime;
        this.cost=cost;
        
        
    }

    public String getFlightNumber() {
        return flightNumber;
    }
    
    public String getFlightName() {
        return flightName;
    }
     

    public String getDeparture() {
        return departure;
    }

    public String getDestination() {
        return destination;
    }

    /*public int getSeatsAvailable() {
        return seatsAvailable;
    }*/

public Time getdepartureTime() {
        return departureTime;
    }

    public Time getarrivalTime() {
        return arrivalTime;
    }
    
    public double getCost(){
       return cost;
    }
}




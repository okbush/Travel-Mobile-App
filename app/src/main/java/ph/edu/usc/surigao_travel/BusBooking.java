package ph.edu.usc.surigao_travel;

import java.io.Serializable;

public class BusBooking implements Serializable {
    private String passengerName;
    private String busName;
    private String departure;
    private String arrival;
    private String travelDate;
    private String price;

    public BusBooking(String passengerName, String busName, String departure, String arrival, String travelDate, String price) {
        this.passengerName = passengerName;
        this.busName = busName;
        this.departure = departure;
        this.arrival = arrival;
        this.travelDate = travelDate;
        this.price = price;
    }

    public String getPassengerName() { return passengerName; }
    public String getBusName() { return busName; }
    public String getDeparture() { return departure; }
    public String getArrival() { return arrival; }
    public String getTravelDate() { return travelDate; }
    public String getPrice() { return price; }
}

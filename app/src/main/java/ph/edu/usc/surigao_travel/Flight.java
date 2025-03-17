package ph.edu.usc.surigao_travel;

public class Flight {
    private String airline;
    private String flightNumber;
    private String departureTime;
    private String arrivalTime;
    private String duration;
    private String seatClass;
    private String price;

    public Flight(String airline, String flightNumber, String departureTime, String arrivalTime,
                  String duration, String seatClass, String price) {
        this.airline = airline;
        this.flightNumber = flightNumber;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.duration = duration;
        this.seatClass = seatClass;
        this.price = price;
    }

    public String getAirline() { return airline; }
    public String getFlightNumber() { return flightNumber; }
    public String getDepartureTime() { return departureTime; }
    public String getArrivalTime() { return arrivalTime; }
    public String getDuration() { return duration; }
    public String getSeatClass() { return seatClass; }
    public String getPrice() { return price; }
}
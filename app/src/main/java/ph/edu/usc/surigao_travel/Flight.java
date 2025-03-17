package ph.edu.usc.surigao_travel;

public class Flight {
    private String airline, flightNumber, departureTime, arrivalTime, duration;
    private String from, to, departureDate, returnDate, seatClass, price;

    public Flight(String airline, String flightNumber, String departureTime, String arrivalTime,
                  String duration, String from, String to, String departureDate, String returnDate,
                  String seatClass, String price) {
        this.airline = airline;
        this.flightNumber = flightNumber;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.duration = duration;
        this.from = from;
        this.to = to;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.seatClass = seatClass;
        this.price = price;
    }

    public String getAirline() { return airline; }
    public String getFlightNumber() { return flightNumber; }
    public String getDepartureTime() { return departureTime; }
    public String getArrivalTime() { return arrivalTime; }
    public String getDuration() { return duration; }
    public String getFrom() { return from; }
    public String getTo() { return to; }
    public String getDepartureDate() { return departureDate; }
    public String getReturnDate() { return returnDate; }
    public String getSeatClass() { return seatClass; }
    public String getPrice() { return price; }
}
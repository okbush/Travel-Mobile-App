package ph.edu.usc.surigao_travel;

public class Flight {
    private String airline;
    private String from;
    private String to;
    private String departureTime;
    private String returnTime;
    private String price;

    public Flight(String airline, String from, String to, String departureTime, String returnTime, String price) {
        this.airline = airline;
        this.from = from;
        this.to = to;
        this.departureTime = departureTime;
        this.returnTime = returnTime;
        this.price = price;
    }

    public String getAirline() { return airline; }
    public String getFrom() { return from; }
    public String getTo() { return to; }
    public String getDepartureTime() { return departureTime; }
    public String getReturnTime() { return returnTime; }
    public String getPrice() { return price; }
}

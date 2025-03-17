package ph.edu.usc.surigao_travel;

public class Bus {
    private String name;
    private String departure;
    private String arrival;
    private String travel;
    private String price;

    public Bus(String name, String departure, String arrival, String travel, String price) {
        this.name = name;
        this.departure = departure;
        this.arrival = arrival;
        this.travel = travel;
        this.price = price;
    }

    public String getName() { return name; }
    public String getDeparture() { return departure; }
    public String getArrival() { return arrival; }
    public String getTravel() { return travel; }
    public String getPrice() { return price; }
}

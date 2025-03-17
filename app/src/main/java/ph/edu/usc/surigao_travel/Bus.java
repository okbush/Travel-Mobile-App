package ph.edu.usc.surigao_travel;

public class Bus {
    private String name;
    private String from;
    private String guests;
    private String checkin;
    private String checkout;
    private String price;

    public Hotel(String name, String from, String guests, String checkin, String checkout, String price) {
        this.name = name;
        this.from = from;
        this.guests = guests;
        this.checkin = checkin;
        this.checkout = checkout;
        this.price = price;
    }

    public String getName() { return name; }
    public String getFrom() { return from; }
    public String getGuests() { return guests; }
    public String getCheckin() { return checkin; }
    public String getCheckout() { return checkout; }
    public String getPrice() { return price; }
}

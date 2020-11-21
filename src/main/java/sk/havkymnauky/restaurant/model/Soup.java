package sk.havkymnauky.restaurant.model;


public class Soup {

    private long soupID;
    private String soupName;
    private double soupPrice;

    public Soup(String soupName, double soupPrice) {
        this.soupName = soupName;
        this.soupPrice = soupPrice;
    }

    public Soup(long id, String soupName, double soupPrice) {
        this.soupID = id;
        this.soupName = soupName;
        this.soupPrice = soupPrice;
    }

    public Soup() {
    }

    public long getID() {
        return soupID;
    }

    public void setID(long soupID) {
        this.soupID = soupID;
    }

    public String getName() {
        return soupName;
    }

    public void setName(String soupName) {
        this.soupName = soupName;
    }

    public double getPrice() {
        return soupPrice;
    }

    public void setPrice(double soupPrice) {
        this.soupPrice = soupPrice;
    }
}

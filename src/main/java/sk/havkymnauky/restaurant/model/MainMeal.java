package sk.havkymnauky.restaurant.model;

public class MainMeal {

    private long id;
    private String name;
    private double price;

    public MainMeal(long id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public MainMeal() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

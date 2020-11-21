package sk.havkymnauky.restaurant.model;


import java.util.*;

public class Menu {
    private long id;
    private Date menuDate;
    private List<Soup> soups = new ArrayList<>();
    private List<MainMeal> mainMeals = new ArrayList<>();

    public Menu(Date menuDate) {
        this.menuDate = menuDate;
    }

    public Menu(long id, Date menuDate, List<Soup> soups, List<MainMeal> mainMeals) {
        this.id = id;
        this.menuDate = menuDate;
        this.soups = soups;
        this.mainMeals = mainMeals;
    }

    public Menu(long id, Date menuDate) {
        this.id = id;
        this.menuDate = menuDate;
    }

    public Menu() {
    }

    public Date getMenuDate() {
        return menuDate;
    }

    public void setMenuDate(Date menuDate) {
        this.menuDate = menuDate;
    }

    public List<MainMeal> getMainMeals() {
        return mainMeals;
    }

    public void setMainMeals(List<MainMeal> mainMeals) {
        this.mainMeals = mainMeals;
    }

    public long getId() {
        return id;
    }

    public List<Soup> getSoups() {
        return soups;
    }

    public void setSoups(List<Soup> menuSoup) {
        this.soups = menuSoup;
    }

    public void setId(long menuId) {
        this.id = menuId;
    }

    public Date getDate() {
        return menuDate;
    }

    public void setDate(Date menuDate) {
        this.menuDate = menuDate;
    }
}

package sk.havkymnauky.restaurant.model;

import javax.persistence.*;

@Entity
@Table( name = DBProperties.MAIN_MEAL)
public class MainMeal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = DBProperties.MAIN_MEAL_ID )
    private long mealID;

    @Column( name = DBProperties.MAIN_MEAL_NAME )
    private String mealName;

    @Column( name = DBProperties.MAIN_MEAL_PRICE )
    private double mealPrice;

    public MainMeal(String mealName, double mealPrice) {
        this.mealName = mealName;
        this.mealPrice = mealPrice;
    }

    public MainMeal() {
    }

    public long getMealID() {
        return mealID;
    }

    public void setMealID(long mealID) {
        this.mealID = mealID;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public double getMealPrice() {
        return mealPrice;
    }

    public void setMealPrice(double mealPrice) {
        this.mealPrice = mealPrice;
    }
}

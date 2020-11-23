package sk.havkymnauky.restaurant.model.dto;

import sk.havkymnauky.restaurant.model.MainMeal;
import sk.havkymnauky.restaurant.model.Soup;

import java.util.Date;
import java.util.List;

public class MenuDTO {

    public long id;
    public Date menuDate;
    public List<Soup> soups;
    public List<MainMeal> mainMeals;
}

package sk.havkymnauky.restaurant.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import sk.havkymnauky.restaurant.model.Menu;

import java.util.List;

public class SoupDTO {

    public long soupId;
    public String soupName;
    public double soupPrice;
    public List<Menu> menus;
}

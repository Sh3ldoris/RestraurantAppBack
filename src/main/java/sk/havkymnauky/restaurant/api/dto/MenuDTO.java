package sk.havkymnauky.restaurant.api.dto;

import sk.havkymnauky.restaurant.model.Soup;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

public class MenuDTO {

    public long id;
    public Date menuDate;
    public Set<Soup> soups;
}

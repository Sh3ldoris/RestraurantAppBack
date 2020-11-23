package sk.havkymnauky.restaurant.repository;

import sk.havkymnauky.restaurant.model.Menu;

import java.util.Date;
import java.util.List;

public interface IMenuRepository{

    List<Menu> findAll();
    Menu findById(long id);
    Menu findCurrent();
    void saveMenu(Menu menu);
    void deleteMenu(long id);
    Menu findByDate(Date date);
}

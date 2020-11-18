package sk.havkymnauky.restaurant.service;

import sk.havkymnauky.restaurant.error.RestaurantFault;
import sk.havkymnauky.restaurant.model.Menu;
import sk.havkymnauky.restaurant.repository.IMenuRepository;

import java.util.List;

public interface IMenuService {

    List<Menu> getAllMenu();
    Menu getCurrentMenu();
    Menu saveMenu(Menu newMenu) throws RestaurantFault;
    Menu updateMenu(Menu menuToUpdate);
    void deleteMenu(long id) throws RestaurantFault;
}

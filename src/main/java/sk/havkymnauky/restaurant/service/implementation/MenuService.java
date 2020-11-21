package sk.havkymnauky.restaurant.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import sk.havkymnauky.restaurant.error.RestaurantFault;
import sk.havkymnauky.restaurant.model.Menu;
import sk.havkymnauky.restaurant.repository.IMenuRepository;
import sk.havkymnauky.restaurant.service.IMenuService;

import java.util.List;

@Service
public class MenuService implements IMenuService {

    @Autowired
    @Qualifier("JDBCMenuRepo")
    private IMenuRepository menuRepository;

    public MenuService(IMenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
    public List<Menu> getAll() {
        return menuRepository.findAll();
    }

    @Override
    public Menu getCurrent() {
        return menuRepository.findCurrent();
    }

    @Override
    public void saveMenu(Menu newMenu) throws RestaurantFault {
        if (newMenu.getDate() == null) {
            throw new RestaurantFault("New menu is missing date!");
        }

        if (menuRepository.findByDate(newMenu.getDate()) != null)
            throw new RestaurantFault("Menu with this date already exists!");

        menuRepository.saveMenu(newMenu);
    }

    @Override
    public void updateMenu(Menu menuToUpdate) {
        menuRepository.saveMenu(menuToUpdate);
    }

    @Override
    public void deleteMenu(long id) {
        menuRepository.deleteMenu(id);
    }
}

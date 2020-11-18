package sk.havkymnauky.restaurant.service.implementation;

import org.springframework.stereotype.Service;
import sk.havkymnauky.restaurant.error.RestaurantFault;
import sk.havkymnauky.restaurant.model.Menu;
import sk.havkymnauky.restaurant.repository.IMenuRepository;
import sk.havkymnauky.restaurant.service.IMenuService;

import java.util.List;

@Service
public class MenuService implements IMenuService {

    private IMenuRepository menuRepository;

    public MenuService(IMenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
    public List<Menu> getAllMenu() {
        return menuRepository.findAll();
    }

    @Override
    public Menu getCurrentMenu() {
        return menuRepository.findTopByOrderByMenuDateDesc();
    }

    @Override
    public Menu saveMenu(Menu newMenu) throws RestaurantFault {

        if (newMenu.getDate() == null) {
            throw new RestaurantFault("Nové menu je bez dátumu!");
        } else {
            Menu added = menuRepository.save(newMenu);
            if ( added == null)
                throw new RestaurantFault("Pri ukladaní došlo ku chybe!");
            return added;
        }
    }

    @Override
    public Menu updateMenu(Menu menuToUpdate) {
        //TODO: Get done some checking
        return menuRepository.save(menuToUpdate);
    }

    @Override
    public void deleteMenu(long id) throws RestaurantFault {
        if (menuRepository.findById(id) != null) {
            menuRepository.deleteById(id);
        } else {
            throw new RestaurantFault("Menu nie je možné vymazať!");
        }
    }
}

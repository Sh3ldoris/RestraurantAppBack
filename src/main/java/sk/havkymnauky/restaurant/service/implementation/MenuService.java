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
        return menuRepository.findCurrent();
    }

    @Override
    public void saveMenu(Menu newMenu) throws RestaurantFault {
        //TODO: dont save menu with same menu date

        if (newMenu.getDate() == null) {
            //TODO: ilegal argument ex
            throw new RestaurantFault("Nové menu je bez dátumu!");
        }
        menuRepository.saveMenu(newMenu);
    }

    @Override
    public void updateMenu(Menu menuToUpdate) {
        //TODO: Get done some checking
        menuRepository.saveMenu(menuToUpdate);
    }

    @Override
    public void deleteMenu(long id) throws RestaurantFault {
        menuRepository.deleteMenu(id);
    }
}

package sk.havkymnauky.restaurant.api;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import sk.havkymnauky.restaurant.api.dto.MainMealDTO;
import sk.havkymnauky.restaurant.api.dto.MenuDTO;
import sk.havkymnauky.restaurant.api.dto.SoupDTO;
import sk.havkymnauky.restaurant.error.RestaurantFault;
import sk.havkymnauky.restaurant.model.MainMeal;
import sk.havkymnauky.restaurant.model.Menu;
import sk.havkymnauky.restaurant.model.Soup;
import sk.havkymnauky.restaurant.repository.IMenuRepository;
import sk.havkymnauky.restaurant.service.IMainMealService;
import sk.havkymnauky.restaurant.service.IMenuService;
import sk.havkymnauky.restaurant.service.ISoupService;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

//@RestController
public class Controller {
/*
    private final IMainMealService mainMealService;
    private final IMenuService menuService;
    private final ISoupService soupService;
    private final ModelMapper modelMapper;
    private final IMenuRepository menuRepository;

    public Controller(IMainMealService mainMealService, IMenuService menuService, ISoupService soupService, ModelMapper modelMapper, IMenuRepository menuRepository) {
        this.mainMealService = mainMealService;
        this.menuService = menuService;
        this.soupService = soupService;
        this.modelMapper = modelMapper;
        this.menuRepository = menuRepository;
    }

    @GetMapping("/getAllMeals")
    public List<MainMealDTO> getAllMeals() {

        return mainMealService.getAllMainMeals().stream()
                .map(meal -> modelMapper.map(meal, MainMealDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/getMeal{id}")
    public MainMealDTO getMeal(@PathVariable("id") long id) {
        return modelMapper.map(mainMealService.getMealByID(id), MainMealDTO.class);
    }

    @PostMapping("/saveMeal")
    public void saveMeal(@RequestBody MainMealDTO meal) {
        mainMealService.saveMeal(modelMapper.map(meal, MainMeal.class));
    }

    @PostMapping("/deleteMeal{id}")
    public void deleteMeal(@PathVariable("id") long id) {
        mainMealService.deleteMeal(id);
    }

    @GetMapping("/getAllMenu")
    public List<MenuDTO> getAllMenu() {
        return menuService
                .getAll()
                .stream()
                .map(menu -> modelMapper.map(menu, MenuDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/getCurrent")
    public MenuDTO getCurrent() {
        Menu hej = menuRepository.findById(3);
        Date date = hej.getDate();

        return modelMapper.map(menuRepository.findByDate(date), MenuDTO.class);
    }

    @PostMapping("/updateMenu")
    public MenuDTO updateMenu(@RequestBody MenuDTO menu) {
        menuService.updateMenu(modelMapper.map(menu, Menu.class));

        Menu hej = menuRepository.findById(menu.id);
        return modelMapper.map(hej, MenuDTO.class);
    }

    @PostMapping("/saveMenu")
    public void saveMenu(@RequestBody MenuDTO menu) throws RestaurantFault {
        menuService.saveMenu(modelMapper.map(menu, Menu.class));
        System.out.println("ended saving");

        //Menu hej = menuRepository.findById(menu.id);
        //return modelMapper.map(hej, MenuDTO.class);
    }

    @GetMapping("/soupTest")
    public List<SoupDTO> getRq() {

        List<Menu> menuL = menuService.getAll();
        List<Soup> soups = soupService.getAllSoups();

        return soups.stream()
                .map(soup -> modelMapper.map(soup, SoupDTO.class))
                .collect(Collectors.toList());
    }*/
}

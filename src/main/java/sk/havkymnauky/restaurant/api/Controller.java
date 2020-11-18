package sk.havkymnauky.restaurant.api;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import sk.havkymnauky.restaurant.api.dto.MainMealDTO;
import sk.havkymnauky.restaurant.api.dto.MenuDTO;
import sk.havkymnauky.restaurant.api.dto.SoupDTO;
import sk.havkymnauky.restaurant.model.MainMeal;
import sk.havkymnauky.restaurant.model.Menu;
import sk.havkymnauky.restaurant.model.Soup;
import sk.havkymnauky.restaurant.service.IMainMealService;
import sk.havkymnauky.restaurant.service.IMenuService;
import sk.havkymnauky.restaurant.service.ISoupService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class Controller {

    private final IMainMealService mainMealService;
    private final IMenuService menuService;
    private final ISoupService soupService;
    private final ModelMapper modelMapper;

    public Controller(IMainMealService mainMealService, IMenuService menuService, ISoupService soupService, ModelMapper modelMapper) {
        this.mainMealService = mainMealService;
        this.menuService = menuService;
        this.soupService = soupService;
        this.modelMapper = modelMapper;
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

    @GetMapping("/soupTest")
    public List<SoupDTO> getRq() {

        List<Menu> menuL = menuService.getAllMenu();
        List<Soup> soups = soupService.getAllSoups();

        return soups.stream()
                .map(soup -> modelMapper.map(soup, SoupDTO.class))
                .collect(Collectors.toList());
    }
}

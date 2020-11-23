package sk.havkymnauky.restaurant.api.impl;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.havkymnauky.restaurant.api.IAdminController;
import sk.havkymnauky.restaurant.model.dto.MainMealDTO;
import sk.havkymnauky.restaurant.model.dto.MenuDTO;
import sk.havkymnauky.restaurant.model.dto.RestaurantResponse;
import sk.havkymnauky.restaurant.model.dto.SoupDTO;
import sk.havkymnauky.restaurant.error.RestaurantFault;
import sk.havkymnauky.restaurant.model.MainMeal;
import sk.havkymnauky.restaurant.model.Menu;
import sk.havkymnauky.restaurant.model.Soup;
import sk.havkymnauky.restaurant.service.IMainMealService;
import sk.havkymnauky.restaurant.service.IMenuService;
import sk.havkymnauky.restaurant.service.ISoupService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AdminController implements IAdminController {

    private final IMenuService menuService;
    private final ModelMapper modelMapper;
    private final IMainMealService mainMealService;
    private final ISoupService soupService;

    public AdminController(IMenuService menuService, ModelMapper modelMapper, IMainMealService mainMealService, ISoupService soupService) {
        this.menuService = menuService;
        this.modelMapper = modelMapper;
        this.mainMealService = mainMealService;
        this.soupService = soupService;
    }

    @Override
    public ResponseEntity<RestaurantResponse> addNewMenu(@RequestBody MenuDTO newMenu) throws RestaurantFault {
        menuService.saveMenu(modelMapper.map(newMenu, Menu.class));
        return new ResponseEntity<>( new RestaurantResponse("Menu bolo úspešne pridané!"), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<RestaurantResponse> saveNewMainMeal(@RequestBody MainMealDTO meal) {
        mainMealService.saveMeal(modelMapper.map(meal, MainMeal.class));
        return new ResponseEntity<>( new RestaurantResponse("Hlavné jedlo bolo úspešne pridané!"), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<RestaurantResponse> saveNewSoup(@RequestBody SoupDTO soup) {
        soupService.saveSoup(modelMapper.map(soup, Soup.class));
        return new ResponseEntity<>( new RestaurantResponse("Poliavka bola úspešne pridaná!"), HttpStatus.CREATED);
    }

    @Override
    public List<MenuDTO> getAllMenus() {
        return menuService
                .getAll()
                .stream()
                .map(menu -> modelMapper.map(menu, MenuDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<MainMealDTO> getAllMainMeals() {
            return mainMealService
                    .getAllMainMeals()
                    .stream()
                    .map(meal -> modelMapper.map(meal, MainMealDTO.class))
                    .collect(Collectors.toList());
    }

    @Override
    public List<SoupDTO> getAllSoups() {
        return soupService
                .getAll()
                .stream()
                .map(soup -> modelMapper.map(soup, SoupDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<RestaurantResponse> deleteMenu(@PathVariable("id") long id) {
        menuService.deleteMenu(id);
        return new ResponseEntity<>(new RestaurantResponse("Menu bolo úspešne vymazané!"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<RestaurantResponse> deleteMainMeal(@PathVariable("id") long id) {
        mainMealService.deleteMeal(id);
        return new ResponseEntity<>(new RestaurantResponse("Hlavné jedlo bolo úspešne vymazané!"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<RestaurantResponse> deleteSoup(@PathVariable("id") long id){
        soupService.deleteSoup(id);
        return new ResponseEntity<>(new RestaurantResponse("Poliavka bola úspešne vymazaná!"), HttpStatus.OK);
    }
}

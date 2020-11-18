package sk.havkymnauky.restaurant.api;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.havkymnauky.restaurant.api.dto.MenuDTO;
import sk.havkymnauky.restaurant.api.dto.RestaurantResponse;
import sk.havkymnauky.restaurant.error.RestaurantFault;
import sk.havkymnauky.restaurant.model.Menu;
import sk.havkymnauky.restaurant.service.IMenuService;

@RestController
@RequestMapping( "/secured" )
public class AdminController {

    private final IMenuService menuService;
    private final ModelMapper modelMapper;

    public AdminController(IMenuService menuService, ModelMapper modelMapper) {
        this.menuService = menuService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/createMenu")
    public ResponseEntity<RestaurantResponse> addNewMenu(@RequestBody MenuDTO newMenu) throws RestaurantFault {
        menuService.saveMenu(modelMapper.map(newMenu, Menu.class));
        return new ResponseEntity<>( new RestaurantResponse("Menu bolo úspešne pridané!"), HttpStatus.CREATED);
    }

    @PutMapping("/updateMenu")
    public ResponseEntity<RestaurantResponse> updateMenu(@RequestBody MenuDTO menuToUpdate) {
        menuService.updateMenu(modelMapper.map(menuToUpdate, Menu.class));
        return new ResponseEntity<>(new RestaurantResponse("Menu bolo úspešne upravené!"),HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/deleteMenu/{id}")
    @ResponseBody
    public ResponseEntity<RestaurantResponse> deleteMenu(@PathVariable("id") long id) throws RestaurantFault {
        menuService.deleteMenu(id);
        return new ResponseEntity<>(new RestaurantResponse("Menu bolo úspešne vymazané!"), HttpStatus.OK);
    }
}

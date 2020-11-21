package sk.havkymnauky.restaurant.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.havkymnauky.restaurant.api.dto.MainMealDTO;
import sk.havkymnauky.restaurant.api.dto.MenuDTO;
import sk.havkymnauky.restaurant.api.dto.RestaurantResponse;
import sk.havkymnauky.restaurant.api.dto.SoupDTO;
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
@RequestMapping( "/secured" )
@ApiResponses( value =
    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = RestaurantFault.class)))
)
public class AdminController {

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

    @PostMapping("/saveMenu")
    @Operation(summary = "Save or update menu", description = "In a case that menu in parameter has ID = 0 it will be saved as new one. Otherwise it will be updated by given ID",
               responses = {@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ResponseEntity.class)))})
    public ResponseEntity<RestaurantResponse> addNewMenu(@Parameter(description = "menu entity body", name = "Menu", required = true)
                                                         @RequestBody MenuDTO newMenu) throws RestaurantFault {
        menuService.saveMenu(modelMapper.map(newMenu, Menu.class));
        return new ResponseEntity<>( new RestaurantResponse("Menu bolo úspešne pridané!"), HttpStatus.CREATED);
    }

    @PostMapping("/saveMainMeal")
    @Operation(summary = "Save or update main meal", description = "In a case that main meal in parameter has ID = 0 it will be saved as new one. Otherwise it will be updated by given ID",
               responses = {@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ResponseEntity.class)))})
    public ResponseEntity<RestaurantResponse> saveNewMainMeal(@Parameter(description = "main meal entity body", name = "Main meal", required = true)
                                                              @RequestBody MainMealDTO meal) {
        mainMealService.saveMeal(modelMapper.map(meal, MainMeal.class));
        return new ResponseEntity<>( new RestaurantResponse("Hlavné jedlo bolo úspešne pridané!"), HttpStatus.CREATED);
    }

    @PostMapping("/saveSoup")
    @Operation(summary = "Save or update soup", description = "In a case that soup in parameter has ID = 0 it will be saved as new one. Otherwise it will be updated by given ID",
               responses = {@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ResponseEntity.class)))})
    public ResponseEntity<RestaurantResponse> saveNewSoup(@Parameter(description = "soup entity body", name = "Soup", required = true)
                                                          @RequestBody SoupDTO soup) {
        soupService.saveNewSoup(modelMapper.map(soup, Soup.class));
        return new ResponseEntity<>( new RestaurantResponse("Poliavka bola úspešne pridaná!"), HttpStatus.CREATED);
    }

    @GetMapping("/getAllMenus")
    @Operation(summary = "Get all saved menus", description = "Returns a list of menus and their meals which are stored in a database",
               responses = {@ApiResponse(responseCode = "200", description = "OK", content = @Content(array = @ArraySchema(schema = @Schema(implementation = MenuDTO.class))))})
    public List<MenuDTO> getAllMenus() {
        return menuService
                .getAll()
                .stream()
                .map(menu -> modelMapper.map(menu, MenuDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/getAllMainMeals")
    @Operation(summary = "Get all saved main meals", description = "Returns a list of all main meals which are stored in a database",
               responses = {@ApiResponse(responseCode = "200", description = "OK", content = @Content(array = @ArraySchema(schema = @Schema(implementation = MainMealDTO.class))))})
    public List<MainMealDTO> getAllMainMeals() {
            return mainMealService
                    .getAllMainMeals()
                    .stream()
                    .map(meal -> modelMapper.map(meal, MainMealDTO.class))
                    .collect(Collectors.toList());
    }

    @GetMapping("/getAllSoups")
    @Operation(summary = "Get all saved soups", description = "Returns a list of all soups which are stored in a database",
               responses = {@ApiResponse(responseCode = "200", description = "OK", content = @Content(array = @ArraySchema(schema = @Schema(implementation = SoupDTO.class))))})
    public List<SoupDTO> getAllSoups() {
        return menuService
                .getAll()
                .stream()
                .map(soup -> modelMapper.map(soup, SoupDTO.class))
                .collect(Collectors.toList());
    }

    @DeleteMapping("/deleteMenu/{id}")
    @Operation(summary = "Delete menu by ID", description = "Deletes menu if there exits menu with given ID",
               responses = {@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ResponseEntity.class)))})
    public ResponseEntity<RestaurantResponse> deleteMenu(@Parameter(description = "identifier of menu which will be deleted", name = "Menu ID", required = true)
                                                         @PathVariable("id") long id) throws RestaurantFault {
        menuService.deleteMenu(id);
        return new ResponseEntity<>(new RestaurantResponse("Menu bolo úspešne vymazané!"), HttpStatus.OK);
    }

    @DeleteMapping("/deleteMainMeal/{id}")
    @Operation(summary = "Delete main meal by ID", description = "Deletes main meal if there exits main meal with given ID",
               responses = {@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ResponseEntity.class)))})
    public ResponseEntity<RestaurantResponse> deleteMainMeal(@Parameter(description = "identifier of main meal which will be deleted", name = "Main meal ID", required = true)
                                                             @PathVariable("id") long id){
        menuService.deleteMenu(id);
        return new ResponseEntity<>(new RestaurantResponse("Hlavné jedlo bolo úspešne vymazané!"), HttpStatus.OK);
    }

    @DeleteMapping("/deleteSoup/{id}")
    @Operation(summary = "Delete soup by ID", description = "Deletes soup if there exits soup with given ID",
               responses = {@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ResponseEntity.class)))})
    public ResponseEntity<RestaurantResponse> deleteSoup(@Parameter(description = "identifier of menu which will be deleted", name = "Soup ID", required = true)
                                                         @PathVariable("id") long id){
        menuService.deleteMenu(id);
        return new ResponseEntity<>(new RestaurantResponse("Poliavka bola úspešne vymazaná!"), HttpStatus.OK);
    }
}

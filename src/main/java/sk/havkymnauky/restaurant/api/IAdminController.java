package sk.havkymnauky.restaurant.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.havkymnauky.restaurant.model.dto.MainMealDTO;
import sk.havkymnauky.restaurant.model.dto.MenuDTO;
import sk.havkymnauky.restaurant.model.dto.RestaurantResponse;
import sk.havkymnauky.restaurant.model.dto.SoupDTO;
import sk.havkymnauky.restaurant.error.RestaurantFault;

import java.util.List;

@RequestMapping( "/secured" )
@ApiResponses( value =
    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = RestaurantFault.class)))
)
public interface IAdminController {

    @PostMapping("/saveMenu")
    @Operation(summary = "Save or update menu", description = "In a case that menu in parameter has ID = 0 it will be saved as new one. Otherwise it will be updated by given ID",
            responses = {@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ResponseEntity.class)))})
    ResponseEntity<RestaurantResponse> addNewMenu(@Parameter(description = "menu entity body", name = "Menu", required = true)
                                                  @RequestBody MenuDTO newMenu) throws RestaurantFault;

    @PostMapping("/saveMainMeal")
    @Operation(summary = "Save or update main meal", description = "In a case that main meal in parameter has ID = 0 it will be saved as new one. Otherwise it will be updated by given ID",
            responses = {@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ResponseEntity.class)))})
    public ResponseEntity<RestaurantResponse> saveNewMainMeal(@Parameter(description = "main meal entity body", name = "Main meal", required = true)
                                                              @RequestBody MainMealDTO meal);

    @PostMapping("/saveSoup")
    @Operation(summary = "Save or update soup", description = "In a case that soup in parameter has ID = 0 it will be saved as new one. Otherwise it will be updated by given ID",
            responses = {@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ResponseEntity.class)))})
    public ResponseEntity<RestaurantResponse> saveNewSoup(@Parameter(description = "soup entity body", name = "Soup", required = true)
                                                          @RequestBody SoupDTO soup);

    @GetMapping("/getAllMenus")
    @Operation(summary = "Get all saved menus", description = "Returns a list of menus and their meals which are stored in a database",
            responses = {@ApiResponse(responseCode = "200", description = "OK", content = @Content(array = @ArraySchema(schema = @Schema(implementation = MenuDTO.class))))})
    public List<MenuDTO> getAllMenus();

    @GetMapping("/getAllSoups")
    @Operation(summary = "Get all saved soups", description = "Returns a list of all soups which are stored in a database",
            responses = {@ApiResponse(responseCode = "200", description = "OK", content = @Content(array = @ArraySchema(schema = @Schema(implementation = SoupDTO.class))))})
    public List<SoupDTO> getAllSoups();

    @GetMapping("/getAllMainMeals")
    @Operation(summary = "Get all saved main meals", description = "Returns a list of all main meals which are stored in a database",
            responses = {@ApiResponse(responseCode = "200", description = "OK", content = @Content(array = @ArraySchema(schema = @Schema(implementation = MainMealDTO.class))))})
    public List<MainMealDTO> getAllMainMeals();

    @DeleteMapping("/deleteMenu/{id}")
    @Operation(summary = "Delete menu by ID", description = "Deletes menu if there exits menu with given ID",
            responses = {@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ResponseEntity.class)))})
    public ResponseEntity<RestaurantResponse> deleteMenu(@Parameter(description = "identifier of menu which will be deleted", name = "Menu ID", required = true)
                                                         @PathVariable("id") long id);


    @DeleteMapping("/deleteMainMeal/{id}")
    @Operation(summary = "Delete main meal by ID", description = "Deletes main meal if there exits main meal with given ID",
            responses = {@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ResponseEntity.class)))})
    public ResponseEntity<RestaurantResponse> deleteMainMeal(@Parameter(description = "identifier of main meal which will be deleted", name = "Main meal ID", required = true)
                                                             @PathVariable("id") long id);

    @DeleteMapping("/deleteSoup/{id}")
    @Operation(summary = "Delete soup by ID", description = "Deletes soup if there exits soup with given ID",
            responses = {@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ResponseEntity.class)))})
    public ResponseEntity<RestaurantResponse> deleteSoup(@Parameter(description = "identifier of menu which will be deleted", name = "Soup ID", required = true)
                                                         @PathVariable("id") long id);

}

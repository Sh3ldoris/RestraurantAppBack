package sk.havkymnauky.restaurant.api;

import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.havkymnauky.restaurant.api.dto.MenuDTO;
import sk.havkymnauky.restaurant.service.IMenuService;

@RestController
@RequestMapping( "/public" )
public class CustomerController {

    private final ModelMapper modelMapper;
    private final IMenuService menuService;

    public CustomerController(ModelMapper modelMapper, IMenuService menuService) {
        this.modelMapper = modelMapper;
        this.menuService = menuService;
    }

    @GetMapping( "/getCurrentMenu" )
    @Operation(summary = "Get menu with current date", description = "Method returns menu which has the latest date")
    public MenuDTO getCurrentMenu() {
        return modelMapper.map(menuService.getCurrent(), MenuDTO.class);
    }
}

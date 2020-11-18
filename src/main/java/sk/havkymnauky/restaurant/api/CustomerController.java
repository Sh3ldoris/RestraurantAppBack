package sk.havkymnauky.restaurant.api;

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
    public MenuDTO getCurrentMenu() {
        return modelMapper.map(menuService.getCurrentMenu(), MenuDTO.class);
    }
}

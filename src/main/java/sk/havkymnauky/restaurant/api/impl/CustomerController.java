package sk.havkymnauky.restaurant.api.impl;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.RestController;
import sk.havkymnauky.restaurant.api.ICustomerController;
import sk.havkymnauky.restaurant.model.dto.MenuDTO;
import sk.havkymnauky.restaurant.service.IMenuService;

@RestController
public class CustomerController implements ICustomerController {

    private final ModelMapper modelMapper;
    private final IMenuService menuService;

    public CustomerController(ModelMapper modelMapper, IMenuService menuService) {
        this.modelMapper = modelMapper;
        this.menuService = menuService;
    }

    @Override
    public MenuDTO getCurrentMenu() {
        return modelMapper.map(menuService.getCurrent(), MenuDTO.class);
    }
}

package sk.havkymnauky.restaurant.api;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sk.havkymnauky.restaurant.model.dto.MenuDTO;

@RequestMapping( "api/public" )
public interface ICustomerController {

    @GetMapping("/getCurrentMenu")
    @Operation(summary = "Get menu with current date", description = "Method returns menu which has the latest date")
    MenuDTO getCurrentMenu();
}

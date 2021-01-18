package sk.havkymnauky.restaurant.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import sk.havkymnauky.restaurant.error.RestaurantFault;
import sk.havkymnauky.restaurant.model.dto.JwtResponse;
import sk.havkymnauky.restaurant.model.dto.LoginFormDTO;

@RequestMapping( "api/auth" )
@ApiResponses( value =
@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = RestaurantFault.class)))
)
public interface IAuthController {

    @PostMapping("/signin")
    @Operation(summary = "Save or update menu", description = "In a case that menu in parameter has ID = 0 it will be saved as new one. Otherwise it will be updated by given ID",
            responses = {@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ResponseEntity.class)))})
    ResponseEntity<JwtResponse> authenticateUser(@RequestBody LoginFormDTO loginForm);

}

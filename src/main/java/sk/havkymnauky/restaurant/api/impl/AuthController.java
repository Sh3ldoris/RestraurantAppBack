package sk.havkymnauky.restaurant.api.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;
import sk.havkymnauky.restaurant.api.IAuthController;
import sk.havkymnauky.restaurant.model.Role;
import sk.havkymnauky.restaurant.model.User;
import sk.havkymnauky.restaurant.model.dto.JwtResponse;
import sk.havkymnauky.restaurant.model.dto.LoginFormDTO;
import sk.havkymnauky.restaurant.repository.IUserRepository;
import sk.havkymnauky.restaurant.security.jwt.JwtService;

import java.util.concurrent.TimeUnit;

@RestController
public class AuthController implements IAuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    @Qualifier("JDBCPostgresqlUserRepo")
    private IUserRepository userRepository;


    @Override
    public ResponseEntity<JwtResponse> authenticateUser(LoginFormDTO loginForm) {

        Authentication authentication =  authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginForm.username, loginForm.password)
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtService.generateJwtToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user =  userRepository.getByUsername(userDetails.getUsername());

        /*try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
            System.err.println(e);
        }*/

        return new ResponseEntity<JwtResponse>(new JwtResponse(jwt, userDetails.getUsername(), "admin"), HttpStatus.OK);
    }
}

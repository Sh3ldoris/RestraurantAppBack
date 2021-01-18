package sk.havkymnauky.restaurant.model.dto;

public class JwtResponse {
    public String token;
    public String username;
    public String role;

    public JwtResponse(String token, String username, String role) {
        this.token = token;
        this.username = username;
        this.role = role;
    }
}

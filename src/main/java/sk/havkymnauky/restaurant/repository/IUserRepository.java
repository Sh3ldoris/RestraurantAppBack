package sk.havkymnauky.restaurant.repository;

import sk.havkymnauky.restaurant.model.User;

import java.util.List;

public interface IUserRepository {
    User getByUsername(String username);
    void saveUser(User user);
    List<User> getAll();
}

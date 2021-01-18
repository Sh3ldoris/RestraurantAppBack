package sk.havkymnauky.restaurant.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import sk.havkymnauky.restaurant.model.Role;
import sk.havkymnauky.restaurant.model.User;
import sk.havkymnauky.restaurant.utils.DBProperties;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int i) throws SQLException {
        return new User(
                rs.getLong(DBProperties.USER_ID),
                rs.getString(DBProperties.USER_NAME),
                rs.getString(DBProperties.USER_PASSWORD),
                Role.ADMIN);
    }
}

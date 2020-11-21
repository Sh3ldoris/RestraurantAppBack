package sk.havkymnauky.restaurant.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import sk.havkymnauky.restaurant.utils.DBProperties;
import sk.havkymnauky.restaurant.model.Menu;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MenuMapper implements RowMapper<Menu> {

    @Override
    public Menu mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Menu(
                resultSet.getLong(DBProperties.MENU_ID),
                resultSet.getDate(DBProperties.MENU_DATE)
        );
    }
}

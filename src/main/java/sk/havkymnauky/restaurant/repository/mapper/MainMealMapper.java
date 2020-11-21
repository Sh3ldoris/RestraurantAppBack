package sk.havkymnauky.restaurant.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import sk.havkymnauky.restaurant.utils.DBProperties;
import sk.havkymnauky.restaurant.model.MainMeal;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MainMealMapper implements RowMapper<MainMeal> {
    @Override
    public MainMeal mapRow(ResultSet rs, int i) throws SQLException {
        return new MainMeal(
                rs.getLong(DBProperties.MAIN_MEAL_ID),
                rs.getString(DBProperties.MAIN_MEAL_NAME),
                rs.getDouble(DBProperties.MAIN_MEAL_PRICE)
        );
    }
}

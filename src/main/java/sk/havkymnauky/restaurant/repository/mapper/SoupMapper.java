package sk.havkymnauky.restaurant.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import sk.havkymnauky.restaurant.model.DBProperties;
import sk.havkymnauky.restaurant.model.Soup;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SoupMapper implements RowMapper<Soup> {

    @Override
    public Soup mapRow(ResultSet rs, int i) throws SQLException {
        return new Soup(
                rs.getLong(DBProperties.SOUP_ID),
                rs.getString(DBProperties.SOUP_NAME),
                rs.getDouble(DBProperties.SOUP_PRICE)
        );
    }
}

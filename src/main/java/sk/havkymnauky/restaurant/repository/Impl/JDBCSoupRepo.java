package sk.havkymnauky.restaurant.repository.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import sk.havkymnauky.restaurant.utils.DBProperties;
import sk.havkymnauky.restaurant.model.Soup;
import sk.havkymnauky.restaurant.repository.ISoupRepository;
import sk.havkymnauky.restaurant.repository.mapper.SoupMapper;

import java.util.List;

@Repository("JDBCPostgresqlSoupRepo")
public class JDBCSoupRepo implements ISoupRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private RowMapper<Soup> soupMapper = new SoupMapper();

    @Override
    public List<Soup> findAll() {
        String sql = "select * from " + DBProperties.SOUP;
        List<Soup> soups = jdbcTemplate.query(sql, soupMapper);
        return soups;
    }

    @Override
    public Soup findById(long id) {
        String sql = String.format("select * from %s where %s=?", DBProperties.SOUP, DBProperties.SOUP_ID);
        List<Soup> soups = jdbcTemplate.query(sql, soupMapper, new Object[] { id });
        return soups.get(0);
    }

    @Override
    public void save(Soup soup) {

        if (soup != null)
            return;

        if (soup.getID() != 0) {
            updateSoup(soup);
        } else {
            String sql = String.format("insert into %s(%s, %s) values(?,?)", DBProperties.SOUP, DBProperties.SOUP_NAME, DBProperties.SOUP_PRICE);
            jdbcTemplate.update(sql, new Object[] { soup.getName(), soup.getPrice() });

        }
    }

    @Override
    public void deleteSoup(long id) {
        String sql = String.format("delete from %s where %s=?", DBProperties.SOUP, DBProperties.SOUP_ID);
        jdbcTemplate.update(sql, new Object[] { id });
    }

    private void updateSoup(Soup soup) {
        String sql = String.format("update %s set %s=?, %s=? where %s=?", DBProperties.SOUP, DBProperties.SOUP_NAME, DBProperties.SOUP_PRICE, DBProperties.SOUP_ID);
        jdbcTemplate.update(sql, new Object[] { soup.getName(), soup.getPrice(), soup.getID()});
    }
}

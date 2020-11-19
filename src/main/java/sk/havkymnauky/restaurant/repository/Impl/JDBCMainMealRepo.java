package sk.havkymnauky.restaurant.repository.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import sk.havkymnauky.restaurant.model.DBProperties;
import sk.havkymnauky.restaurant.model.MainMeal;
import sk.havkymnauky.restaurant.repository.IMainMealRepository;
import sk.havkymnauky.restaurant.repository.mapper.MainMealMapper;

import java.util.List;

@Repository("JDBCPostgresqlMainMealRepo")
public class JDBCMainMealRepo implements IMainMealRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private RowMapper<MainMeal> mainMealMapper = new MainMealMapper();

    @Override
    public List<MainMeal> findAll() {
        String sql = "select * from " + DBProperties.MAIN_MEAL;
        List<MainMeal> meals = jdbcTemplate.query(sql, mainMealMapper);
        return meals;

    }

    @Override
    public MainMeal findById(long id) {
        String sql = String.format("select * from %s where %s=?", DBProperties.MAIN_MEAL, DBProperties.MAIN_MEAL_ID);
        List<MainMeal> meal = jdbcTemplate.query(sql, mainMealMapper, new Object[] { id });
        return meal.get(0);
    }

    @Override
    public void save(MainMeal mainMeal) {
        if (mainMeal != null) {
            if (mainMeal.getId() != 0) {
                updateMeal(mainMeal);
            } else {
                String sql = String.format("insert into %s(%s, %s) values(?,?)", DBProperties.MAIN_MEAL, DBProperties.MAIN_MEAL_NAME, DBProperties.MAIN_MEAL_PRICE);
                jdbcTemplate.update(sql, new Object[] { mainMeal.getName(), mainMeal.getPrice() });
            }
        }
    }

    @Override
    public void deleteMeal(long id) {
        String sql = String.format("delete from %s where %s=?", DBProperties.MAIN_MEAL, DBProperties.MAIN_MEAL_ID);
        jdbcTemplate.update(sql, new Object[] { id });
    }

    private void updateMeal(MainMeal meal) {
        String sql = String.format("update %s set %s=?, %s=? where %s=?", DBProperties.MAIN_MEAL, DBProperties.MAIN_MEAL_NAME, DBProperties.MAIN_MEAL_PRICE, DBProperties.MAIN_MEAL_ID);
        jdbcTemplate.update(sql, new Object[] { meal.getName(), meal.getPrice(), meal.getId()});
    }
}

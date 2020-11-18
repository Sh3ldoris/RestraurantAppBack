package sk.havkymnauky.restaurant.repository.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import sk.havkymnauky.restaurant.model.MainMeal;
import sk.havkymnauky.restaurant.repository.IMainMealRepository;

import java.util.List;

@Repository("JDBCPostgresqlRepo")
public class JDBCMainMealRepo implements IMainMealRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<MainMeal> findAll() {
        return null;
    }

    @Override
    public MainMeal findById(long id) {
        return null;
    }

    @Override
    public void save(MainMeal mainMeal) {

    }

    @Override
    public void deleteMeal(long id) {

    }
}

package sk.havkymnauky.restaurant.repository.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import sk.havkymnauky.restaurant.utils.DBProperties;
import sk.havkymnauky.restaurant.model.*;
import sk.havkymnauky.restaurant.repository.IMenuRepository;
import sk.havkymnauky.restaurant.repository.mapper.*;

import java.util.Date;
import java.util.List;

@Repository("JDBCMenuRepo")
public class JDBCMenuRepo implements IMenuRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private RowMapper<Menu> menuRowMapper = new MenuMapper();
    private RowMapper<MainMeal> mainMealMapper = new MainMealMapper();
    private RowMapper<Soup> soupMapper = new SoupMapper();

    @Override
    public List<Menu> findAll() {
        String sql = "select * from " + DBProperties.MENU + " order by " + DBProperties.MENU_DATE + " desc";
        List<Menu> founded = jdbcTemplate.query(sql, menuRowMapper);

        for (int i = 0; i < founded.size(); i++) {
            founded.get(i).setSoups(findAllSoupsForMenu(founded.get(i).getId()));
            founded.get(i).setMainMeals(findAllMainMealsForMenu(founded.get(i).getId()));
        }

        return founded;
    }

    @Override
    public Menu findById(long id) {
        String sql = String.format("select * from %s where %s=?", DBProperties.MENU, DBProperties.MENU_ID);
        List<Menu> list = jdbcTemplate.query(sql, menuRowMapper, new Object[] { id });

        if (list.size() == 0)
            return null;

        Menu founded = list.get(0);
        founded.setSoups(findAllSoupsForMenu(founded.getId()));
        founded.setMainMeals(findAllMainMealsForMenu(founded.getId()));
        return founded;
    }

    @Override
    public Menu findCurrent() {
        /*String sql = String.format(
                "select m1.%s, m1.%s from %s m1\n" +
                                        "join (\n" +
                                            "SELECT max(%s) as maxdate\n" +
                                            "FROM   %s\n" +
                                        ") m2\n" +
                                    "on m1.%s = m2.maxdate",
                DBProperties.MENU_ID,
                DBProperties.MENU_DATE,
                DBProperties.MENU,
                DBProperties.MENU_DATE,
                DBProperties.MENU,
                DBProperties.MENU_DATE
                );*/
        String sql = String.format("select * from %s where %s = current_date;", DBProperties.MENU, DBProperties.MENU_DATE);
        List<Menu> list = jdbcTemplate.query(sql, menuRowMapper);

        if (list.size() == 0)
            return null;

        Menu founded = list.get(0);
        founded.setSoups(findAllSoupsForMenu(founded.getId()));
        founded.setMainMeals(findAllMainMealsForMenu(founded.getId()));
        return founded;
    }

    @Override
    public void saveMenu(Menu menu) {
        if (menu == null)
            return;

        if (menu.getId() != 0) {
            updateMenu(menu);
        } else {
            String sql = String.format("insert into %s(%s) values(?)", DBProperties.MENU, DBProperties.MENU_DATE);
            jdbcTemplate.update(sql, new Object[] { menu.getDate() });

            //Set new ID that is also in DB
            menu.setId(this.findByDate(menu.getDate()).getId());

            saveSoupsToMenu(menu);
            saveMainMealsToMenu(menu);
        }

    }

    @Override
    public void deleteMenu(long id) {
        deleteAllSoupsInMenu(id);
        deleteAllMainMealsInMenu(id);
        String sql = String.format("delete from %s where %s=?", DBProperties.MENU, DBProperties.MENU_ID);
        jdbcTemplate.update(sql, new Object[] { id });
    }

    @Override
    public Menu findByDate(Date date) {
        String sql = String.format("SELECT * from %s where %s=?", DBProperties.MENU, DBProperties.MENU_DATE);
        List<Menu> founded = jdbcTemplate.query(sql, menuRowMapper, new Object[] { date });
        return founded.size() != 0 ? founded.get(0) : null;
    }

    private void updateMenu(Menu menu) {
        deleteAllSoupsInMenu(menu.getId());
        deleteAllMainMealsInMenu(menu.getId());
        String sql = String.format("update %s set %s=? where %s=?", DBProperties.MENU, DBProperties.MENU_DATE, DBProperties.MENU_ID);
        jdbcTemplate.update(sql, new Object[] { menu.getDate(), menu.getId() });
        saveSoupsToMenu(menu);
        saveMainMealsToMenu(menu);
    }

    private List<Soup> findAllSoupsForMenu(long id) {
        String sql = String.format(
                "select mm.* from %s t1\n" +
                        "join %s mm on mm.%s = t1.%s\n" +
                        "where %s=?",
                DBProperties.MENU_SOUP,
                DBProperties.SOUP,
                DBProperties.SOUP_ID,
                DBProperties.SOUP_ID,
                DBProperties.MENU_ID
        );
        List<Soup> soups = jdbcTemplate.query(sql, soupMapper, new Object[] { id });
        return soups;
    }

    private List<MainMeal> findAllMainMealsForMenu(long id) {
        String sql = String.format(
                "select mm.* from %s t1\n" +
                        "join %s mm on mm.%s = t1.%s\n" +
                        "where %s=?",
                DBProperties.MENU_MAIN_MEAL,
                DBProperties.MAIN_MEAL,
                DBProperties.MAIN_MEAL_ID,
                DBProperties.MAIN_MEAL_ID,
                DBProperties.MENU_ID
                );
        List<MainMeal> meals = jdbcTemplate.query(sql, mainMealMapper, new Object[] { id });
        return meals;
    }

    private void saveSoupsToMenu(Menu menu) {
        if (menu.getSoups().size() == 0)
            return;

        String sql = String.format("insert  into %s values (?,?)", DBProperties.MENU_SOUP);
        for (Soup soup : menu.getSoups()) {
            jdbcTemplate.update(sql, new Object[] {soup.getID(), menu.getId()});
        }
    }

    private void saveMainMealsToMenu(Menu menu) {
        if (menu.getMainMeals().size() == 0)
            return;

        String sql = String.format("insert into %s values (?,?)", DBProperties.MENU_MAIN_MEAL);
        for (MainMeal meal : menu.getMainMeals()) {
            jdbcTemplate.update(sql, new Object[] {meal.getId(), menu.getId()});
        }
    }

    private void deleteAllMainMealsInMenu(long id) {
        String sql = String.format("delete from %s where %s=?",DBProperties.MENU_MAIN_MEAL, DBProperties.MENU_ID);
        jdbcTemplate.update(sql, new Object[] { id });
    }

    private void deleteAllSoupsInMenu(long id) {
        String sql = String.format("delete from %s where %s=?",DBProperties.MENU_SOUP, DBProperties.MENU_ID);
        jdbcTemplate.update(sql, new Object[] { id });
    }
}

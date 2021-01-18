package sk.havkymnauky.restaurant.repository.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import sk.havkymnauky.restaurant.model.Soup;
import sk.havkymnauky.restaurant.model.User;
import sk.havkymnauky.restaurant.repository.IUserRepository;
import sk.havkymnauky.restaurant.repository.mapper.SoupMapper;
import sk.havkymnauky.restaurant.repository.mapper.UserMapper;
import sk.havkymnauky.restaurant.utils.DBProperties;

import java.util.List;

@Repository("JDBCPostgresqlUserRepo")
public class JDBCUserRepo implements IUserRepository {

    private User user;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private RowMapper<User> userMapper = new UserMapper();

    @Override
    public User getByUsername(String username) {
        String sql = String.format("select * from %s where %s=?", DBProperties.USER, DBProperties.USER_NAME);
        List<User> users = jdbcTemplate.query(sql, this.userMapper, new Object[] { username });
        return users.get(0);
    }

    @Override
    public void saveUser(User user) {
        String sql = String.format("insert into %s(%s, %s, %s) values (?,?,?)", DBProperties.USER, DBProperties.USER_NAME, DBProperties.USER_PASSWORD, DBProperties.USER_ROLE);
        jdbcTemplate.update(sql, new Object[] { user.getUsername(), user.getPassword(), user.getRole().label });
    }

    @Override
    public List<User> getAll() {
        String sql = String.format("select * from %s", DBProperties.USER);
        List<User> users = jdbcTemplate.query(sql, this.userMapper);
        return users;
    }
}

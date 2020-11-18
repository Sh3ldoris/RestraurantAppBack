package sk.havkymnauky.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sk.havkymnauky.restaurant.model.Menu;

import java.util.List;

@Repository
public interface IMenuRepository extends JpaRepository<Menu, Long> {

    /**
     * @return last Menu record in database
     */
    Menu findTopByOrderByMenuDateDesc();
    //List<Menu> findAllBy
}

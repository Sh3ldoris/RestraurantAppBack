package sk.havkymnauky.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sk.havkymnauky.restaurant.model.MainMeal;

import java.util.List;


public interface IMainMealRepository {
    List<MainMeal> findAll();
    MainMeal findById(long id);
    void save(MainMeal mainMeal);
    void deleteMeal(long id);
}

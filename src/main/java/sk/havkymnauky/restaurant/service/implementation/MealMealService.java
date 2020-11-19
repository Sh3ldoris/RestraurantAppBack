package sk.havkymnauky.restaurant.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import sk.havkymnauky.restaurant.model.MainMeal;
import sk.havkymnauky.restaurant.repository.IMainMealRepository;
import sk.havkymnauky.restaurant.service.IMainMealService;

import java.util.List;

@Service
public class MealMealService implements IMainMealService {

    @Autowired
    @Qualifier("JDBCPostgresqlMainMealRepo")
    private IMainMealRepository mainMealRepository;


    @Override
    public List<MainMeal> getAllMainMeals() {
        return mainMealRepository.findAll();
    }

    @Override
    public MainMeal getMealByID(long id) {
        return mainMealRepository.findById(id);
    }

    @Override
    public void saveMeal(MainMeal newMeal) {
        mainMealRepository.save(newMeal);
    }

    @Override
    public void deleteMeal(long id) {
        mainMealRepository.deleteMeal(id);
    }


}

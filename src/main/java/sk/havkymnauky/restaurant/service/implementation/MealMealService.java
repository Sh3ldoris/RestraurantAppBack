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
    @Qualifier("JDBCPostgresqlRepo")
    private IMainMealRepository mainMealRepository;


    @Override
    public List<MainMeal> getAllMainMeals() {
        List<MainMeal> list = mainMealRepository.findAll();
        return list;
    }

    @Override
    public MainMeal getMealByID(long id) {
        return mainMealRepository.findById(id);
    }

    @Override
    public void saveNewMeal(MainMeal newMeal) {
        mainMealRepository.save(newMeal);
    }


}

package sk.havkymnauky.restaurant.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import sk.havkymnauky.restaurant.model.Soup;
import sk.havkymnauky.restaurant.repository.IMainMealRepository;
import sk.havkymnauky.restaurant.repository.ISoupRepository;
import sk.havkymnauky.restaurant.service.ISoupService;

import java.util.List;

@Service
public class SoupService implements ISoupService {

    @Autowired
    @Qualifier("JDBCPostgresqlSoupRepo")
    private ISoupRepository soupRepository;

    @Override
    public List<Soup> getAll() {
        return this.soupRepository.findAll();
    }

    @Override
    public Soup getByID(long id) {
        return soupRepository.findById(id);
    }

    @Override
    public void saveSoup(Soup newSoup) {
        soupRepository.save(newSoup);
    }

    @Override
    public void deleteSoup(long id) {
        soupRepository.deleteSoup(id);
    }

}

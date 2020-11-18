package sk.havkymnauky.restaurant.service.implementation;

import org.springframework.stereotype.Service;
import sk.havkymnauky.restaurant.model.Soup;
import sk.havkymnauky.restaurant.repository.IMainMealRepository;
import sk.havkymnauky.restaurant.repository.ISoupRepository;
import sk.havkymnauky.restaurant.service.ISoupService;

import java.util.List;

@Service
public class SoupService implements ISoupService {

    private ISoupRepository soupRepository;

    public SoupService(ISoupRepository soupRepository) {
        this.soupRepository = soupRepository;
    }

    @Override
    public List<Soup> getAllSoups() {
        return this.soupRepository.findAll();
    }

    @Override
    public Soup getSoupByID(long id) {
        return soupRepository.findById(id).orElse(null);
    }

    @Override
    public Soup saveNewSoup(Soup newSoup) {
        return soupRepository.save(newSoup);
    }

    @Override
    public void deleteAllSoups() {
        soupRepository.deleteAll();
    }
}
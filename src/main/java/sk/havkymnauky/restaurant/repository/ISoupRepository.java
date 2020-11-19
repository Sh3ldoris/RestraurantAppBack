package sk.havkymnauky.restaurant.repository;

import org.springframework.stereotype.Repository;
import sk.havkymnauky.restaurant.model.Soup;

import java.util.List;

public interface ISoupRepository {
    List<Soup> findAll();
    Soup findById(long id);
    void save(Soup soup);
    void deleteSoup(long id);
}

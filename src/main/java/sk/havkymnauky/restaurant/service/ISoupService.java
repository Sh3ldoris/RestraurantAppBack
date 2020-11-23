package sk.havkymnauky.restaurant.service;

import sk.havkymnauky.restaurant.model.Soup;

import java.util.List;

public interface ISoupService {

    List<Soup> getAll();
    Soup getByID(long id);
    void saveSoup(Soup newSoup);
    void deleteSoup(long id);
}

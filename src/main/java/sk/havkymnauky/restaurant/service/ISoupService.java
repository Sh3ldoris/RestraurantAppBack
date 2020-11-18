package sk.havkymnauky.restaurant.service;

import sk.havkymnauky.restaurant.model.Soup;

import java.util.List;

public interface ISoupService {

    List<Soup> getAllSoups();
    Soup getSoupByID(long id);
    Soup saveNewSoup(Soup newSoup);
    void deleteAllSoups();
}

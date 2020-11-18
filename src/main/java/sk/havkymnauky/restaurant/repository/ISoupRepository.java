package sk.havkymnauky.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sk.havkymnauky.restaurant.model.Soup;

@Repository
public interface ISoupRepository extends JpaRepository<Soup, Long> {
}

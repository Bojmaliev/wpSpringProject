package mk.trkalo.dnp.dnpshop.repository;

import mk.trkalo.dnp.dnpshop.model.address.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    boolean existsByName(String name);
}

package mk.trkalo.dnp.dnpshop.service;

import mk.trkalo.dnp.dnpshop.model.City;


public interface CityService {
    City save(City city);
    City findById(Long id);

    boolean existsByName(String name);
    void deleteAll();
}

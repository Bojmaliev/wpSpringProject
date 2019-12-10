package mk.trkalo.dnp.dnpshop.service;

import mk.trkalo.dnp.dnpshop.model.address.City;

import java.util.List;


public interface CityService {
    City save(City city);
    City findById(Long id);
    List<City> findAll();

    boolean existsByName(String name);
    void deleteAll();
}

package mk.trkalo.dnp.dnpshop.service.impl;

import mk.trkalo.dnp.dnpshop.model.address.City;
import mk.trkalo.dnp.dnpshop.repository.CityRepository;
import mk.trkalo.dnp.dnpshop.service.CityService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }


    @Override
    public City save(City city) {
        if(existsByName(city.name)) throw new RuntimeException("Веќе постои град со тоа име");
        return cityRepository.save(city);
    }

    @Override
    public City findById(Long id) {
        return cityRepository.findById(id).orElseThrow(()-> new RuntimeException("Градот не е пронајден."));
    }

    @Override
    public List<City> findAll() {
        return cityRepository.findAll();
    }


    @Override
    public boolean existsByName(String name) {
        return cityRepository.existsByName(name);
    }

    @Override
    public void deleteAll() {
        cityRepository.deleteAll();
    }
}

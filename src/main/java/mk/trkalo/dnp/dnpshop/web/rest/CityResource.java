package mk.trkalo.dnp.dnpshop.web.rest;

import mk.trkalo.dnp.dnpshop.model.City;
import mk.trkalo.dnp.dnpshop.model.User;
import mk.trkalo.dnp.dnpshop.service.CityService;
import mk.trkalo.dnp.dnpshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/cities", produces = MediaType.APPLICATION_JSON_VALUE)
public class CityResource {
    final private CityService cityService;

    @Autowired
    public CityResource(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping
    public List<City> findAll(){
        return cityService.findAll();
    }

}

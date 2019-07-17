package mk.trkalo.dnp.dnpshop.web.rest;


import mk.trkalo.dnp.dnpshop.model.ShippingMethod;
import mk.trkalo.dnp.dnpshop.model.Size;
import mk.trkalo.dnp.dnpshop.service.ShippingService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/shipping_methods", produces = MediaType.APPLICATION_JSON_VALUE)
public class ShippingResource {
    private final ShippingService shippingService;

    public ShippingResource(ShippingService shippingService) {
        this.shippingService = shippingService;
    }


    @GetMapping
    public List<ShippingMethod> findAll(){
        return shippingService.findAllActive();
    }


}

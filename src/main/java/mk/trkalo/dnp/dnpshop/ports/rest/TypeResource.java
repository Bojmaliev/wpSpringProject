package mk.trkalo.dnp.dnpshop.ports.rest;

import mk.trkalo.dnp.dnpshop.model.product.Type;
import mk.trkalo.dnp.dnpshop.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/types", produces = MediaType.APPLICATION_JSON_VALUE)
public class TypeResource {
    private final TypeService typeService;

    @Autowired
    public TypeResource(TypeService typeService) {
        this.typeService = typeService;
    }

    @GetMapping
    public List<Type> findAll(){
        return typeService.findAll();
    }
}

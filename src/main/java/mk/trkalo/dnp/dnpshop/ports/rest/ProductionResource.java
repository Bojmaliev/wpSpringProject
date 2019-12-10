package mk.trkalo.dnp.dnpshop.ports.rest;
import mk.trkalo.dnp.dnpshop.dto.ProductionDto;
import mk.trkalo.dnp.dnpshop.model.product.Production;
import mk.trkalo.dnp.dnpshop.service.ProductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/production", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductionResource {
    final private ProductionService productionService;

    @Autowired
    public ProductionResource(ProductionService productionService) {
        this.productionService = productionService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Production saveProduction(@RequestBody ProductionDto dto){
        return productionService.save(dto);
    }
}

package mk.trkalo.dnp.dnpshop.service;
import mk.trkalo.dnp.dnpshop.dto.ProductionDto;
import mk.trkalo.dnp.dnpshop.model.Production;

public interface ProductionService {
    Production save(ProductionDto dto);
    void deleteAll();

}

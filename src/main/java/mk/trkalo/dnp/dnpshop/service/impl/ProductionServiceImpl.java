package mk.trkalo.dnp.dnpshop.service.impl;

import mk.trkalo.dnp.dnpshop.dto.ProductionDto;
import mk.trkalo.dnp.dnpshop.model.product.Product;
import mk.trkalo.dnp.dnpshop.model.product.ProductVariant;
import mk.trkalo.dnp.dnpshop.model.product.Production;
import mk.trkalo.dnp.dnpshop.repository.ProductionRepository;
import mk.trkalo.dnp.dnpshop.service.ProductService;
import mk.trkalo.dnp.dnpshop.service.ProductionService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ProductionServiceImpl implements ProductionService {

    private final ProductionRepository productionRepository;

    private final ProductService productService;

    public ProductionServiceImpl(ProductionRepository productionRepository, ProductService productService) {
        this.productionRepository = productionRepository;
        this.productService = productService;
    }

    @Override
    @Transactional
    public Production save(ProductionDto dto){
        Product p = productService.findById(dto.productId);
        ProductVariant pv = p.getProductVariantById(dto.productVariantId);
        pv.addProduction(dto.quantity);

        return productionRepository.save(new Production(pv, dto.quantity));

    }

    @Override
    public void deleteAll() {
        productionRepository.deleteAll();
    }


}

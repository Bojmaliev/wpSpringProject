package mk.trkalo.dnp.dnpshop.service.impl;
import mk.trkalo.dnp.dnpshop.dto.ProductVariantDto;
import mk.trkalo.dnp.dnpshop.model.Product;
import mk.trkalo.dnp.dnpshop.model.ProductVariant;
import mk.trkalo.dnp.dnpshop.model.Size;
import mk.trkalo.dnp.dnpshop.model.Type;
import mk.trkalo.dnp.dnpshop.repository.ProductVarientRepository;
import mk.trkalo.dnp.dnpshop.service.ProductService;
import mk.trkalo.dnp.dnpshop.service.ProductVarientService;
import mk.trkalo.dnp.dnpshop.service.SizeService;
import mk.trkalo.dnp.dnpshop.service.TypeService;
import org.springframework.stereotype.Service;

@Service
public class ProductVarientServiceImpl implements ProductVarientService {

    private final ProductVarientRepository productVarientRepository;
    private final ProductService productService;
    private final SizeService sizeService;
    private final TypeService typeService;

    public ProductVarientServiceImpl(ProductVarientRepository productVarientRepository, ProductService productService, SizeService sizeService, TypeService typeService) {
        this.productVarientRepository = productVarientRepository;
        this.productService = productService;
        this.sizeService = sizeService;

        this.typeService = typeService;
    }

    @Override
    public ProductVariant saveProductVariant(ProductVariant p) {
        return productVarientRepository.save(p);
    }

    @Override
    public ProductVariant saveProductVariant(int productId, ProductVariantDto p) {
        Product product = productService.findById(productId);
        Size size = sizeService.findById(p.sizeId);
        Type type = typeService.findById(p.typeId);
        if(productVarientRepository.existsByProductAndSizeAndType(product, size, type)) throw new RuntimeException("Веќе постои таква комбинација");
        return productVarientRepository.save(new ProductVariant(product, size, type, p.price));

    }
}

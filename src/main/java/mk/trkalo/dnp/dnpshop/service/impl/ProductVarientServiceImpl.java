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
    public ProductVariant findById(int id) {
            return productVarientRepository.findById(id).orElseThrow(()-> new RuntimeException("Варијантата не постои."));

    }

    @Override
    public ProductVariant save(ProductVariant p) {
        return productVarientRepository.save(p);
    }

    @Override
    public ProductVariant save(int productId, ProductVariantDto p) {
        Product product = productService.findById(productId);
        Size size = sizeService.findById(p.sizeId);
        Type type = typeService.findById(p.typeId);
        if(productVarientRepository.existsByProductAndSizeAndType(product, size, type)) throw new RuntimeException("Веќе постои таква комбинација");
        return productVarientRepository.save(new ProductVariant(product, size, type, p.price, p.canOrder));

    }

    @Override
    public ProductVariant updateById(int id, ProductVariantDto p) {
        ProductVariant pv = findById(id);
        pv.setPrice(p.price);
        pv.setCanOrder(p.canOrder);
        return productVarientRepository.save(pv);
    }

    @Override
    public void deleteById(int index) {
        findById(index);
        productVarientRepository.deleteById(index);
    }
}

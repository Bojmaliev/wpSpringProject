package mk.trkalo.dnp.dnpshop.service.impl;

import mk.trkalo.dnp.dnpshop.dto.ProductVariantDto;
import mk.trkalo.dnp.dnpshop.model.Product;
import mk.trkalo.dnp.dnpshop.model.ProductVariant;
import mk.trkalo.dnp.dnpshop.model.Size;
import mk.trkalo.dnp.dnpshop.model.Type;
import mk.trkalo.dnp.dnpshop.repository.ProductRepository;
import mk.trkalo.dnp.dnpshop.repository.ProductVariantRepository;
import mk.trkalo.dnp.dnpshop.service.ProductService;
import mk.trkalo.dnp.dnpshop.service.SizeService;
import mk.trkalo.dnp.dnpshop.service.TypeService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final SizeService sizeService;
    private final TypeService typeService;
    private final ProductVariantRepository productVariantRepository;

    public ProductServiceImpl(ProductRepository productRepository, SizeService sizeService, TypeService typeService, ProductVariantRepository productVariantRepository) {
        this.productRepository = productRepository;
        this.sizeService = sizeService;
        this.typeService = typeService;

        this.productVariantRepository = productVariantRepository;
    }

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product saveProduct(Product p) {
        if(p.getName().length() < 4) throw new RuntimeException("Името на продуктот мора да има барем 4 карактери");
        if(productRepository.existsByName(p.getName()))throw new RuntimeException("Продукт со вакво име веќе постои.");
        return productRepository.save(p);
    }



    @Override
    public void deleteAllProducts() {
        productRepository.deleteAll();
    }

    @Override
    public void deleteById(Long id) {
        Product p = findById(id);
        if(p.getProductVariants().size() != 0) throw new RuntimeException("Избришете ги прво варијантите");
        productRepository.deleteById(id);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(()-> new RuntimeException("Продуктот не постои."));
    }

    @Override
    public Product updateById(Product product, Long id) {
        if(productRepository.existsProductByNameAndIdNot(product.getName(), id)) throw new RuntimeException("Веќе постои продукт со тоа име");
        Product existing = findById(id);
        existing.setDescription(product.getDescription());
        existing.setName(product.getName());
        return productRepository.save(existing);
    }

    @Override
    public ProductVariant findProductVariantById(Long id) {
        return productVariantRepository.findById(id).orElseThrow(()-> new RuntimeException("Варијантата на продуктот не постои."));
    }

    @Override
    public Product addProductVariant(Long productId, ProductVariantDto p) {
        Size s = sizeService.findById(p.sizeId);
        Type t = typeService.findById(p.typeId);
        Product product = findById(productId);
        if(product.hasProductVariantBySizeAndType(s,t)) throw new RuntimeException("Веќе постои таа комбинација");

        ProductVariant pv = new ProductVariant(s,t,p.price,p.canOrder);
        product.addProductVariant(pv);
        return productRepository.saveAndFlush(product);
    }

    @Override
    public Product updateProductVariant(Long productId, Long variantId, ProductVariantDto dto) {
        Product product = findById(productId);
        ProductVariant pv = product.getProductVariantById(variantId);
        pv.setPrice(dto.price);
        pv.setCanOrder(dto.canOrder);
        return productRepository.saveAndFlush(product);
    }

    @Override
    @Transactional
    public void deleteProductVariant(Long productId, Long variantId) {
        Product p = findById(productId);

        p.removeProductVariant(variantId);
        productRepository.flush();
    }
}

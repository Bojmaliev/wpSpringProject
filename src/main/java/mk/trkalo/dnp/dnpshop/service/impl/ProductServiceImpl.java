package mk.trkalo.dnp.dnpshop.service.impl;

import mk.trkalo.dnp.dnpshop.model.Product;
import mk.trkalo.dnp.dnpshop.repository.ProductRepository;
import mk.trkalo.dnp.dnpshop.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product saveProduct(Product p) {
        if(productRepository.existsByName(p.getName()))throw new RuntimeException("Продукт со вакво име веќе постои.");
        return productRepository.save(p);
    }



    @Override
    public void deleteAllProducts() {
        productRepository.deleteAll();
    }

    @Override
    public void deleteById(int id) {
        Product p = findById(id);
        if(p.getProductVariants().size() != 0) throw new RuntimeException("Избришете ги прво варијантите");
        productRepository.deleteById(id);
    }

    @Override
    public Product findById(int id) {
        return productRepository.findById(id).orElseThrow(()-> new RuntimeException("Продуктот не постои."));
    }

    @Override
    public Product updateById(Product product, int id) {
        if(productRepository.existsProductByNameAndIdNot(product.getName(), id)) throw new RuntimeException("Веќе постои продукт со тоа име");
        Product existing = findById(id);
        existing.setDescription(product.getDescription());
        existing.setName(product.getName());
        return productRepository.save(existing);
    }
}

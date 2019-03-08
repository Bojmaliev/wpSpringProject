package mk.trkalo.dnp.dnpshop.service;

import mk.trkalo.dnp.dnpshop.dto.ProductVariantDto;
import mk.trkalo.dnp.dnpshop.model.Product;
import mk.trkalo.dnp.dnpshop.model.ProductVariant;

import java.util.List;

public interface ProductService {
    List<Product> findAllProducts();
    Product saveProduct(Product p);
    void deleteAllProducts();
    void deleteById(int id);
    Product findById(int id);
    Product updateById(Product student, int index);
}

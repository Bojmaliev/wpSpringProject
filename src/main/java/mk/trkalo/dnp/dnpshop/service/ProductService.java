package mk.trkalo.dnp.dnpshop.service;

import mk.trkalo.dnp.dnpshop.dto.ProductVariantDto;
import mk.trkalo.dnp.dnpshop.model.order.OrderItem;
import mk.trkalo.dnp.dnpshop.model.product.Product;
import mk.trkalo.dnp.dnpshop.model.product.ProductVariant;

import java.util.List;
import java.util.Set;

public interface ProductService {
    List<Product> findAllProducts();
    Product saveProduct(Product p);
    void deleteAllProducts();
    void deleteById(Long id);
    Product findById(Long id);
    Product updateById(Product student, Long index);
    ProductVariant findProductVariantById(Long id);
    Product addProductVariant(Long productId, ProductVariantDto p);

    Product updateProductVariant(Long productId, Long variantId, ProductVariantDto productVariantDto);
    void deleteProductVariant(Long productId, Long variantId);

    void updateStock(Set<OrderItem> currList, Set<OrderItem> newList);

    void addNumberSoldToProductVariant(Long productVariantId, Integer quantity);
}

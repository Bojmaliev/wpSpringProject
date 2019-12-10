package mk.trkalo.dnp.dnpshop.repository;

import mk.trkalo.dnp.dnpshop.model.product.Product;
import mk.trkalo.dnp.dnpshop.model.product.ProductVariant;
import mk.trkalo.dnp.dnpshop.model.product.Size;
import mk.trkalo.dnp.dnpshop.model.product.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface ProductVariantRepository extends JpaRepository<ProductVariant, Long> {
    Set<ProductVariant> findAllByProduct(Product p);
    boolean existsByProduct(Product p);
    boolean existsByProductAndSizeAndType(Product p, Size s, Type t);

}

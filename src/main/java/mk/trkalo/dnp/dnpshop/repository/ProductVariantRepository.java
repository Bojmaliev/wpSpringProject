package mk.trkalo.dnp.dnpshop.repository;

import mk.trkalo.dnp.dnpshop.model.Product;
import mk.trkalo.dnp.dnpshop.model.ProductVariant;
import mk.trkalo.dnp.dnpshop.model.Size;
import mk.trkalo.dnp.dnpshop.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface ProductVariantRepository extends JpaRepository<ProductVariant, Long> {
    Set<ProductVariant> findAllByProduct(Product p);
    boolean existsByProduct(Product p);
    boolean existsByProductAndSizeAndType(Product p, Size s, Type t);

}

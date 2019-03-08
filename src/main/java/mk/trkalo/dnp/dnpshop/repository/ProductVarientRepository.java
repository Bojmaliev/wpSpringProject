package mk.trkalo.dnp.dnpshop.repository;

import mk.trkalo.dnp.dnpshop.model.Product;
import mk.trkalo.dnp.dnpshop.model.ProductVariant;
import mk.trkalo.dnp.dnpshop.model.Size;
import mk.trkalo.dnp.dnpshop.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductVarientRepository extends JpaRepository<ProductVariant, Integer> {
    boolean existsByProductAndSizeAndType(Product product, Size size, Type type);
   // boolean existsProductVariantByProductEqualsAndSizeEqualsAndTypeExists(Product product, Size size, Type type);
}

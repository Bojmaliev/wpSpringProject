package mk.trkalo.dnp.dnpshop.repository;

import mk.trkalo.dnp.dnpshop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByName(String name);
    List<Product> findAllByName(String name);
    boolean existsProductByNameAndIdNot(String name, Long id);

}

package mk.trkalo.dnp.dnpshop.repository;

import mk.trkalo.dnp.dnpshop.model.Production;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductionRepository extends JpaRepository<Production, Long> {
}

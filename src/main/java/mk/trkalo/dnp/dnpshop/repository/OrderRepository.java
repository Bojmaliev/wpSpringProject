package mk.trkalo.dnp.dnpshop.repository;

import mk.trkalo.dnp.dnpshop.model.Order;
import mk.trkalo.dnp.dnpshop.model.Status;
import mk.trkalo.dnp.dnpshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT _order FROM Order _order " +
            "WHERE _order.currentStatus.status=:status " +
            "AND EXISTS (SELECT _status FROM _order.orderStatuses _status " +
            "WHERE _status.userMadeChange=:user)")
    Optional<Order> findLastNotFinished(@Param("status") Status status, @Param("user") User user);
}

package mk.trkalo.dnp.dnpshop.service;

import mk.trkalo.dnp.dnpshop.model.order.Order;
import mk.trkalo.dnp.dnpshop.model.payloads.request.ProductVariantRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.sql.Timestamp;
import java.util.List;

public interface OrderService {
    Order createEmptyOrder();
    void deleteAll();

    Order findById(Long orderId);

    Order connectOrderWithUser(Long orderId, Long userId);

    Order findLastNotFinished();

    Order updateItemList(Long orderId, List<ProductVariantRequest> itemList);

    Order updateShippingMethod(Long orderId, Long shippingId);

    Order updateDescription(Long orderId, String desc);

    Order updateShippingDate(Long orderId, Timestamp parse);

    Order updateAddress(Long orderId, Long addressId);

    Page<Order> search(String search, Pageable pageable);
}

package mk.trkalo.dnp.dnpshop.service;

import mk.trkalo.dnp.dnpshop.model.Order;
import mk.trkalo.dnp.dnpshop.model.User;
import mk.trkalo.dnp.dnpshop.model.payloads.request.ProductVariantRequest;

import java.util.ArrayList;
import java.util.List;

public interface OrderService {
    Order createEmptyOrder();
    void deleteAll();

    Order findById(Long orderId);

    Order connectOrderWithUser(Long orderId, Long userId);

    Order findLastNotFinished();

    Order updateItemList(Long orderId, List<ProductVariantRequest> itemList);

    Order updateShippingMethod(Long orderId, Long shippingId);
}

package mk.trkalo.dnp.dnpshop.service;

import mk.trkalo.dnp.dnpshop.model.Order;
import mk.trkalo.dnp.dnpshop.model.User;

public interface OrderService {
    Order createEmptyOrder(User userCreated);
    void deleteAll();

    Order findById(Long orderId);

    void connectOrderWithUser(Long orderId, Long userId);
}

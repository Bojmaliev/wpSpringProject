package mk.trkalo.dnp.dnpshop.service.impl;

import mk.trkalo.dnp.dnpshop.model.Order;
import mk.trkalo.dnp.dnpshop.model.User;
import mk.trkalo.dnp.dnpshop.repository.OrderRepository;
import mk.trkalo.dnp.dnpshop.service.OrderService;
import mk.trkalo.dnp.dnpshop.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;

    public OrderServiceImpl(OrderRepository orderRepository, UserService userService) {
        this.orderRepository = orderRepository;
        this.userService = userService;
    }

    @Override
    public Order createEmptyOrder(User userCreated) {
        return orderRepository.save(Order.createEmptyOrder(userCreated));
    }

    @Override
    public void deleteAll() {
        orderRepository.deleteAll();
    }

    @Override
    public Order findById(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(()-> new RuntimeException("Нарачката не постои."));
    }

    @Override
    public void connectOrderWithUser(Long orderId, Long userId) {
        User user = userService.findById(userId);
        Order order = findById(orderId);
        order.connectWithUser(user);
        orderRepository.saveAndFlush(order);
    }
}

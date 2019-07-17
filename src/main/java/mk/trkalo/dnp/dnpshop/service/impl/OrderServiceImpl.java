package mk.trkalo.dnp.dnpshop.service.impl;

import mk.trkalo.dnp.dnpshop.exception.Error;
import mk.trkalo.dnp.dnpshop.model.*;
import mk.trkalo.dnp.dnpshop.model.payloads.request.ProductVariantRequest;
import mk.trkalo.dnp.dnpshop.repository.OrderRepository;
import mk.trkalo.dnp.dnpshop.service.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ShippingService shippingService;
    private final ProductService productService;
    private final UserService userService;
    private final LoggedUserService loggedUserService;

    public OrderServiceImpl(OrderRepository orderRepository, ShippingService shippingService, ProductService productService, UserService userService, LoggedUserService loggedUserService) {
        this.orderRepository = orderRepository;
        this.shippingService = shippingService;
        this.productService = productService;
        this.userService = userService;
        this.loggedUserService = loggedUserService;
    }

    @Override
    public Order createEmptyOrder() {
        Order o = Order.createEmptyOrder(loggedUserService.get());

        o.shippingMethod = shippingService.getDefaultShippingMethod();
        return orderRepository.save(o);
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
    public Order connectOrderWithUser(Long orderId, Long userId) {
        Order order = findById(orderId);
        User user=null;
        if(userId!=0)user = userService.findById(userId);

        order.connectWithUser(user);
        order.checkOrder(loggedUserService.get());

        return orderRepository.saveAndFlush(order);
    }

    @Override
    public Order findLastNotFinished() {

        return orderRepository.findLastNotFinished(Status.CREATED, loggedUserService.get()).orElseThrow(()->new Error("Корисникот нема започнато никаква нарачка"));
    }

    @Override
    public Order updateItemList(Long orderId, List<ProductVariantRequest> itemList) {
        Order order = findById(orderId);
        List<OrderItem> orderItemsReq = itemList.stream().map(a-> {
            ProductVariant pv = productService.findProductVariantById(a.productVariant);
            return new OrderItem(pv, a.quantity, a.price);
        }).collect(Collectors.toList());


        order.retainAllOrderItems(orderItemsReq);
        order.addAllOrderItems(orderItemsReq);
        order.checkOrder(loggedUserService.get());
        return orderRepository.saveAndFlush(order);

    }

    @Override
    public Order updateShippingMethod(Long orderId, Long shippingId) {
        Order order = findById(orderId);
        ShippingMethod sm = shippingService.findById(shippingId);
        order.updateShippingMethod(sm);
        return orderRepository.saveAndFlush(order);
    }
}

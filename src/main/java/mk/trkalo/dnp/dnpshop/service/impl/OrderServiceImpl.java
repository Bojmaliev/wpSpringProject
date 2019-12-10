package mk.trkalo.dnp.dnpshop.service.impl;

import mk.trkalo.dnp.dnpshop.exception.Error;
import mk.trkalo.dnp.dnpshop.model.order.Order;
import mk.trkalo.dnp.dnpshop.model.order.OrderItem;
import mk.trkalo.dnp.dnpshop.model.order.ShippingMethod;
import mk.trkalo.dnp.dnpshop.model.order.Status;
import mk.trkalo.dnp.dnpshop.model.payloads.request.ProductVariantRequest;
import mk.trkalo.dnp.dnpshop.model.product.ProductVariant;
import mk.trkalo.dnp.dnpshop.model.user.User;
import mk.trkalo.dnp.dnpshop.repository.OrderRepository;
import mk.trkalo.dnp.dnpshop.service.*;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ShippingService shippingService;
    private final ProductService productService;
    private final UserService userService;
    private final LoggedUserService loggedUserService;
    private final AddressService addressService;

    public OrderServiceImpl(OrderRepository orderRepository, ShippingService shippingService, ProductService productService, UserService userService, LoggedUserService loggedUserService, AddressService addressService) {
        this.orderRepository = orderRepository;
        this.shippingService = shippingService;
        this.productService = productService;
        this.userService = userService;
        this.loggedUserService = loggedUserService;
        this.addressService = addressService;
    }

    @Override
    public Order createEmptyOrder() {
        Order o = Order.createEmptyOrder(loggedUserService.get(), shippingService.getDefaultShippingMethod());

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

        order.connectWithUser(user, loggedUserService.get());
        return orderRepository.saveAndFlush(order);
    }

    @Override
    public Order findLastNotFinished() {

        return orderRepository.findLastNotFinished(Status.CREATED, loggedUserService.get()).orElseThrow(()->new Error("Корисникот нема започнато никаква нарачка"));
    }

    @Override
    public Order updateItemList(Long orderId, List<ProductVariantRequest> itemList) {
        Order order = findById(orderId);

        Set<OrderItem> orderItemsReq = itemList.stream().map(a-> {
            ProductVariant pv = productService.findProductVariantById(a.productVariant);
            return new OrderItem(pv, a.quantity, a.price);
        }).collect(Collectors.toSet());

        productService.updateStock(order.orderItemList, orderItemsReq);

        order.retainAllOrderItems(orderItemsReq, loggedUserService.get());
        order.addAllOrderItems(orderItemsReq, loggedUserService.get());
        return orderRepository.saveAndFlush(order);

    }

    @Override
    public Order updateShippingMethod(Long orderId, Long shippingId) {
        Order order = findById(orderId);
        ShippingMethod sm = shippingService.findById(shippingId);
        order.updateShippingMethod(sm, loggedUserService.get());
        return orderRepository.saveAndFlush(order);
    }

    @Override
    public Order updateDescription(Long orderId, String desc) {
        Order order = findById(orderId);
        order.description = desc;
        return orderRepository.saveAndFlush(order);
    }

    @Override
    public Order updateShippingDate(Long orderId, Timestamp parse) {
        Order order = findById(orderId);
        order.shouldBeShipped = parse;
        return orderRepository.saveAndFlush(order);
    }

    @Override
    public Order updateAddress(Long orderId, Long addressId) {
        Order order = findById(orderId);
        order.setAddress(addressService.findById(addressId), loggedUserService.get());
        return orderRepository.saveAndFlush(order);
    }

        @Override
    public Page<Order> search(String search, Pageable pageable) {
        Order o = Order.createOrderExample();
        ExampleMatcher ex = ExampleMatcher.matching().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);//.withIgnorePaths();
        Example<Order> example = Example.of(o, ex);
        return orderRepository.findAll(example, pageable);
    }


}

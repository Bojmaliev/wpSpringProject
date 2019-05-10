package mk.trkalo.dnp.dnpshop.web;

import mk.trkalo.dnp.dnpshop.model.Order;
import mk.trkalo.dnp.dnpshop.model.User;
import mk.trkalo.dnp.dnpshop.service.OrderService;
import mk.trkalo.dnp.dnpshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/orders", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderResource {
    private final OrderService orderService;
    private final UserService userService;

    @Autowired
    public OrderResource(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @PostMapping("/new")
    public Order createEmptyOrder(@RequestParam Long userIdCreated){
        User user = userService.findById(userIdCreated);
        return orderService.createEmptyOrder(user);
    }

    @GetMapping("/{orderId}")
    public Order findById(@PathVariable Long orderId){
        return orderService.findById(orderId);
    }


}

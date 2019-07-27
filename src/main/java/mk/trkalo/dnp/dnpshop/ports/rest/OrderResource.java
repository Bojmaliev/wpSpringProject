package mk.trkalo.dnp.dnpshop.ports.rest;

import com.fasterxml.jackson.databind.node.TextNode;
import mk.trkalo.dnp.dnpshop.model.Order;
import mk.trkalo.dnp.dnpshop.model.payloads.request.ProductVariantRequest;
import mk.trkalo.dnp.dnpshop.service.LoggedUserService;
import mk.trkalo.dnp.dnpshop.service.OrderService;
import mk.trkalo.dnp.dnpshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/orders", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderResource {
    private final OrderService orderService;
    private final UserService userService;
    private final LoggedUserService loggedUserService;

    @Autowired
    public OrderResource(OrderService orderService, UserService userService, LoggedUserService loggedUserService) {
        this.orderService = orderService;
        this.userService = userService;
        this.loggedUserService = loggedUserService;
    }

    @GetMapping("/new")
    public ResponseEntity<?> createEmptyOrder(){

        return ResponseEntity.ok(orderService.createEmptyOrder());
    }
    @GetMapping("/newOrExisting")
    public ResponseEntity<?> notFinished(){
        try {
            return ResponseEntity.ok(orderService.findLastNotFinished());
        }catch (Exception e){
            return createEmptyOrder();
        }
    }

    @GetMapping("/{orderId}")
    public Order findById(@PathVariable Long orderId){
        return orderService.findById(orderId);
    }

    @GetMapping("/{orderId}/setUser/{userId}")
    public ResponseEntity<?> connectOrderWithUser(@PathVariable Long orderId, @PathVariable Long userId){
        return ResponseEntity.ok(orderService.connectOrderWithUser(orderId, userId));
    }

    @GetMapping("/{orderId}/setShipping/{shippingId}")
    public ResponseEntity<?> updateShippingMethod(@PathVariable Long orderId, @PathVariable Long shippingId){
        return ResponseEntity.ok(orderService.updateShippingMethod(orderId, shippingId));
    }

    @PatchMapping("{orderId}/itemList")
    public ResponseEntity<?> updateOrderList(@PathVariable Long orderId, @RequestBody List<ProductVariantRequest> itemList){
        return ResponseEntity.ok(orderService.updateItemList(orderId, itemList));
    }

    @PatchMapping("{orderId}/description")
    public ResponseEntity<?> updateDescription(@PathVariable Long orderId, @RequestBody TextNode desc){
        return ResponseEntity.ok(orderService.updateDescription(orderId, desc.asText()));
    }


    @PatchMapping("{orderId}/shouldBeShipped")
    public ResponseEntity<?> shouldBeShipped(@PathVariable Long orderId, @RequestBody TextNode desc){
        return ResponseEntity.ok(orderService.updateShippingDate(orderId, Timestamp.valueOf(desc.asText())));
    }

    @GetMapping("/{orderId}/setAddress/{addressId}")
    public ResponseEntity<?> setAddressToOrder(@PathVariable Long orderId, @PathVariable Long addressId){
        return ResponseEntity.ok(orderService.updateAddress(orderId, addressId));
    }
}

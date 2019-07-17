package mk.trkalo.dnp.dnpshop.model;


import mk.trkalo.dnp.dnpshop.exception.Error;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "order_id")
    public Set<OrderItem> orderItemList = new TreeSet<>();

    @ManyToOne
    public User user;

    @ManyToOne
    public Address address;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "order_id")
    public Set<OrderStatus> orderStatuses = new TreeSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    public OrderStatus currentStatus;

    @ManyToOne
    public ShippingMethod shippingMethod;

    public String description;
    //leaved for later think
    public LocalDateTime shouldBeShipped;

    public void addOrUpdateItemToOrder(OrderItem item) {
        Optional<OrderItem> oi = orderItemList.stream().filter(a -> a.equals(item)).findFirst();
        if (oi.isPresent()) {
            oi.get().update(item);
        } else orderItemList.add(item);
    }

    public void addOrderStatus(OrderStatus orderStatus) {
        orderStatuses.add(orderStatus);
        currentStatus = orderStatus;
    }

    public void checkOrder(LoggedUser loggedUser){
        if(currentStatus.status!=Status.SUBMITTED){
            if(orderItemList.size()>0 && user != null && address != null)
                addOrderStatus(OrderStatus.create(Status.SUBMITTED, loggedUser));
        }else if(currentStatus.status != Status.CREATED){
            if(orderItemList.size()==0 || user == null || address ==null)
                addOrderStatus(OrderStatus.create(Status.CREATED, loggedUser));

        }
    }


    @Transient
    public int getPrice() {
        int sum = 0;
        for (OrderItem o : orderItemList) {
            sum += o.getTotalPrice();
        }
        return sum;
    }

    private Order() {
    }

    public void connectWithUser(User user) {
        this.user = user;
        if (user == null || user.addresses.size() == 0) this.address = null;
        else {
            this.address = user.addresses.get(0);
        }
    }

    public static Order createEmptyOrder(User userCreated) {
        Order order = new Order();
        order.addOrderStatus(OrderStatus.create(Status.CREATED, userCreated));
        return order;
    }


    public void updateShippingMethod(ShippingMethod sm) {
        if (!sm.active) throw new Error("Доставувачкиот метод е истечен");
        shippingMethod = sm;
    }

    public void retainAllOrderItems(List<OrderItem> orderItemsReq) {
        orderItemList.retainAll(orderItemsReq);
    }

    public void addAllOrderItems(List<OrderItem> orderItemsReq) {
        orderItemsReq.forEach(this::addOrUpdateItemToOrder);
    }
}

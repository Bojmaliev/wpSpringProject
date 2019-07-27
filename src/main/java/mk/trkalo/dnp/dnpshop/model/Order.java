package mk.trkalo.dnp.dnpshop.model;


import mk.trkalo.dnp.dnpshop.exception.Error;

import javax.persistence.*;
import java.sql.Timestamp;
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
    public Timestamp shouldBeShipped;

    public void addOrUpdateItemToOrder(OrderItem item, LoggedUser user) {
        Optional<OrderItem> oi = orderItemList.stream().filter(a -> a.equals(item)).findFirst();
        if (oi.isPresent()) {
            oi.get().update(item);
        } else orderItemList.add(item);
        checkOrder(user);
    }

    public void addOrderStatus(OrderStatus orderStatus) {
        orderStatuses.add(orderStatus);
        currentStatus = orderStatus;
    }

    private void checkOrder(LoggedUser loggedUser) {
        if (currentStatus.status != Status.SUBMITTED) {
            if (orderItemList.size() > 0 && user != null && (address != null || !shippingMethod.requiresAddress)) {
                addOrderStatus(OrderStatus.create(Status.SUBMITTED, loggedUser));
            }
        } else if (currentStatus.status != Status.CREATED) {
            if (orderItemList.size() == 0 || user == null || (address == null && shippingMethod.requiresAddress)) {
                OrderStatus old = orderStatuses.stream().filter(a -> a.equals(OrderStatus.create(Status.CREATED, loggedUser))).findFirst().get();
                currentStatus = old;
                orderStatuses.clear();
                orderStatuses.add(old);

            }

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

    public void connectWithUser(User user, LoggedUser userMadeChange) {
        this.user = user;
        if (user == null || user.addresses.size() == 0) this.address = null;
        else {
            this.address = user.addresses.get(0);
        }
        checkOrder(userMadeChange);
    }

    public static Order createEmptyOrder(User userCreated, ShippingMethod defaultShippingMethod) {
        Order order = new Order();
        order.addOrderStatus(OrderStatus.create(Status.CREATED, userCreated));

        order.shippingMethod = defaultShippingMethod;
        order.shouldBeShipped = order.shippingMethod.nextShippment;
        return order;
    }


    public void updateShippingMethod(ShippingMethod sm, LoggedUser userMadeChange) {
        if (!sm.active) throw new Error("Доставувачкиот метод е истечен");
        shippingMethod = sm;
        checkOrder(userMadeChange);
    }

    public void retainAllOrderItems(Set<OrderItem> orderItemsReq, LoggedUser userMadeChange) {
        orderItemList.retainAll(orderItemsReq);
    }

    public void addAllOrderItems(Set<OrderItem> orderItemsReq, LoggedUser userMadeChange) {
        orderItemsReq.forEach(a -> addOrUpdateItemToOrder(a, userMadeChange));
    }

    public void setAddress(Address address, LoggedUser loggedUser) {
        this.address = address;
        checkOrder(loggedUser);
    }
}

package mk.trkalo.dnp.dnpshop.model;



import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="order_id")
    public Set<OrderItem> orderItemList = new TreeSet<>();

    @ManyToOne
    public User user;

    @ManyToOne
    public Address address;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="order_id")
    public Set<OrderStatus> orderStatuses = new TreeSet<>();

    @OneToOne
    public OrderStatus currentStatus;

    @ManyToOne
    public ShippingMethod shippingMethod;

    public String description;

    public void addItemToOrder(OrderItem item){
        orderItemList.add(item);
    }
    public void addOrderStatus(User user, Status status){
        OrderStatus os = OrderStatus.create(status, user);
        orderStatuses.add(os);
        currentStatus = os;
    }
    public void makeOrderInFuture(User user, LocalDateTime dateTimeShouldBeDone){
        OrderStatus os = OrderStatus.createInFuture(user, dateTimeShouldBeDone);
        orderStatuses.add(os);
        currentStatus = os;
    }
    @Transient
    public int getPrice(){
        int sum=0;
        for (OrderItem o : orderItemList){
            sum+=o.getTotalPrice();
        }
        return sum;
    }
    private Order(){
    }

    public void connectWithUser(User user){
        if(this.user != null) throw new RuntimeException("Нарачката е веќе поврзана со корисник");
        this.user = user;
    }

    public static Order createEmptyOrder(User userCreated){
        Order order = new Order();
        order.addOrderStatus(userCreated, Status.CONSTRUCTED);

        return order;
    }
}

package mk.trkalo.dnp.dnpshop.model;


import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="order_id")
    private List<OrderItem> orderItemList = new ArrayList<>();

    @ManyToOne
    @OnDelete(action= OnDeleteAction.NO_ACTION)
    private User user;

    private HashMap<String, Address> shippingAddresses = new HashMap<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="order_id")
    private List<OrderStatus> orderStatuses;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private ShippingMethod shippingMethod;

    private LocalDateTime created;

    private String description;

    @Transient
    public int getPrice(){
        int sum=0;
        for (OrderItem o : orderItemList){
            sum+=o.getTotalPrice();
        }
        return sum;
    }

}

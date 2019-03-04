package mk.trkalo.dnp.dnpshop.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy = "id.order")
    private List<OrderItem> orderItemList;

    @NotNull
    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "id.order")
    private List<OrderStatus> orderStatuses;

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

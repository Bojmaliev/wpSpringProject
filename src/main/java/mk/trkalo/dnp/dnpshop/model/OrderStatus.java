package mk.trkalo.dnp.dnpshop.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
public class OrderStatus implements Comparable<OrderStatus> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Enumerated(EnumType.STRING)
    public Status status;

    public LocalDateTime dateTime;

    @ManyToOne
    @JsonIgnoreProperties({"phoneNumbers", "addresses", "email"})
    public User userMadeChange;

    private OrderStatus() {
    }

    public static OrderStatus create(Status status, User userMadeChange) {
        OrderStatus os = new OrderStatus();
        os.status = status;
        os.dateTime = LocalDateTime.now();
        os.userMadeChange = userMadeChange;
        return os;
    }

    @Override
    public int compareTo(OrderStatus o)
    {
        int s = status.compareTo(o.status);
        if(s == 0) return dateTime.compareTo(o.dateTime);
        return s;
    }
}

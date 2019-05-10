package mk.trkalo.dnp.dnpshop.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

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

    private OrderStatus(){
    }
    public static OrderStatus create(Status status, User userMadeChange){
        OrderStatus os = new OrderStatus();
        os.status = status;
        os.dateTime = LocalDateTime.now();
        os.userMadeChange = userMadeChange;
        return os;
    }
    public static OrderStatus createInFuture(User userMadeChange, LocalDateTime dateTime){
        OrderStatus os = new OrderStatus();
        os.status = Status.IN_FUTURE;
        os.userMadeChange=userMadeChange;
        os.dateTime = dateTime;
        return os;
    }

    @Override
    public int compareTo(OrderStatus o) {
        return dateTime.compareTo(o.dateTime);
    }
}

package mk.trkalo.dnp.dnpshop.model.order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import mk.trkalo.dnp.dnpshop.model.user.User;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;


@Entity
public class OrderStatus implements Comparable<OrderStatus> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Enumerated(EnumType.STRING)
    public Status status;

    public Timestamp dateTime;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnoreProperties({"phoneNumbers", "addresses", "email"})
    public User userMadeChange;

    private OrderStatus() {
    }

    public static OrderStatus create(Status status, User userMadeChange) {
        OrderStatus os = new OrderStatus();
        os.status = status;
        os.dateTime = new Timestamp(System.currentTimeMillis());
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderStatus that = (OrderStatus) o;
        return status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(status);
    }
}

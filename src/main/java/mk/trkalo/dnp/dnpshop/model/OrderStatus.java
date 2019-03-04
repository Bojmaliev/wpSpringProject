package mk.trkalo.dnp.dnpshop.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;


@Entity
public class OrderStatus {
    @EmbeddedId
    private OrderStatusId id;

    private LocalDateTime dateTime;

    @ManyToOne
    private User userMadeChange;

}

@Embeddable
class OrderStatusId implements Serializable {
    @ManyToOne
    @NotNull
    private Order order;
    @Enumerated(EnumType.STRING)
    private Status status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderStatusId that = (OrderStatusId) o;
        return Objects.equals(order, that.order) &&
                status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(order, status);
    }
}
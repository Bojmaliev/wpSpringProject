package mk.trkalo.dnp.dnpshop.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class OrderItem implements Comparable<OrderItem>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne
    @NotNull
    public ProductVariant productVariant;

    @NotNull
    public Integer quantity;

    @NotNull
    public Integer price;

    @Transient
    public Integer getTotalPrice(){
        return quantity*price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return Objects.equals(id, orderItem.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public int compareTo(OrderItem o) {
        return id.compareTo(o.id);
    }
}



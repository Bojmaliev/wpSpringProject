package mk.trkalo.dnp.dnpshop.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NotNull
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private ProductVariant productVariant;
    @NotNull
    private Integer quantity;
    @NotNull
    private Integer price;

    public Integer getQuantity() {return quantity;}
    public Integer getPrice() { return price;  }
    public ProductVariant getProductVarient() { return productVariant;}

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
}



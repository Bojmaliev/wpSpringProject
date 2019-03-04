package mk.trkalo.dnp.dnpshop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;
@Entity
public class OrderItem {
    /* @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private long id;
     */
    @EmbeddedId
    private OrderItemId id;
    @NotNull
    private int quantity;
    @NotNull
    private int price;

    public int getQuantity() {
        return quantity;
    }

    public int getPrice() {
        return price;
    }

    public ProductVarient getProductVarient() {
        return id.getProductVarient();
    }
    public Order getOrder(){
        return id.getOrder();
    }
    @Transient
    public int getTotalPrice(){
        return quantity*price;
    }
}

@Embeddable
class OrderItemId implements Serializable {
    @ManyToOne
    @JsonIgnore
    private Order order;
    @ManyToOne
    @NotNull
    private ProductVarient productVarient;

    public Order getOrder() {
        return order;
    }

    public ProductVarient getProductVarient() {
        return productVarient;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setProductVarient(ProductVarient productVarient) {
        this.productVarient = productVarient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItemId that = (OrderItemId) o;
        return Objects.equals(order, that.order) &&
                Objects.equals(productVarient, that.productVarient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(order, productVarient);
    }
}


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

    public ProductVariant getProductVarient() {
        return id.getProductVariant();
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
    private ProductVariant productVariant;

    public Order getOrder() {
        return order;
    }

    public ProductVariant getProductVariant() {
        return productVariant;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setProductVariant(ProductVariant productVariant) {
        this.productVariant = productVariant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItemId that = (OrderItemId) o;
        return Objects.equals(order, that.order) &&
                Objects.equals(productVariant, that.productVariant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(order, productVariant);
    }
}


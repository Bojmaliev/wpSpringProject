package mk.trkalo.dnp.dnpshop.model.order;

import mk.trkalo.dnp.dnpshop.model.product.ProductVariant;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class OrderItem implements Comparable<OrderItem> {
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
    public Integer getTotalPrice() {
        return quantity * price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return Objects.equals(productVariant.id, orderItem.productVariant.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productVariant.id);
    }

    @Override
    public int compareTo(OrderItem o) {
        return productVariant.id.compareTo(o.productVariant.id);
    }

    public OrderItem(ProductVariant productVariant, Integer quantity, Integer price) {
        this.productVariant = productVariant;
        this.quantity = quantity;
        this.price = price;

    }

    private OrderItem() {
    }

    public void update(OrderItem item) {
        quantity = item.quantity;
        price = item.price;
    }
}



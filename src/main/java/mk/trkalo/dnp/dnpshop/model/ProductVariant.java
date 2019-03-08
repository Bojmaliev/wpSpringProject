package mk.trkalo.dnp.dnpshop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class ProductVariant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @NotNull
    @JsonIgnore
    private Product product;
    @ManyToOne
    @NotNull
    private Size size;
    @ManyToOne
    @NotNull
    private Type type;
    @NotNull
    private int price;

    public ProductVariant(){}
    public ProductVariant(Product p, Size s, Type t, int price){
        this.product = p;
        this.size = s;
        this.type = t;
        this.price = price;
    }
    public int getId() {
        return id;
    }

    public Size getSize() {
        return size;
    }

    public Type getType() {
        return type;
    }

    public int getPrice() {
        return price;
    }

    public Product getProduct() {
        return product;
    }
}

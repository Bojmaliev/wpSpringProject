package mk.trkalo.dnp.dnpshop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

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
    @OneToMany(mappedBy = "productVariantId")
    @JsonIgnore
    private List<ProductionProductVarient> productionProductVarients;
    private int numberSold;
    private boolean canOrder;

    public ProductVariant(){this.canOrder=true; numberSold=0;}
    public ProductVariant(Product p, Size s, Type t, int price, boolean canOrder){
        this.product = p;
        this.size = s;
        this.type = t;
        this.price = price;
        this.canOrder = canOrder;
        this.numberSold = 0;
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

    public void setPrice(int price){
        this.price = price;
    }
    @Transient
    public int getNumberProduced(){
        return productionProductVarients.stream().mapToInt(ProductionProductVarient::getQuantity).sum();
    }
    public int getNumberSold(){
        return this.numberSold;
    }

    @Transient
    public int getNumberAvailable(){
        return getNumberProduced()-getNumberSold();
    }
    public boolean isCanOrder(){return canOrder;}

    public void setCanOrder(boolean canOrder){
        this.canOrder = canOrder;
    }

    public Product getProduct() {
        return product;
    }
}

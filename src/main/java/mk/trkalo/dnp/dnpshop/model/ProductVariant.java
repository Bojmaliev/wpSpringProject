package mk.trkalo.dnp.dnpshop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class ProductVariant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @ManyToOne
    @NotNull
    public Size size;
    @ManyToOne
    @NotNull
    public Type type;

    @ManyToOne
    private Product product;

    @NotNull
    public Integer price;

    private Integer numberProduced =0;
    private Integer numberSold = 0;
    private Boolean canOrder = true;

    public ProductVariant(){}
    public ProductVariant(Size s, Type t, int price, Boolean canOrder){
        this.size = s;
        this.type = t;
        setPrice(price);
        this.canOrder = canOrder;

    }

    public void setPrice(Integer price){
        if(price < 0) throw new RuntimeException("Цената неможе да биде негативен број");
        this.price = price;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getProduct() {
        return product.getName();
    }

    public Integer getNumberSold(){
        return this.numberSold;
    }
    @Transient
    public Integer getNumberProduced(){
        return this.numberProduced;
    }

    @Transactional
    public void addProduction(Integer quantity){
        this.numberProduced+=quantity;
    }
    @Transactional
    public void addNumberSold(Integer quantity){this.numberSold +=quantity;}


    @Transient
    public Integer getNumberAvailable(){
        return getNumberProduced()-getNumberSold();
    }
    public Boolean isCanOrder(){return canOrder;}

    public void setCanOrder(Boolean canOrder){
        this.canOrder = canOrder;
    }

}

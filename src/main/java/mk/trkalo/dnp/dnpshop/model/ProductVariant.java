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
    private Long id;
    @ManyToOne
    @NotNull
    private Size size;
    @ManyToOne
    @NotNull
    private Type type;

    @NotNull
    private int price;

    private int numberProduced =0;
    private int numberSold = 0;
    private Boolean canOrder = true;

    public ProductVariant(){}
    public ProductVariant(Size s, Type t, int price, Boolean canOrder){
        this.size = s;
        this.type = t;
        setPrice(price);
        this.canOrder = canOrder;

    }
    public Long getId() {
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
        if(price < 0) throw new RuntimeException("Цената неможе да биде негативен број");
        this.price = price;
    }
    public Integer getNumberSold(){
        return this.numberSold;
    }
    @Transient
    public Integer getNumberProduced(){
        return this.numberProduced;
    }

    public void addProduction(int quantity){
        this.numberProduced+=quantity;
    }
    public void addNumberSold(int quantity){this.numberSold +=quantity;}


    @Transient
    public Integer getNumberAvailable(){
        return getNumberProduced()-getNumberSold();
    }
    public Boolean isCanOrder(){return canOrder;}

    public void setCanOrder(Boolean canOrder){
        this.canOrder = canOrder;
    }

}

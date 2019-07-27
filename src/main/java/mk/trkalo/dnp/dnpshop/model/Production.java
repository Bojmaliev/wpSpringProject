package mk.trkalo.dnp.dnpshop.model;


import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
public class Production {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @NotNull
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ProductVariant productVariant;
    private int quantity;
    private Timestamp dateTime;

    public Production(){}
    public Production(ProductVariant pv, int quantity){
        this.productVariant = pv;
        this.quantity = quantity;
        dateTime = new Timestamp(System.currentTimeMillis());
    }
    public int getQuantity(){
        return quantity;
    }
    private Timestamp getDateTime(){
        return dateTime;
    }

}

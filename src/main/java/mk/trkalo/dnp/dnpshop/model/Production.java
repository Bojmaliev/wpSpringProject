package mk.trkalo.dnp.dnpshop.model;



import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

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
    private LocalDateTime dateTime;

    public Production(){}
    public Production(ProductVariant pv, int quantity){
        this.productVariant = pv;
        this.quantity = quantity;
        dateTime = LocalDateTime.now();
    }
    public int getQuantity(){
        return quantity;
    }
    private LocalDateTime getDateTime(){
        return dateTime;
    }

}

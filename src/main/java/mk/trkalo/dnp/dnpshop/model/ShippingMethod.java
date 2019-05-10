package mk.trkalo.dnp.dnpshop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class ShippingMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String name;

    public Integer minOrderPrice=0;


    public boolean active=true;

    private ShippingMethod(){
    }
    public ShippingMethod(String name){
        this.name = name;
    }
}

package mk.trkalo.dnp.dnpshop.model.order;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class ShippingMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String name;

    public Integer minOrderPrice=0;

    public Timestamp nextShippment = new Timestamp(System.currentTimeMillis());

    public Boolean requiresAddress=true;


    public boolean active=true;

    private ShippingMethod(){
    }
    public ShippingMethod(String name, Boolean requiresAddress){
        this.requiresAddress=requiresAddress;
        this.name = name;
    }
}

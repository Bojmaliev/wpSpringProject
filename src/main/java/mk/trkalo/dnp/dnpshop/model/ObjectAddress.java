package mk.trkalo.dnp.dnpshop.model;

import javax.persistence.Entity;

@Entity
public class ObjectAddress extends Address {
    public String objectTitle;
    public ObjectAddress(){}
    public ObjectAddress(User user, City city, String objectTitle) {
        super(user, city);
        this.objectTitle = objectTitle;
    }
}

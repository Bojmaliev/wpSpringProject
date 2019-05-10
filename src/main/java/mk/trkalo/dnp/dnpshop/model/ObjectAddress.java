package mk.trkalo.dnp.dnpshop.model;

import javax.persistence.Entity;

@Entity
public class ObjectAddress extends Address {

    public String objectTitle;

    private ObjectAddress(){}
    public ObjectAddress(User user, City city, String objectTitle) {
        super(user, city);
        this.objectTitle = objectTitle;
    }

    @Override
    public String getType() {
        return "OBJECT";
    }
}
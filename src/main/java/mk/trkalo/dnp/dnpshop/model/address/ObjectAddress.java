package mk.trkalo.dnp.dnpshop.model.address;

import javax.persistence.Entity;

@Entity
public class ObjectAddress extends Address {

    public String objectTitle;

    private ObjectAddress(){}
    public ObjectAddress(City city, String municipality, String description, String objectTitle) {
        super(city, municipality, description);
        this.objectTitle = objectTitle;
    }

    @Override
    public String getType() {
        return "OBJECT";
    }
}
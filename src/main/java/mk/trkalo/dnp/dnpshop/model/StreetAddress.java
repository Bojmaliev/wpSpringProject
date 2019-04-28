package mk.trkalo.dnp.dnpshop.model;

import javax.persistence.Entity;

@Entity
public class StreetAddress extends Address{
    public String streetName;
    public String streetNumber;
    public String additional;

    public StreetAddress(){}
    public StreetAddress(User user, City city, String streetName, String streetNumber, String additional) {
        super(user, city);
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.additional = additional;
    }
}

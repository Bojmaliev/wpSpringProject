package mk.trkalo.dnp.dnpshop.model;

import javax.persistence.Entity;

@Entity
public class StreetAddress extends Address{
    public String streetName;
    public String streetNumber;
    public String additional;

    private StreetAddress(){}

    public StreetAddress(City city, String municipality, String description, String streetName, String streetNumber, String additional) {
        super(city, municipality, description);
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.additional = additional;
    }

    @Override
    public String getType() {
        return "STREET";
    }
}
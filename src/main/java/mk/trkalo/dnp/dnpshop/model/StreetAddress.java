package mk.trkalo.dnp.dnpshop.model;

import javax.persistence.Entity;

@Entity
public class StreetAddress extends Address{
    private String streetName;
    private String streetNumber;
    private String additional;
}

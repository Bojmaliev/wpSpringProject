package mk.trkalo.dnp.dnpshop.model;

import javax.persistence.Entity;

@Entity
public class CoordinateAddress extends Address {
    private String latitude;
    private String longitude;
}

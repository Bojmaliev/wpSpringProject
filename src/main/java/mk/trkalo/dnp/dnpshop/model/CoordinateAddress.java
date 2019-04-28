package mk.trkalo.dnp.dnpshop.model;

import javax.persistence.Entity;

@Entity
public class CoordinateAddress extends Address {
    public Double latitude;
    public Double longitude;
    public CoordinateAddress(){}
    public CoordinateAddress(User user, City city, Double latitude, Double longitude){
        super(user,city);
        this.latitude=latitude;
        this.longitude=longitude;
    }

}

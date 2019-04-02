package mk.trkalo.dnp.dnpshop.model;

import javax.persistence.Entity;

@Entity
public class CoordinateAddress extends Address {
    private Double latitude;
    private Double longitude;
    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }


}

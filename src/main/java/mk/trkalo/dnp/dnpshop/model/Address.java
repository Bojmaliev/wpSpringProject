package mk.trkalo.dnp.dnpshop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @ManyToOne
    @JsonIgnore
    @NotNull
    public User user;
    @NotNull
    @ManyToOne
    public City city;
    public String municipality;
    public String description;
    public Double latitude;
    public Double longitude;

    protected Address(){}
    public Address(User user, City city){
        this.user = user;
        this.city = city;
    }

    public Address(User user, City city, Double latitude, Double longitude){
        this.user = user;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getType(){
        return "NORMAL";
    }



}


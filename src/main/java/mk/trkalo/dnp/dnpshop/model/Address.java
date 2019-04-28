package mk.trkalo.dnp.dnpshop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Address {
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

    public Address(){}
    public Address(User user, City city){
        this.user = user;
        this.city = city;
    }
    void addMunicipality(String municipality){
        this.municipality = municipality;
    }
    void addDescription(String description){
        this.description = description;
    }


}


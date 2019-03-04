package mk.trkalo.dnp.dnpshop.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;
    @ManyToOne
    @NotNull
    private User user;
    @NotNull
    private String city;
    private String municipality;
    private String description;
}


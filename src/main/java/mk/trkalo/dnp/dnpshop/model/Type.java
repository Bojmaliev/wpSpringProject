package mk.trkalo.dnp.dnpshop.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity

public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    String name;

    public Type(){}
    public Type(String name){this.name = name;}

    public Long getId(){
        return id;
    }
    public String getName(){
        return name;
    }
}

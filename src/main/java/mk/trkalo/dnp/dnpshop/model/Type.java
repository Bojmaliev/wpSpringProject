package mk.trkalo.dnp.dnpshop.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity

public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    String name;

    public Type(){}
    public Type(String name){this.name = name;}

    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
}

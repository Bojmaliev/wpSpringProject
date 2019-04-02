package mk.trkalo.dnp.dnpshop.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Size {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;

    public Size(){}
    public Size(String name){
        this.name = name;
    }

    public Long getId(){ return id;}
    public String getName(){
        return name;
    }
}

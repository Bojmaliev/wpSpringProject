package mk.trkalo.dnp.dnpshop.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Size {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @NotNull
    public String name;
    public String size;

    public Size(){}
    public Size(String name){
        this.name = name;
    }
    public Size(String name, String size){
        this.name = name;
        this.size = size;
    }
}

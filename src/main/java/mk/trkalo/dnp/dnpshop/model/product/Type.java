package mk.trkalo.dnp.dnpshop.model.product;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity

public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @NotNull
    public String name;

    public Type(){}
    public Type(String name){this.name = name;}

}

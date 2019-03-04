package mk.trkalo.dnp.dnpshop.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class ProductVarient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @NotNull
    private Product product;
    @ManyToOne
    @NotNull
    private Size size;
    @ManyToOne
    @NotNull
    private Type type;
    @NotNull
    private int price;
}

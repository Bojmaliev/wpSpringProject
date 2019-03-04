package mk.trkalo.dnp.dnpshop.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class ProductionProductVarient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @NotNull
    private ProductVarient productVarientId;
    private int quantity;
    private LocalDateTime dateTime;

}

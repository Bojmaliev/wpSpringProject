package mk.trkalo.dnp.dnpshop.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Името на продуктот е задолжително")
    private String name;
    private String description;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="product_id")
    private List<ProductVariant> productVariants = new ArrayList<>();

    public Product(){}
    public Product(String name, String description){
        this.name=name;
        this.description=description;
    }
    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public boolean hasProductVariantBySizeAndType(Size s, Type t){
        return productVariants.stream().anyMatch(a -> a.getSize() == s && a.getType() == t);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<ProductVariant> getProductVariants() {
        return productVariants;
    }

    public void addProductVariant(ProductVariant pv) {
        productVariants.add(pv);
    }

    public ProductVariant getProductVariantById(Long productVariantId){
        return productVariants.stream().filter(a->a.getId() == productVariantId).findFirst().orElseThrow(()-> new RuntimeException("Таква варијанта не постои"));
    }
    public void removeProductVariant(Long productVariantId){
        productVariants.remove(getProductVariantById(productVariantId));
    }
}
package mk.trkalo.dnp.dnpshop.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String name;

    @ElementCollection(targetClass = String.class)
    private List<String> phoneNumbers;

    @OneToMany(mappedBy = "user")
    private List<Address> addresses;

    private String email;
}

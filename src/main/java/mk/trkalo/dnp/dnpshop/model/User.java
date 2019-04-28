package mk.trkalo.dnp.dnpshop.model;


import mk.trkalo.dnp.dnpshop.repository.converters.PhoneNumbersConverter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @NotNull
    public String name;

    @Convert(converter = PhoneNumbersConverter.class)
    public Set<String> phoneNumbers = new TreeSet<>();

    @OneToMany(mappedBy = "user")
    public List<Address> addresses;

    public String email;

    public void addPhoneNumber(String phoneNumber) {
        this.phoneNumbers.add(phoneNumber);
    }
}

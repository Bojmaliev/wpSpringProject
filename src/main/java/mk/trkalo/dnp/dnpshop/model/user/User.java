package mk.trkalo.dnp.dnpshop.model.user;


import mk.trkalo.dnp.dnpshop.model.address.Address;
import mk.trkalo.dnp.dnpshop.repository.converters.PhoneNumbersConverter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @NotNull
    public String name;

    @Convert(converter = PhoneNumbersConverter.class)
    public Set<String> phoneNumbers = new TreeSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    public List<Address> addresses = new ArrayList<>();


    public void addAddress(Address address){
        addresses.add(address);
    }

    public void addPhoneNumber(String phoneNumber) {
        phoneNumber =phoneNumber.replaceAll(" ", "");
        if(!validatePhoneNumber(phoneNumber)) throw new RuntimeException("Некој од телефонските броеви не е валиден");
        this.phoneNumbers.add(phoneNumber);
    }

    public static boolean validatePhoneNumber(String phoneNumber){
        return phoneNumber.matches("[+]?[0123456789]{6,12}");
    }
    protected User(){}
    public User(String name){
        this.name=name;
    }
    public User(String name, @NotNull Set<String> phoneNumbers, @NotNull List<Address> addresses){
        for(String pn: phoneNumbers)this.addPhoneNumber(pn);
        this.name = name;
        for(Address ad: addresses)this.addAddress(ad);
    }

}

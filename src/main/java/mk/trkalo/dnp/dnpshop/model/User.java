package mk.trkalo.dnp.dnpshop.model;


import mk.trkalo.dnp.dnpshop.repository.converters.PhoneNumbersConverter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    public List<Address> addresses = new ArrayList<>();

    public String email;


    public void addAddress(Address address){
        addresses.add(address);
    }

    public void addPhoneNumber(String phoneNumber) {
        this.phoneNumbers.add(phoneNumber);
    }

    public static boolean validatePhoneNumber(String phoneNumber){
        return phoneNumber.matches("[+]?[0123456789]{6,12}");
    }

    public User(){}
    public User(String name, Set<String> phoneNumbers, List<Address> addresses){
        Set<String> newPhone = new TreeSet<>();
        for(String pn: phoneNumbers){
            String replacedNumber =pn.replaceAll(" ", "");
            newPhone.add(replacedNumber);
            if(!validatePhoneNumber(replacedNumber)) throw new RuntimeException("Некој од телефонските броеви не е валиден");
        }
        this.name = name;
        this.phoneNumbers=newPhone;
        this.addresses = addresses;
    }

}

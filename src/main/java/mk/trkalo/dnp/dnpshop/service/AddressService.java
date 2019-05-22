package mk.trkalo.dnp.dnpshop.service;


import mk.trkalo.dnp.dnpshop.dto.address.AddressDto;
import mk.trkalo.dnp.dnpshop.dto.address.StreetDto;
import mk.trkalo.dnp.dnpshop.model.Address;

import java.util.List;

public interface AddressService {
//    Address save(Address address);
//    StreetAddress save(StreetAddress streetAddress);
//    ObjectAddress save(ObjectAddress objectAddress);
    void deleteAll();

    List<Address> saveAddresses(AddressDto address, StreetDto street, String object);
}

package mk.trkalo.dnp.dnpshop.service;

import mk.trkalo.dnp.dnpshop.model.Address;
import mk.trkalo.dnp.dnpshop.model.ObjectAddress;
import mk.trkalo.dnp.dnpshop.model.StreetAddress;

public interface AddressService {
    Address save(Address address);
    StreetAddress save(StreetAddress streetAddress);
    ObjectAddress save(ObjectAddress objectAddress);
    void deleteAll();
}

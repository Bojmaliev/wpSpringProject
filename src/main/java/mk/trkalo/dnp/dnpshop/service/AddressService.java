package mk.trkalo.dnp.dnpshop.service;

import mk.trkalo.dnp.dnpshop.model.Address;
import mk.trkalo.dnp.dnpshop.model.CoordinateAddress;
import mk.trkalo.dnp.dnpshop.model.ObjectAddress;
import mk.trkalo.dnp.dnpshop.model.StreetAddress;

public interface AddressService {
    CoordinateAddress save(CoordinateAddress address);
    StreetAddress save(StreetAddress address);
    ObjectAddress save(ObjectAddress address);
    void deleteAll();
}
